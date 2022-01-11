package eu.hypetime.proxy.lang;

import eu.hypetime.proxy.ProxySystem;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

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
          if (getDataBaseLanguage(player) == null) language.put(player, lang);
          Integer databaseLanguage = getDataBaseLanguage(player);
          language.put(player, databaseLanguage);

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
          String message = Language.getConfig(player).getString(messageShort);
          message = message.replace("%prefix%", ProxySystem.getInstance().getPrefix());
          player.sendMessage(new TextComponent(message));
     }

}
