package eu.hypetime.proxy.lang;

import eu.hypetime.proxy.ProxySystem;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.config.Configuration;

import java.io.File;
import java.util.ArrayList;

/*
    Created by Andre
    At 21:19 Uhr | 11. Jan.. 2022
    Project ProxySystem-v3
*/
public enum Language {

     ENGLISH(0, "English", ProxySystem.getInstance().getEnglish(), ProxySystem.getInstance().getEnglishCfg()),
     GERMAN(1, "Deutsch", ProxySystem.getInstance().getGerman(), ProxySystem.getInstance().getGermanCfg());

     private final int id;
     private final String name;
     private final File file;
     private final Configuration cfg;

     Language(int id, String name, File file, Configuration cfg) {
          this.id = id;
          this.name = name;
          this.file = file;
          this.cfg = cfg;
     }

     public static Language getLanguage(int lang) {
          for (Language language : Language.values()) {
               if (language.getId() == lang) {
                    return language;
               }
          }
          return ENGLISH;
     }

     public static Configuration getConfig(ProxiedPlayer player) {
          return getLanguage(LanguageManager.getLanguage(player)).getCfg();
     }

     public static ArrayList<String> getShorts() {
          ArrayList<String> shorts = new ArrayList<>();
          for (Language language : Language.values()) {
               shorts.add(language.getName().substring(0, 1).toLowerCase());
          }
          return shorts;
     }

     public static Language getLanguageByShort(String idShort) {
          for (Language language : Language.values()) {
               if(language.getName().substring(0, 1).equalsIgnoreCase(idShort)) {
                    return language;
               }
          }
          return ENGLISH;
     }

     public int getId() {
          return id;
     }

     public String getName() {
          return name;
     }

     public File getFile() {
          return file;
     }

     public Configuration getCfg() {
          return cfg;
     }
}
