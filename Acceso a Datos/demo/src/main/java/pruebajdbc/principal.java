package pruebajdbc;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.sql.Statement;

import org.bson.Document;



import com.mongodb.ConnectionString;

import com.mongodb.client.MongoClient;

import com.mongodb.client.MongoClients;

import com.mongodb.client.MongoCollection;

import com.mongodb.client.MongoCursor;

import com.mongodb.client.MongoDatabase;



public class principal {



public static void main(String[] args) {


try {



// PASO 1: Conexión al Server de MongoDB Pasandole el host y el puerto


ConnectionString connectionString = new ConnectionString("mongodb://mati:mati@localhost:27017/?authSource=admin");

MongoClient mongoClient = MongoClients.create(connectionString);

//new MongoClient("localhost", 27017);


// PASO 2: Conexión a la base de datos

MongoDatabase db = mongoClient.getDatabase("basedatos1");



// PASO 3: Obtenemos una coleccion para trabajar con ella

MongoCollection<Document> collection = db.getCollection("colleccion1");



Document document = new Document("name", "Marcos").

append("email", "correomarcos").

/*append("twitter", twitter).

append("hobbies", hobbies).*/

append("location", new Document("city", "Valencia").append("zip", "46020"));


collection.insertOne(document);



collection.find();


MongoCursor<Document> resultDocument = collection.find().iterator();


// Iterate over the results printing each document

while (resultDocument.hasNext()) {

//System.out.println(resultDocument.next().getString("name"));

System.out.println(resultDocument.next().toString());

}
} finally {

}














/* Connection conexion = null;


try {

Class.forName("org.postgresql.Driver");

conexion = DriverManager.getConnection(

"jdbc:postgresql://localhost:5432/ciclismo",

"mati", "mati");


Statement st = conexion.createStatement();

ResultSet rs = st.executeQuery("SELECT dorsal, nombre, edad, nomeq FROM public.ciclista;");

while (rs.next()) {

// System.out.print("Column 1 returned ");

System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3));

//System.out.println(rs.getString(1), rs.get);

}

rs.close();

st.close();



} catch (ClassNotFoundException cnfe) {

cnfe.printStackTrace();

} catch (SQLException sqle) {

sqle.printStackTrace();

} */





// TODO Auto-generated method stub



}



}