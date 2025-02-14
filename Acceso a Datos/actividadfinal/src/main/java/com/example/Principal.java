package com.example;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Random;

import org.bson.Document;

public class Principal {
	public static MongoDB mongoDB = new MongoDB();
	public static MongoClient mongoClient;
	public static Random random = new Random();
	public static String nombreColeccion = "febrero";
	public static ArrayList<ArrayList<Double>> diasMes = new ArrayList<ArrayList<Double>>();
	public static Postgresql psql = new Postgresql();
	
    public static void main(String[] args) {
        try {
			generarPreciosMes();

			//MONGODB
			//mongoDB.conectarMongoDB();
			//GENERAMOS LOS CONTRATOS DE UN MES
			//MongoCollection<Document> collection = mongoDB.getDb().getCollection(mongoDB.crearColeccion(mongoDB.getDb()));
			//mongoDB.generarContratos(collection, diasMes);

			//GENERAMOS LAS FACTURAS DE UN MES
			//Factura.generarFacturas(db, db.getCollection("febrero"));

			//CONSULTA OBTENER UNA FACTURA DE UNA VIVIENDA EN UN MES DETERMINADO
			//Factura.buscarFacturas(mongoDB.getDb(), "febrero", "Avenida Baleares 0");

			//ACTUALIZAR CLIENTE DE UN CONTRATO
			//Factura.actualizarDniFactura(mongoDB.getDb(), "febrero", "Avenida Baleares 0", "11111111A");
			//Factura.buscarFacturas(mongoDB.getDb(), "febrero", "Avenida Baleares 0");

			//ELIMINAR FACTURA DE UNA VIVIENDA EN UN MES DETERMINADO
			//Factura.eliminarFactura(mongoDB.getDb(), "febrero", "Avenida Baleares 0");
			//Factura.buscarFacturas(mongoDB.getDb(), "febrero", "Avenida Baleares 0");

			//POSTGRESQL
			Connection sql = psql.conectar();
			psql.generarConsumoMes(sql, diasMes);
		} catch(Exception e) {
			System.out.println(e);
		}
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
}

