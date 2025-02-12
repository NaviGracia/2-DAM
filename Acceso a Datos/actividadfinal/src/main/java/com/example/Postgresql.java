package com.example;

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

    public void generarContratos(Connection sql, ArrayList<ArrayList<Double>> diasMes) {
        for (int i = 0; i < 500; i++) {
            String cliente = generarCliente(sql);
            generarContador(sql, i, diasMes);
            generarContrato(sql, cliente, i);
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

    public static void generarContador(Connection sql, int numContador,  ArrayList<ArrayList<Double>> diasMes){
        String contador = "INSERT INTO contador (contador) VALUES (?)";
        try{
            PreparedStatement statement = sql.prepareStatement(contador);
            statement.setInt(1, numContador);

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("❌ Error al insertar contador: " + e.getMessage());
        }  
        
        //CONSUMOS DEL CONTADOR
        for (int dia = 1; dia <=30; dia++) {
            for (int hora = 0; hora <= 23; hora++) {
                String consumo = "INSERT INTO consumo (contador, dia, hora, consumo, precio) VALUES (?, ?, ?, ?, ?)";
                try{
                    PreparedStatement statement = sql.prepareStatement(consumo);
                    statement.setInt(1, numContador);
                    statement.setInt(2, dia);
                    statement.setInt(3, hora);
                    statement.setDouble(4, Math.round((Math.random() * (0.80 - 0.15) + 0.15) * 100.0) / 100.0);
                    statement.setDouble(5, diasMes.get(dia-1).get(hora));
                    statement.executeUpdate();
                } catch (SQLException e) {
                    System.out.println("❌ Error al insertar consumo: " + e.getMessage());
                }  
            }   
        }
    }

    public static void generarContrato(Connection sql, String cliente, int numContrato){
        String contrato = "INSERT INTO contrato (vivienda, cliente, contador) VALUES (?, ?, ?)";
            try{
                PreparedStatement statement = sql.prepareStatement(contrato);
                String vivienda = "Avenida Baleares " + numContrato;
                statement.setString(1, vivienda);
                statement.setString(2, cliente);
                statement.setInt(3, numContrato);


                statement.executeUpdate();
            } catch (SQLException e) {
                System.out.println("❌ Error al insertar contrato: " + e.getMessage());
            }  
    }
}
