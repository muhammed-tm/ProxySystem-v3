package eu.hypetime.proxy.utils;

/*
 *  Created by quele | Muhammed
 *  Copyright (C) all rights reserved.
 *  Website: http://quele.live
 */

import eu.hypetime.proxy.ProxySystem;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileManager {

     ProxySystem proxySystem;

     public FileManager(ProxySystem proxySystem) {
          this.proxySystem = proxySystem;

     }

     public static String getFileContents(String filenmame) {
          try {
               BufferedReader bufferedReader = new BufferedReader(new FileReader(ProxySystem.getInstance().getDataFolder() + "/website/" + filenmame));
               StringBuilder stringBuilder = new StringBuilder();
               String line = bufferedReader.readLine();
               while (line != null) {
                    stringBuilder.append(line);
                    stringBuilder.append(System.lineSeparator());
                    line = bufferedReader.readLine();
               }
               return stringBuilder.toString();
          } catch (Exception exception) {
               exception.printStackTrace();
          }
          return "<h1>Error!</h1>";
     }

     public static String getLogContents() {
          try {
               BufferedReader bufferedReader = new BufferedReader(new FileReader("logs//proxy.log.0"));
               StringBuilder end = new StringBuilder();
               String line = bufferedReader.readLine();
               while (line != null) {
                    end.insert(0, line + "<br>");
                    line = bufferedReader.readLine();
               }
               return end.toString();
          } catch (IOException | NullPointerException exception) {
               exception.printStackTrace();
          }
          return "<h1>Error!</h1>";

     }

     public File createNewFile(String filename, String path) {
          File f = new File(path, filename);
          File d = new File(path);
          if (!d.exists()) {
               d.mkdirs();
          }
          if (!f.exists()) {
               try {
                    f.createNewFile();
                    return f;
               } catch (IOException e) {
                    e.printStackTrace();
               }
          }
          return f;
     }

     public File getFile(String filename, String path) {
          return new File(path, filename);
     }

     public boolean exists(String filename, String path) {
          return getFile(filename, path).exists();
     }

     public void deleteFile(String filename, String path) {
          File f = new File(path, filename);
          if (f.exists()) {
               f.delete();
          }
     }

     public Configuration getConfiguration(String filename, String path) {
          try {
               return ConfigurationProvider.getProvider(YamlConfiguration.class).load(getFile(filename, path));
          } catch (IOException e) {
               e.printStackTrace();
          }
          return null;
     }

     public void saveFile(File file, Configuration cfg) {
          try {
               ConfigurationProvider.getProvider(YamlConfiguration.class).save(cfg, file);
          } catch (IOException e) {
               e.printStackTrace();
          }
     }
}