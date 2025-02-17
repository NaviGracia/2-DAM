package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bson.Document;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

public class MongoDB {
    public static MongoDatabase db;
    public static MongoClient mongoClient;
	public static Random random = new Random();
	public static String nombreColeccion = "febrero";

    public MongoDB(){}

    public MongoDatabase getDb() {
        return db;
    }


    public void conectarMongoDB(){
		ConnectionString connectionString = new ConnectionString("mongodb://mati:mati@localhost:27017");
		mongoClient = MongoClients.create(connectionString);
		System.out.println("Mongolo" + mongoClient.toString());
		
		db = mongoClient.getDatabase("facturacionconsumo");
	}

	public String crearColeccion(MongoDatabase db){
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

	public void generarContratos(MongoCollection<Document> colle, ArrayList<ArrayList<Double>> diasMes){
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

	
}
