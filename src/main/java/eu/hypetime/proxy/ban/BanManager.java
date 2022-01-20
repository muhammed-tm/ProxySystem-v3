package eu.hypetime.proxy.ban;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import eu.hypetime.proxy.ProxySystem;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.bson.Document;

import java.util.UUID;

/*
    Created by Andre
    At 21:06 Uhr | 20. Jan.. 2022
    Project ProxySystem-v3
*/
public class BanManager {

     public static Gson gson = (new Gson()).newBuilder().create();
     private static MongoCollection<Document> collection;

     public BanManager() {
          collection = ProxySystem.getInstance().getMongoDB().getDatabase().getCollection("proxy_ban");
     }

     public static void ban(UUID uuid, String name, BanReasons reason, CommandSender banner) {
          if (isBanned(uuid)) {
               banner.sendMessage("Der Spieler ist bereits gebannt");
               return;
          }
          BanPlayer player;
          if (banner instanceof ProxiedPlayer) {
               player = new BanPlayer(name, reason, banner.getName());
          } else {
               player = new BanPlayer(name, reason, "System");
          }
          BasicDBObject query = new BasicDBObject("uuid", uuid);
          Document doc = (new Document()).append("uuid", uuid).append("banPlayer", gson.toJson(player));
          collection.findOneAndReplace(query, doc);
          if(isBanned(uuid)) {
               banner.sendMessage("Der Spieler " + name + " wurde erfolgreich gebannt.");
          }
     }

     public static boolean isBanned(UUID uuid) {
          boolean isBanned;
          BasicDBObject query = new BasicDBObject("uuid", uuid);
          isBanned = collection.find(query).first() != null;
          return isBanned;
     }

     public BanPlayer getBanPlayer(UUID uuid) {
          if (isBanned(uuid)) {
               BasicDBObject query = new BasicDBObject("uuid", uuid);
               return gson.fromJson(collection.find(query).first().get("friendPlayer").toString(), BanPlayer.class);
          } else {
               return null;
          }
     }

}
