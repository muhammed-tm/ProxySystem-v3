package eu.hypetime.proxy.events;

import eu.hypetime.proxy.lang.LanguageManager;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

/*
    Created by Andre
    At 20:05 Uhr | 11. Jan.. 2022
    Project ProxySystem-v3
*/
public class JoinQuit implements Listener {

     @EventHandler
     public void onJoin(PostLoginEvent event) {
          LanguageManager.register(event.getPlayer());
          LanguageManager.sendMessage(event.getPlayer(), "join");
     }

     @EventHandler
     public void onQuit(PlayerDisconnectEvent event) {
          LanguageManager.save(event.getPlayer());
     }
}
