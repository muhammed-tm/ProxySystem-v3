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
         // client = new MongoClient(new MongoClientURI("mongodb://193.135.10.148:27017/?readPreference=primary&appname=mongosh+1.1.9&directConnection=true&ssl=false"));
          client = new MongoClient((new MongoClientURI("mongodb://127.0.0.1:27017/?directConnection=true&useSSL=false")));
          database = client.getDatabase("minecraft");
     }

     public MongoClient getClient() {
          return client;
     }

     public MongoDatabase getDatabase() {
          return database;
     }
}
