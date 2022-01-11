package eu.hypetime.proxy;

import eu.hypetime.proxy.bots.dc.DiscordBot;
import eu.hypetime.proxy.database.MySQL;
import eu.hypetime.proxy.database.MySQLConfig;
import eu.hypetime.proxy.utils.FileManager;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;

import java.io.File;

/*
    Created by Andre
    At 13:46 Uhr | 02. Jan.. 2022
    Project ProxySystem-v3
*/
public class ProxySystem extends Plugin {

     private static ProxySystem instance;
     private FileManager fileManager;
     private MySQL mySQL;
     private File english;
     private File german;
     private Configuration englishCfg;
     private Configuration germanCfg;
     private String prefix;

     public static ProxySystem getInstance() {
          return instance;
     }

     @Override
     public void onEnable() {
          instance = this;
          fileManager = new FileManager(this);
          MySQLConfig mySQLConfig = new MySQLConfig(fileManager);
          mySQL = mySQLConfig.mySQL;

          english = fileManager.createNewFile("english.yml", getDataFolder().getAbsolutePath() + "/messages");
          german = fileManager.createNewFile("german.yml", getDataFolder().getAbsolutePath() + "/messages");
          englishCfg = fileManager.getConfiguration("english.yml", getDataFolder().getAbsolutePath() + "/messages");
          germanCfg = fileManager.getConfiguration("german.yml", getDataFolder().getAbsolutePath() + "/messages");

          prefix = "§6HypeTime §8| §7";
          DiscordBot.start();
     }

     @Override
     public void onDisable() {

     }

     public FileManager getFileManager() {
          return fileManager;
     }

     public MySQL getMySQL() {
          return mySQL;
     }

     public File getEnglish() {
          return english;
     }

     public File getGerman() {
          return german;
     }

     public Configuration getEnglishCfg() {
          return englishCfg;
     }

     public Configuration getGermanCfg() {
          return germanCfg;
     }

     public String getPrefix() {
          return prefix;
     }
}
