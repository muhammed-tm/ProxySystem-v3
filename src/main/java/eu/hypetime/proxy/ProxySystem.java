package eu.hypetime.proxy;

import eu.hypetime.proxy.bots.dc.DiscordBot;
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
          DiscordBot.start();
     }

     @Override
     public void onDisable() {

     }

     public static ProxySystem getInstance() {
          return instance;
     }
}
