package com.example;

import org.bson.Document;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class Factura {
    String vivienda;
    String dni;
    String mes;
    double facturaTotal;
    double consumoMes;

    public Factura(String vivienda, String dni, String mes, double facturaTotal, double consumoMes) {
        this.vivienda = vivienda;
        this.dni = dni;
        this.facturaTotal = facturaTotal;
        this.consumoMes = consumoMes;
    }

    public static void generarFacturas(MongoDatabase db, MongoCollection<Document> coleccionMes) {
        String nombreColeccionFacturas = "f" + coleccionMes.getNamespace().getCollectionName();
        
        // Verificar si la colección de facturas ya existe
        boolean existe = false;
        for (String coleccion : db.listCollectionNames()) {
            if (coleccion.equals(nombreColeccionFacturas)) {
                existe = true;
                break;
            }
        }
        
        // Si la colección no existe, la creamos
        if (!existe) {
            db.createCollection(nombreColeccionFacturas);
            System.out.println("📂 Creada colección de facturas: " + nombreColeccionFacturas);
        } else {
            System.out.println("✅ La colección de facturas ya existe: " + nombreColeccionFacturas);
        }

        // Obtener la colección de facturas
        MongoCollection<Document> coleccionFacturas = db.getCollection(nombreColeccionFacturas);
        
        MongoCursor<Document> cursor = coleccionMes.find().iterator();
        
        while (cursor.hasNext()) {
            Document contrato = cursor.next();
            
            // Extraer datos del contrato
            String vivienda = contrato.getString("vivienda");
            Document cliente = (Document) contrato.get("cliente");
            String dni = cliente.getString("dni");

            double consumoTotal = 0;
            double precioTotal = 0;

            // Obtener el contador (lista de consumos diarios)
            for (Document dia : (Iterable<Document>) contrato.get("contador")) {
                for (Document hora : (Iterable<Document>) dia.get("consumo")) {
                    consumoTotal += hora.getDouble("Consumo");
                    precioTotal += hora.getDouble("Precio") * hora.getDouble("Consumo");
                }
            }

            // Crear la factura
            precioTotal = Math.round(precioTotal * 100.0) / 100.0;
            consumoTotal = Math.round(consumoTotal * 100.0) / 100.0;
            Factura factura = new Factura(vivienda, dni, coleccionMes.getNamespace().getCollectionName(), precioTotal, consumoTotal);
            System.out.println("📄 Factura generada para " + vivienda + " - DNI: " + dni);
            System.out.println("⚡ Consumo total: " + consumoTotal + " kWh | 💰 Costo total: " + precioTotal + "€");
            
            // Guardar la factura en la colección de facturas
            guardarFactura(coleccionFacturas, factura);
        }
    }

    private static void guardarFactura(MongoCollection<Document> coleccionFacturas, Factura factura) {
        Document docFactura = new Document("vivienda", factura.vivienda)
            .append("dni", factura.dni)
            .append("mes", factura.mes)
            .append("facturaTotal", factura.facturaTotal)
            .append("consumoMes", factura.consumoMes);

        coleccionFacturas.insertOne(docFactura);
        System.out.println("💾 Factura guardada en MongoDB en la colección: " + coleccionFacturas.getNamespace().getCollectionName());
    }

    public class BuscarFactura {
    public static Document buscarFactura(MongoDatabase db, String mes, String vivienda) {
        String nombreColeccionFacturas = "f" + mes;

        MongoCollection<Document> coleccionFacturas = db.getCollection(nombreColeccionFacturas);

        Document factura = coleccionFacturas.find(Filters.eq("vivienda", vivienda)).first();

        if (factura != null) {
            System.out.println("✅ Factura encontrada: " + factura.toJson());
        } else {
            System.out.println("❌ No se encontró ninguna factura para la vivienda: " + vivienda + " en " + mes);
        }
        
        return factura;
    }
}
}
