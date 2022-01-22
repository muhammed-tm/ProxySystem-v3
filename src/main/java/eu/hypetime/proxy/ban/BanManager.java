package eu.hypetime.proxy.ban;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import eu.hypetime.proxy.ProxySystem;
import eu.hypetime.proxy.utils.UUIDFetcher;
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

     public void ban(UUID uuid, BanReasons reason, CommandSender banner) {
          if (isBanned(uuid)) {
               banner.sendMessage("§7The player is already banned");
               return;
          }
          String name = UUIDFetcher.getName(uuid);
          BanPlayer player;
          if (banner instanceof ProxiedPlayer) {
               player = new BanPlayer(name, reason, banner.getName());
          } else {
               player = new BanPlayer(name, reason, "System");
          }
          Document doc = (new Document()).append("uuid", uuid.toString()).append("banPlayer", gson.toJson(player));
          collection.insertOne(doc);
          banner.sendMessage("§7The player §6" + name + " §7was successfully banned.");
     }

     public void unban(UUID uuid, CommandSender sender) {
          if (!isBanned(uuid)) {
               sender.sendMessage("The player is not banned.");
               return;
          }
          BasicDBObject query = new BasicDBObject("uuid", uuid.toString());
          collection.find(query).first().clear();
          sender.sendMessage("§7The player §6" + UUIDFetcher.getName(uuid) + " §7was unbanned");
     }

     public boolean isBanned(UUID uuid) {
          boolean isBanned;
          BasicDBObject query = new BasicDBObject("uuid", uuid.toString());
          isBanned = collection.find(query).first() != null;
          return isBanned;
     }

     public BanPlayer getBanPlayer(UUID uuid) {
          if (isBanned(uuid)) {
               BasicDBObject query = new BasicDBObject("uuid", uuid.toString());
               return gson.fromJson(collection.find(query).first().get("banPlayer").toString(), BanPlayer.class);
          } else {
               return null;
          }
     }

     public String getReason(UUID uuid) {
          if(isBanned(uuid))
               return getBanPlayer(uuid).getReason();
          else
               return "";
     }

     public long getEnd(UUID uuid) {
          if(isBanned(uuid))
               return getBanPlayer(uuid).getEnd();
          else
               return 0;
     }

     public String getBanner(UUID uuid) {
          if(isBanned(uuid))
               return getBanPlayer(uuid).getBanner();
          else
               return "";
     }

     public String getReamainingTime(UUID uuid) {
          long current = System.currentTimeMillis();
          long end = getBanPlayer(uuid).getEnd();
          if (end == -1) {
               return "§c§lPERMANENT";
          }
          long millis = end - current;
          long seconds = 0;
          long minutes = 0;
          long hours = 0;
          long days = 0;
          long weeks = 0;
          while (millis > 1000) {
               millis -= 1000;
               ++seconds;
          }
          while (seconds > 60) {
               seconds -= 60;
               ++minutes;
          }
          while (minutes > 60) {
               minutes -= 60;
               ++hours;
          }
          while (hours > 24) {
               hours -= 24;
               ++days;
          }
          while (days > 7) {
               days -= 7;
               ++weeks;
          }
          return "§e" + weeks + " week(s) " + days + " day(s) " + hours + " hour(s) " + minutes + " minute(s) " + seconds + " second(s) ";
     }


}
