package eu.hypetime.proxy;

import eu.hypetime.proxy.bots.dc.DiscordBot;
import eu.hypetime.proxy.database.MySQL;
import eu.hypetime.proxy.database.MySQLConfig;
import eu.hypetime.proxy.events.JoinQuit;
import eu.hypetime.proxy.utils.FileManager;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;

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

     public static ProxySystem getInstance() {
          return instance;
     }

     @Override
     public void onEnable() {
          instance = this;
          fileManager = new FileManager(this);
          MySQLConfig mySQLConfig = new MySQLConfig(fileManager);
          mySQL = mySQLConfig.mySQL;

          registerMessage();
          DiscordBot.start();

          registerListener();
     }

     @Override
     public void onDisable() {

     }

     public void registerListener() {
          PluginManager pm = ProxyServer.getInstance().getPluginManager();
          pm.registerListener(this, new JoinQuit());
     }

     public void registerMessage() {
          System.out.println("Start loading messages");
          english = fileManager.createNewFile("english.yml", getDataFolder().getAbsolutePath() + "/messages");
          german = fileManager.createNewFile("german.yml", getDataFolder().getAbsolutePath() + "/messages");
          englishCfg = fileManager.getConfiguration("english.yml", getDataFolder().getAbsolutePath() + "/messages");
          germanCfg = fileManager.getConfiguration("german.yml", getDataFolder().getAbsolutePath() + "/messages");

          //DEFAULT
          //Prefix

          englishCfg.set("prefix", "&6Proxy &8| &7");
          germanCfg.set("prefix", "&6Bungee &8| &7");

          //Not Found
          englishCfg.set("msgNotFound", "Message was not found");
          germanCfg.set("msgNotFound", "Nachricht wurde nicht gefunden");

          //Command not Found
          englishCfg.set("cmdNotFound", "This command was not found in our system");
          germanCfg.set("cmdNotFound", "Der Befehl wurden nicht gefunden");

          try {
               ConfigurationProvider.getProvider(YamlConfiguration.class).save(englishCfg, english);
               ConfigurationProvider.getProvider(YamlConfiguration.class).save(germanCfg, german);
          } catch (IOException e) {
               e.printStackTrace();
          }


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

}
