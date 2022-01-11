package eu.hypetime.proxy.lang;

import eu.hypetime.proxy.ProxySystem;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.config.Configuration;

import java.sql.ResultSet;
import java.sql.SQLException;
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
          if(toggle) {
               if(getLanguage(player) == 1) {
                    setLanguage(player, 0);
               } else if(getLanguage(player) == 0) {
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
          ProxySystem.getInstance().getMySQL().update("UPDATE proxy_PlayerData SET Language = '" + language.get(player) + "' WHERE UUID = '" + player.getUniqueId() + "'");
     }

     public static int getLanguage(ProxiedPlayer player) {
          return language.get(player);
     }

     public static Integer getDataBaseLanguage(ProxiedPlayer player) {
          ResultSet rs = ProxySystem.getInstance().getMySQL().getResult("SELECT * FROM proxy_PlayerData WHERE UUID = '" + player.getUniqueId() + "'");
          try {
               if (rs == null) return null;
               if (!rs.next()) return null;
               return rs.getInt("Language");
          } catch (SQLException exception) {
               exception.printStackTrace();
          }
          return null;
     }

     public static void sendMessage(ProxiedPlayer player, String messageShort) {
          Configuration config = Language.getConfig(player);
          String message = config.getString(messageShort);
          if(message.equals("")) messageShort = "msgNotFound";
          message = config.getString(messageShort);
          message = message.replace("%prefix% ", config.getString("prefix"));
          message = message.replace("%player%", player.getName());
          message = message.replace("%lang%", Language.getLanguage(getLanguage(player)).getName());
          player.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', message)));
     }

}
