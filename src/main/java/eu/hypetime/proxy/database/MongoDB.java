package eu.hypetime.proxy.database;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/*
    Created by Andre
    At 20:48 Uhr | 20. Jan.. 2022
    Project ProxySystem-v3
*/
public class MongoDB {

     private final MongoClient client;
     private final MongoDatabase database;

     public MongoDB() {
          client = new MongoClient("193.135.10.148", 27017);
          database = client.getDatabase("minecraft");
     }

     public MongoClient getClient() {
          return client;
     }

     public MongoDatabase getDatabase() {
          return database;
     }
}
