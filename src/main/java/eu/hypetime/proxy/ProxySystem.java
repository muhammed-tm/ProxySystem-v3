package eu.hypetime.proxy;

import eu.hypetime.proxy.bots.dc.DiscordBot;
import eu.hypetime.proxy.database.MySQL;
import eu.hypetime.proxy.database.MySQLConfig;
import eu.hypetime.proxy.utils.FileManager;
import net.md_5.bungee.api.plugin.Plugin;

/*
    Created by Andre
    At 13:46 Uhr | 02. Jan.. 2022
    Project ProxySystem-v3
*/
public class ProxySystem extends Plugin {

     private static ProxySystem instance;
     private FileManager fileManager;
     private MySQL mySQL;

     public static ProxySystem getInstance() {
          return instance;
     }

     @Override
     public void onEnable() {
          instance = this;
          fileManager = new FileManager(this);
          MySQLConfig mySQLConfig = new MySQLConfig(fileManager);
          mySQL = mySQLConfig.mySQL;
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
}
