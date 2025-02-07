package com.example;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

public class Principal {
	public static MongoDatabase db;
	public static MongoClient mongoClient;
    public static void main(String[] args) {
        try {

			db = conectarMongoDB();

			// PASO 3.1: Obtenemos una coleccion para trabajar con ella
			MongoCollection<Document> collection = db.getCollection(crearColeccion(db));

			generarContratos(collection);

		} finally {

		}
 	}

	public static MongoDatabase conectarMongoDB(){
		// PASO 1: Conexión al Server de MongoDB Pasandole el host y el puerto
		ConnectionString connectionString = new ConnectionString("mongodb://mati:mati@localhost:27017");
		mongoClient = MongoClients.create(connectionString);
		System.out.println("Mongolo" + mongoClient.toString());
		
		// PASO 2: Conexión a la base de datos
		db = mongoClient.getDatabase("facturacionconsumo");

		return db;
	}

	public static String crearColeccion(MongoDatabase db){
		// PASO 3.0: Creamos una coleccion para trabajar con ella si no existiera previamente
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

		return nombreColeccion;
	}

	public static void generarContratos(MongoCollection<Document> colle){
		List<Document> contratos = new ArrayList<>();
		for(int i = 0; i <= 100; i++){
			// Crear los consumos por hora de un dia
			List<Document> consumoHora = new ArrayList<>();
			
			consumoHora.add(new Document("Mañana", Math.random() * 20).append("tarde", Math.random() * 20));
		
			
			// Crear el contador con consumo diario
			List<Document> contador = new ArrayList<>();
			for (int ind = 1; ind <= 30; ind++) {
				contador.add(new Document("dia", ind).append("precio energetico", Math.random() * 10).append("consumo", consumoHora));
			}
			
			// Crear el documento de cliente
			String dni = ((int) (Math.random() * 100000000)) + "A";
			Document cliente = new Document("dni", dni);

			// Crear el primer contrato
			String vivienda = "Avenida Baleares " + i;
			Document contrato = new Document("vivienda", vivienda).append("cliente", cliente)
					.append("contador", contador);
			
			contratos.add(contrato);
		}
		colle.insertMany(contratos);
	}
}

