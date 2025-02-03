package com.example;

import com.mongodb.ConnectionString;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

public class Principal {
    public static void main(String[] args) {
        try {

			// PASO 1: Conexión al Server de MongoDB Pasandole el host y el puerto
			ConnectionString connectionString = new ConnectionString("mongodb://mati:mati@localhost:27017/?authSource=admin");
			MongoClient mongoClient = MongoClients.create(connectionString);
			
            // PASO 2: Conexión a la base de datos
			MongoDatabase db = mongoClient.getDatabase("facturacionconsumo");

			// PASO 3.0: Creamos una coleccion para trabajar con ella si no existiera previamente
			// Nombre de la colección que queremos verificar
			String nombreColeccion = "febrero";
			MongoIterable<String> colecciones = db.listCollectionNames();
			boolean existe = false;
			for (String coleccion : colecciones)
				if (coleccion.equals(nombreColeccion)) {
					existe = true;
					break;
				}

			if (existe)
				System.out.println("La colección " + nombreColeccion + " ya existe.");
			else {
				System.out.println("La colección " + nombreColeccion + " no existe y la creo a continuación.");
				db.createCollection(nombreColeccion);
			}

			// PASO 3.1: Obtenemos una coleccion para trabajar con ella
			MongoCollection<Document> collection = db.getCollection(nombreColeccion);

			// Crear el contador con consumo diario
            List<Document> contador = new ArrayList<>();
            for (int i = 1; i <= 30; i++) {
                contador.add(new Document("dia", i).append("consumo", Math.random() * 50));
            }

			// Crear el documento de cliente
			Document cliente1 = new Document("dni", "11111111A").append("nombre", "Ivan García Torresano").append("edad", 23); 

			// Crear el primer contrato
			Document contrato1 = new Document("vivienda", "Av. Baleares 0").append("precio energetico", "0.10").append("cliente", cliente1)
					.append("contador", contador);

			// Consultar el documento insertado
			// Document encontrado = collection.find(new Document("usuario",
			// "usuario123")).first();
			FindIterable<Document> documentos = collection.find(new Document("vivienda", "Av. Baleares 0"));
			int i=0;
			for (Document encontrado : documentos) {				
				if (encontrado != null) {//Este if es por si hacemos una búsqueda sencilla de un documento 
					i++;
					
					System.out.println("\nDocumento encontrado: "+ i);
					//System.out.println(encontrado.toJson());
					String vivienda = encontrado.getString("vivienda");
					System.out.println("Vivienda: " + vivienda);
					System.out.println("Contador: ");

					// Recorrer el contador
					for (Document consumodiario : encontrado.getList("contador", Document.class)) {
						Integer dia = consumodiario.getInteger("dia");
						Integer consumo = consumodiario.getInteger("consumo");

						System.out.println("Dia: " + dia + ", Consumo: " + consumo);
					}
				}
			}

		} finally {

		}
    }
}

