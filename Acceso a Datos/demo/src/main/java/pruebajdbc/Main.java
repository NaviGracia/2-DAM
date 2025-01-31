package pruebajdbc;

import org.bson.Document;
import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Main {
    public static void main(String[] args) {
        /*ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017/?authSource=admin");
        MongoClient mongoClient = MongoClients.create(connectionString);
        MongoDatabase database = mongoClient.getDatabase("basedatos1");*/

        MongoClient mongoClient = new MongoClient();
        // Getting a connection 
        mongoClient = new MongoClient(host, port);
        
        // Select the database
        db = mongoClient.getDatabase(database);

        MongoCollection<Document> collection = database.getCollection("colleccion1");
        Document document = new Document("name", "John").
            append("email", "john@doe.com").
            append("twitter", "johndoe").
            append("location", new Document("city", "nowhere").append("zip", 12345));
        collection.insertOne(document);
    }
}