package eu.hypetime.proxy.database;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

/*
    Created by Andre
    At 20:48 Uhr | 20. Jan.. 2022
    Project ProxySystem-v3
*/
public class MongoDB {

     private final MongoClient client;
     private final MongoDatabase database;

     public MongoDB() {
          client = new MongoClient((new MongoClientURI("mongodb://91.210.224.170:27017/?directConnection=true&ssl=false")));
          database = client.getDatabase("minecraft");
     }

     public MongoClient getClient() {
          return client;
     }

     public MongoDatabase getDatabase() {
          return database;
     }
}
