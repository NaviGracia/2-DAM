package com.example;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.Filters;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bson.Document;

public class Principal {
	public static MongoDatabase db;
	public static MongoClient mongoClient;
	public static Random random = new Random();
	public static String nombreColeccion = "febrero";
	public static ArrayList<ArrayList<Double>> diasMes = new ArrayList<ArrayList<Double>>();
	
    public static void main(String[] args) {
        try {
			generarPreciosMes();

			db = conectarMongoDB();
			//GENERAMOS LOS CONTRATOS DE UN MES
			//MongoCollection<Document> collection = db.getCollection(crearColeccion(db));
			//generarContratos(collection);

			//GENERAMOS LAS FACTURAS DE UN MES
			//Factura.generarFacturas(db, db.getCollection("febrero"));

			//CONSULTA OBTENER UNA FACTURA DE UNA VIVIENDA EN UN MES DETERMINADO
			Factura.buscarFacturas(db, "febrero", "Avenida Baleares 0");

			//ACTUALIZAR CLIENTE DE UN CONTRATO
			actualizarDniContrato(db, "febrero", "Avenida Baleares 0", "11111111A");

			//ELIMINAR FACTURA DE UNA VIVIENDA EN UN MES DETERMINADO
			Factura.eliminarFactura(db, "febrero", "Avenida Baleares 0");
		} finally {

		}
 	}

	public static MongoDatabase conectarMongoDB(){
		ConnectionString connectionString = new ConnectionString("mongodb://mati:mati@localhost:27017");
		mongoClient = MongoClients.create(connectionString);
		System.out.println("Mongolo" + mongoClient.toString());
		
		db = mongoClient.getDatabase("facturacionconsumo");

		return db;
	}

	public static String crearColeccion(MongoDatabase db){
		MongoIterable<String> colecciones = db.listCollectionNames();
		boolean existe = false;
		for (String coleccion : colecciones){
			if (coleccion.equals(nombreColeccion)) {
				existe = true;
				break;
			}
		}

		if (existe)
			System.out.println("La colección " + nombreColeccion + " ya existe.");
		else {
			System.out.println("La colección " + nombreColeccion + " no existe y la creo a continuación.");
			db.createCollection(nombreColeccion);
		}

		return nombreColeccion;
	}

	public static void generarContratos(MongoCollection<Document> colle){
		List<Document> contratos = new ArrayList<>();
		for(int numContrato = 0; numContrato <= 100; numContrato++){
			// Crear el contador con consumo diario
			List<Document> contador = new ArrayList<>();
			for (int dia = 1; dia <= 30; dia++) {
				// Crear los consumos por hora de un dia
				List<Document> consumoHora = new ArrayList<>();
				for(int hora = 0; hora <24; hora++){
					int diaMes = dia - 1;
					consumoHora.add(new Document("Consumo", Math.round((Math.random() * (0.80 - 0.15) + 0.15) * 100.0) / 100.0).append("Precio", diasMes.get(diaMes).get(hora)));
				}
				contador.add(new Document("dia", dia).append("consumo", consumoHora));
			}
			
			// Crear el documento de cliente
			String dni = ((int) (random.nextInt(99999999)+1000000)) + "A";
			Document cliente = new Document("dni", dni);

			// Crear el primer contrato
			String vivienda = "Avenida Baleares " + numContrato;
			Document contrato = new Document("vivienda", vivienda).append("cliente", cliente)
					.append("contador", contador);
			
			contratos.add(contrato);
		}
		colle.insertMany(contratos);
		System.out.println("💾 Contratos de " + nombreColeccion + " generados.");
	}

	public static void generarPreciosMes(){
		for (int dia = 1; dia <= 30; dia++) {
			ArrayList<Double> preciosPorHora = new ArrayList<Double>();
			for(int hora = 0; hora < 24; hora++){
				preciosPorHora.add(Math.round((Math.random() * (0.34 - 0.13) + 0.13) * 100.0) / 100.0);
			}
			diasMes.add(preciosPorHora);
		}
	}

	public static void actualizarDniContrato(MongoDatabase db, String mes, String vivienda, String nuevoDni) {
        String nombreColeccionContratos = mes; 
        MongoCollection<Document> coleccionContratos = db.getCollection(nombreColeccionContratos);
    
        Document contratoExistente = coleccionContratos.find(Filters.eq("vivienda", vivienda)).first();
    
        if (contratoExistente != null) {
            // Actualizar el DNI dentro del campo "cliente"
            coleccionContratos.updateOne(
                Filters.eq("vivienda", vivienda), 
                new Document("$set", new Document("cliente.dni", nuevoDni))
            );
    
            System.out.println("✅ DNI del contrato actualizado correctamente para la vivienda: " + vivienda);
        } else {
            System.out.println("❌ No se encontró ningún contrato para la vivienda: " + vivienda + " en " + mes);
        }
    }
}

