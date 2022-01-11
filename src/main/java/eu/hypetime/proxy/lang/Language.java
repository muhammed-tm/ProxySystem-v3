package eu.hypetime.proxy.lang;

import eu.hypetime.proxy.ProxySystem;
import eu.hypetime.proxy.utils.FileManager;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.config.Configuration;

import java.io.File;

/*
    Created by Andre
    At 21:19 Uhr | 11. Jan.. 2022
    Project ProxySystem-v3
*/
public enum Language {

     ENGLISH(0, ProxySystem.getInstance().getEnglish(), ProxySystem.getInstance().getEnglishCfg()),
     GERMAN(1, ProxySystem.getInstance().getGerman(), ProxySystem.getInstance().getGermanCfg());

     private final int id;
     private final File file;
     private final Configuration cfg;

     Language(int id, File file, Configuration cfg) {
          this.id = id;
          this.file = file;
          this.cfg = cfg;
     }

     public int getId() {
          return id;
     }

     public File getFile() {
          return file;
     }

     public Configuration getCfg() {
          return cfg;
     }

     public static Language getLanguage(int lang) {
          for (Language language : Language.values()) {
               if(language.getId() == lang) {
                    return language;
               }
          }
          return ENGLISH;
     }

     public static Configuration getConfig(ProxiedPlayer player) {
          return getLanguage(LanguageManager.getLanguage(player)).getCfg();
     }
}
