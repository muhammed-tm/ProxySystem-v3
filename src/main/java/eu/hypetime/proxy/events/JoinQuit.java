package eu.hypetime.proxy.events;

import eu.hypetime.proxy.ProxySystem;
import eu.hypetime.proxy.ban.BanManager;
import eu.hypetime.proxy.lang.LanguageManager;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.UUID;

/*
    Created by Andre
    At 20:05 Uhr | 11. Jan.. 2022
    Project ProxySystem-v3
*/
public class JoinQuit implements Listener {

     @EventHandler
     public void onJoin(PostLoginEvent event) {
          ProxiedPlayer player = event.getPlayer();
          BanManager banManager = ProxySystem.getInstance().getBanManager();
          UUID uuid = player.getUniqueId();
          if (banManager.isBanned(uuid)) {
               player.disconnect("§8==========§cBan§8==========\n"
                    + "§7Du wurdest vom Netzwerk gesperrt.\n"
                    + "§7Grund§8: §6" + banManager.getReason(uuid) + "\n"
                    + "§7Verbleibende Zeit§8: " + banManager.getReamainingTime(uuid) + "\n"
                    + "§7Gebannt von§8: §6" + banManager.getBanner(uuid) + "\n"
                    + "§8==========§cBan§8==========");
          }
          LanguageManager.register(player);
          LanguageManager.sendMessage(player, "join");
     }


     @EventHandler
     public void onQuit(PlayerDisconnectEvent event) {
          LanguageManager.save(event.getPlayer());
     }
}
