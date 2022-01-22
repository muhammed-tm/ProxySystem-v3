package eu.hypetime.proxy.ban;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import eu.hypetime.proxy.ProxySystem;
import eu.hypetime.proxy.lang.Language;
import eu.hypetime.proxy.lang.LanguageManager;
import eu.hypetime.proxy.utils.UUIDFetcher;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.bson.Document;

import java.util.Collection;
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
          if (ProxyServer.getInstance().getPlayer(name) != null) {
               ProxyServer.getInstance().getPlayer(name).disconnect("§8==========§cBan§8==========\n"
                    + "§7Du wurdest vom Netzwerk gesperrt.\n"
                    + "§7Grund§8: §6" + getReason(uuid) + "\n"
                    + "§7Verbleibende Zeit§8: " + getReamainingTime(uuid) + "\n"
                    + "§7Gebannt von§8: §6" + getBanner(uuid) + "\n"
                    + "§8==========§cBan§8==========");
          }
     }

     public void unban(UUID uuid, CommandSender sender) {
          if (!isBanned(uuid)) {
               sender.sendMessage("The player is not banned.");
               return;
          }
          BasicDBObject query = new BasicDBObject("uuid", uuid.toString());
          collection.deleteOne(query);
          sender.sendMessage("§7The player §6" + UUIDFetcher.getName(uuid) + " §7was unbanned");
     }

     public void unban(UUID uuid, Collection<ProxiedPlayer> players) {
          BasicDBObject query = new BasicDBObject("uuid", uuid.toString());
          collection.deleteOne(query);
          for (ProxiedPlayer player : players) {
               if (player.hasPermission("system.unban.see")) {
                    Language language = Language.getLanguage(LanguageManager.getLanguage(player));
                    if (language == Language.ENGLISH) {
                         player.sendMessage("§7The Player §6" + UUIDFetcher.getName(uuid) + " §7was automatically unbanned from the system§8!");
                    } else {
                         player.sendMessage("§7Der Spieler §6" + UUIDFetcher.getName(uuid) + " §7wurde automatisch vom System entbannt§8!");
                    }
               }
          }
     }

     public boolean isBanned(UUID uuid) {
          BasicDBObject query = new BasicDBObject("uuid", uuid.toString());
          return collection.find(query).first() != null;
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
          if (isBanned(uuid))
               return getBanPlayer(uuid).getReason();
          else
               return "";
     }

     public long getEnd(UUID uuid) {
          if (isBanned(uuid))
               return getBanPlayer(uuid).getEnd();
          else
               return 0;
     }

     public String getBanner(UUID uuid) {
          if (isBanned(uuid))
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
