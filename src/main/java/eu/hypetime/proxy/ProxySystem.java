package eu.hypetime.proxy;

import eu.hypetime.proxy.ban.BanCommand;
import eu.hypetime.proxy.ban.BanManager;
import eu.hypetime.proxy.ban.UnbanCommand;
import eu.hypetime.proxy.bots.dc.DiscordBot;
import eu.hypetime.proxy.database.MongoDB;
import eu.hypetime.proxy.database.MySQL;
import eu.hypetime.proxy.database.MySQLConfig;
import eu.hypetime.proxy.events.JoinQuit;
import eu.hypetime.proxy.lang.LangCommand;
import eu.hypetime.proxy.lang.Language;
import eu.hypetime.proxy.lang.LanguageManager;
import eu.hypetime.proxy.utils.FileManager;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
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
     private MongoDB mongoDB;
     private BanManager banManager;

     public static ProxySystem getInstance() {
          return instance;
     }

     @Override
     public void onEnable() {
          instance = this;
          fileManager = new FileManager(this);
          MySQLConfig mySQLConfig = new MySQLConfig(fileManager);
          mySQL = mySQLConfig.mySQL;
          mongoDB = new MongoDB();
          banManager = new BanManager();

          registerMessage();
          DiscordBot.start();

          registerListener();
          registerCommands();
     }

     @Override
     public void onDisable() {

     }

     public void registerListener() {
          PluginManager pm = ProxyServer.getInstance().getPluginManager();
          pm.registerListener(this, new JoinQuit());
     }

     public void registerCommands() {
          PluginManager pm = ProxyServer.getInstance().getPluginManager();
          pm.registerCommand(this, new LangCommand());
          pm.registerCommand(this, new BanCommand());
          pm.registerCommand(this, new UnbanCommand());
     }

     public void registerMessage() {
          System.out.println("!!!REGISTER MESSAGES!!!");

          english = fileManager.createNewFile("english.yml", getDataFolder().getAbsolutePath() + "/messages");
          german = fileManager.createNewFile("german.yml", getDataFolder().getAbsolutePath() + "/messages");
          englishCfg = fileManager.getConfiguration("english.yml", getDataFolder().getAbsolutePath() + "/messages");
          germanCfg = fileManager.getConfiguration("german.yml", getDataFolder().getAbsolutePath() + "/messages");

          //DEFAULT

          //Not Found
          englishCfg.set("msgNotFound", "%prefix% Message was not found");
          germanCfg.set("msgNotFound", "%prefix% Nachricht wurde nicht gefunden");

          //Command not Found
          englishCfg.set("cmdNotFound", "%prefix% This command was not found in our system");
          germanCfg.set("cmdNotFound", "%prefix% Der Befehl wurden nicht gefunden");

          //Change Language
          englishCfg.set("changeSuc", "%prefix% Language was change to %lang%");
          germanCfg.set("changeSuc", "%prefix% Sprache wurde zu %lang% geändert");

          englishCfg.set("changeErr", "%prefix% Please use /lang <de/en/toggle>");
          germanCfg.set("changeErr", "%prefix% Bitte nutze /lang <de/en/toggle>");

          //PERSONALIZED
          //Join

          englishCfg.set("join", "%prefix% Welcome");
          germanCfg.set("join", "%prefix% Willkommen");


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

     public MongoDB getMongoDB() {
          return mongoDB;
     }

     public BanManager getBanManager() {
          return banManager;
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
          return "§6HypeTime §8| §7";
     }

}
