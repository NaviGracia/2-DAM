package com.example;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class Postgresql {
    public static Random random = new Random();

    public Postgresql(){}

    public Connection conectar() {
        String url = "jdbc:postgresql://localhost:5432/energetica";
        String usuario = "mati";
        String contraseña = "mati";

        try {
            Connection conexion = DriverManager.getConnection(url, usuario, contraseña);
            System.out.println("✅ Conexión exitosa a PostgreSQL.");
            return conexion;
        } catch (SQLException e) {
            System.out.println("❌ Error al conectar a PostgreSQL: " + e.getMessage());
            return null;
        }
    }

    public void generarConsumoMes(Connection sql, ArrayList<ArrayList<Double>> diasMes) {
        System.out.println("Iniciando generación de contratos...");
        for (int i = 0; i < 100; i++) {
            System.out.println("Generando contrato #" + i);
            String cliente = generarCliente(sql);
            System.out.println("Cliente generado: " + cliente);
            generarContrato(sql, cliente, i);
            System.out.println("Contrato generado para cliente: " + cliente);
            generarConsumoContador(sql, i, diasMes);
            System.out.println("Contador generado: " + i);
        }
        System.out.println("✅ Contratos Generados");
    }
    

    public static String generarCliente(Connection sql){
        String cliente = "INSERT INTO cliente (dni) VALUES (?)";
        String dni = "";
            try{
                PreparedStatement statement = sql.prepareStatement(cliente);
                dni = ((int) (random.nextInt(99999999)+1000000)) + "A";
                statement.setString(1, dni);

                statement.executeUpdate();
            } catch (SQLException e) {
                System.out.println("❌ Error al insertar cliente: " + e.getMessage());
            }  
        return dni;
    }

    public static void generarContrato(Connection sql, String cliente, int numContrato){
        String contrato = "INSERT INTO contrato (contador, vivienda, cliente) VALUES (?, ?, ?)";
            try{
                PreparedStatement statement = sql.prepareStatement(contrato);
                String vivienda = "Avenida Baleares " + numContrato;
                statement.setInt(1, numContrato);
                statement.setString(2, vivienda);
                statement.setString(3, cliente);
                statement.executeUpdate();

            } catch (SQLException e) {
                System.out.println("❌ Error al insertar contrato: " + e.getMessage());
            }  
    }

    public static void generarConsumoContador(Connection sql, int numContador,  ArrayList<ArrayList<Double>> diasMes){
        //CONTADOR
        for (int dia = 1; dia <=30; dia++) {
            
            String psql = "INSERT INTO consumo_febrero (contador, dia, consumo_horas, precio_horas) VALUES (?, ?, ?, ?)";
            try{
                ArrayList<Double> consumo = new ArrayList<>();
                for (int index = 0; index <=23; index++) {
                    consumo.add(Math.round((Math.random() * (0.80 - 0.15) + 0.15) * 100.0) / 100.0);
                }
                PreparedStatement statement = sql.prepareStatement(psql);
                statement.setInt(1, numContador);
                statement.setInt(2, dia);

                Double[] consumoArray = consumo.toArray(new Double[0]);
                Array sqlArray = sql.createArrayOf("float8", consumoArray);
                statement.setArray(3, sqlArray);

                Double[] precioArray = diasMes.get(dia-1).toArray(new Double[0]);
                Array sqlArrayPrecio = sql.createArrayOf("float8", precioArray);
                statement.setArray(4, sqlArrayPrecio);
                
                statement.executeUpdate();
            } catch (SQLException e) {
                System.out.println("❌ Error al insertar consumo: " + e.getMessage());
            }  
             
        }
    }
}
