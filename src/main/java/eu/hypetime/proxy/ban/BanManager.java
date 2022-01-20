package eu.hypetime.proxy.ban;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import eu.hypetime.proxy.ProxySystem;
import eu.hypetime.proxy.utils.UUIDFetcher;
import io.github.waterfallmc.waterfall.utils.UUIDUtils;
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

     public static void ban(UUID uuid, BanReasons reason, CommandSender banner) {
          if (isBanned(uuid)) {
               banner.sendMessage("Der Spieler ist bereits gebannt");
               return;
          }
          String name = UUIDFetcher.getName(uuid);
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

     public static void unban(UUID uuid, CommandSender sender) {
          if(!isBanned(uuid)) {
               sender.sendMessage("Der Spieler ist nicht gebannt.");
               return;
          }
          BasicDBObject query = new BasicDBObject("uuid", uuid);
          collection.find(query).first().clear();
          if(!isBanned(uuid)) {
               sender.sendMessage("Der Spieler " + UUIDFetcher.getName(uuid) + " wurde entbannt");
          }
     }

     public static boolean isBanned(UUID uuid) {
          boolean isBanned;
          BasicDBObject query = new BasicDBObject("uuid", uuid);
          isBanned = collection.find(query).first() != null;
          return isBanned;
     }

     public static BanPlayer getBanPlayer(UUID uuid) {
          if (isBanned(uuid)) {
               BasicDBObject query = new BasicDBObject("uuid", uuid);
               return gson.fromJson(collection.find(query).first().get("friendPlayer").toString(), BanPlayer.class);
          } else {
               return null;
          }
     }

}
