/*
 *  Created by quele | Muhammed
 *  Copyright (C) all rights reserved.
 *  Website: http://quele.live
 */

package eu.hypetime.proxy.database;

import eu.hypetime.proxy.ProxySystem;
import eu.hypetime.proxy.utils.FileManager;
import net.md_5.bungee.config.Configuration;

import java.io.File;

public class MySQLConfig {

     public FileManager fileManager;
     public Configuration configuration;
     public MySQL mySQL;

     public MySQLConfig(FileManager fileManager) {
          this.fileManager = fileManager;
          File file = fileManager.createNewFile("mysql.yml", ProxySystem.getInstance().getDataFolder().getAbsolutePath());
          configuration = fileManager.getConfiguration("mysql.yml", ProxySystem.getInstance().getDataFolder().getAbsolutePath());
          if (configuration == null) {
               System.out.println("Config ist null");
               return;
          }
          if (configuration.get("host") == null) {
               configuration.set("host", "185.194.236.246");
          }
          if (configuration.get("username") == null) {
               configuration.set("username", "muhammedtuerkmen_minecraft");
          }
          if (configuration.get("password") == null) {
               configuration.set("password", "a73n0bX&");
          }
          if (configuration.get("database") == null) {
               configuration.set("database", "muhammedtuerkmen_minecraft");
          }

          fileManager.saveFile(file, configuration);
          mySQL = new MySQL(configuration.getString("host"), configuration.getString("username"),
               configuration.getString("password"), configuration.getString("database"));
     }
}
