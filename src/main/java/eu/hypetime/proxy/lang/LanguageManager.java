package eu.hypetime.proxy.lang;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import eu.hypetime.proxy.ProxySystem;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.config.Configuration;
import org.bson.Document;

import java.util.HashMap;

/*
    Created by Andre
    At 19:33 Uhr | 11. Jan.. 2022
    Project ProxySystem-v3
*/
public class LanguageManager {

     public static HashMap<ProxiedPlayer, Integer> language = new HashMap<>();

     public static void register(ProxiedPlayer player) {
          int lang = 0;
          Integer databaseLanguage = getDataBaseLanguage(player);
          if (databaseLanguage == null) language.put(player, lang);
          language.put(player, databaseLanguage);

     }

     public static void changeLanguage(ProxiedPlayer player, int id, boolean toggle) {
          if (toggle) {
               if (getLanguage(player) == 1) {
                    setLanguage(player, 0);
               } else if (getLanguage(player) == 0) {
                    setLanguage(player, 1);
               }
          } else {
               setLanguage(player, id);
          }
     }

     public static void setLanguage(ProxiedPlayer player, int id) {
          language.remove(player);
          language.put(player, id);
     }

     public static void save(ProxiedPlayer player) {
          BasicDBObject query = new BasicDBObject("uuid", player.getUniqueId().toString());
          MongoCollection<Document> collection = ProxySystem.getInstance().getMongoDB().getDatabase().getCollection("proxy_PlayerData");
          Document doc = new Document().append("uuid", player.getUniqueId().toString()).append("Language", getLanguage(player));
          if (collection.find(query).first() == null) {
               collection.insertOne(doc);
          } else {
               collection.findOneAndReplace(query, doc);
          }
     }

     public static int getLanguage(ProxiedPlayer player) {
         if(!language.containsKey(player)) {
             register(player);
         }
         return language.get(player);
     }

     public static Integer getDataBaseLanguage(ProxiedPlayer player) {
          BasicDBObject query = new BasicDBObject("uuid", player.getUniqueId().toString());
          MongoCollection<Document> collection = ProxySystem.getInstance().getMongoDB().getDatabase().getCollection("proxy_PlayerData");
          if (collection.find(query).first() != null) {
               return (Integer) collection.find(query).first().get("Language");
          } else {
               setLanguage(player, 0);
               save(player);
               return getLanguage(player);
          }
     }

     public static void sendMessage(ProxiedPlayer player, String messageShort) {
          player.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', getMessage(player, messageShort))));
     }

     public static String getMessage(ProxiedPlayer player, String messageShort) {
          Configuration config = Language.getConfig(player);
          String message = config.getString(messageShort);
          if (message.equals("")) messageShort = "msgNotFound";
          message = config.getString(messageShort);
          message = message.replace("%prefix% ", ProxySystem.getInstance().getPrefix());
          message = message.replace("%player%", player.getName());
          message = message.replace("%lang%", Language.getLanguage(getLanguage(player)).getName());
          return message;
     }

}
