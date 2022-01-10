package eu.hypetime.proxy;

import net.md_5.bungee.api.plugin.Plugin;

/*
    Created by Andre
    At 13:46 Uhr | 02. Jan.. 2022
    Project ProxySystem-v3
*/
public class ProxySystem extends Plugin {

     private static ProxySystem instance;

     @Override
     public void onEnable() {
          instance = this;
     }

     @Override
     public void onDisable() {

     }

     public static ProxySystem getInstance() {
          return instance;
     }
}
