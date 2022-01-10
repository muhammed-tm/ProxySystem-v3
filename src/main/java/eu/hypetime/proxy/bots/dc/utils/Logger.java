package eu.hypetime.proxy.bots.dc.utils;

import java.text.SimpleDateFormat;

public class Logger {

     public static void info(String message) {
          SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy | HH:mm:ss");
          String formatted = dateFormat.format(System.currentTimeMillis());
          System.out.println("[" + formatted + "] INFO: " + message);
     }

     public static void warning(String message) {
          SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy | HH:mm:ss");
          String formatted = dateFormat.format(System.currentTimeMillis());
          System.out.println("[" + formatted + "] WARNING: " + message);
     }

     public static void error(String message) {
          SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy | HH:mm:ss");
          String formatted = dateFormat.format(System.currentTimeMillis());
          System.out.println("[" + formatted + "] ERROR: " + message);
     }
}
