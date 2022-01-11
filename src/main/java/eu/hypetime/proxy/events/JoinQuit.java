package eu.hypetime.proxy.events;

import eu.hypetime.proxy.lang.LanguageManager;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
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
          ProxiedPlayer player = event.getPlayer();
          LanguageManager.register(player);
          LanguageManager.sendMessage(player, "join");
     }


     @EventHandler
     public void onQuit(PlayerDisconnectEvent event) {
          LanguageManager.save(event.getPlayer());
     }
}
