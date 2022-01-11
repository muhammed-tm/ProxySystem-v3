/*
 *  Created by quele | Muhammed
 *  Copyright (C) all rights reserved.
 *  Website: http://quele.live
 */

package eu.hypetime.proxy.bots.dc.commands.betasystem;

import eu.hypetime.proxy.ProxySystem;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class BetaSystemCommands extends ListenerAdapter {

     private final List<UUID> uuids = new ArrayList<>();

     @Override
     public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
          Role betarole = event.getGuild().getRoleById("740342244044832819");

          if (event.getAuthor().isBot()) {
               return;
          }
          if (!event.getChannel().getId().equals("873990473990164591")) {
               return;
          }
          if (event.getAuthor().isBot()) {
               event.getMessage().delete().queueAfter(10L, TimeUnit.SECONDS);
               return;
          }
          event.getMessage().delete().queue();
          Message message = event.getMessage();
          if (!message.getContentRaw().startsWith("!beta")) {
               message.getChannel().sendMessage("!beta <Player-UUID> <KEY>").queue(message1 -> {
                    message1.delete().queueAfter(5, TimeUnit.SECONDS);

               });
               return;
          }
          String[] args = message.getContentRaw().split(" ");
          if (args.length != 3) {
               message.getChannel().sendMessage("!beta <Player-UUID> <KEY>").queue(message1 -> {
                    message1.delete().queueAfter(5, TimeUnit.SECONDS);
               });
               return;
          }
          UUID uuid = UUID.fromString(args[1]);
          if (isUUIDForKey(uuid.toString())) {
               message.getChannel().sendMessage("Dieser Spieler ist bereits im Betaprogramm.").queue(message1 -> {
                    message1.delete().queueAfter(5, TimeUnit.SECONDS);
               });
               return;
          }
          String key = args[2];
          if (!checkKey(key)) {
               message.getChannel().sendMessage("Ungültiger Key.").queue(message1 -> {
                    message1.delete().queueAfter(5, TimeUnit.SECONDS);
               });
               return;
          }
          //ProxySystem.getInstance().getMySQL().update("UPDATE `proxy_BetaSystem` SET `uuid`='" + uuid + "' WHERE `betakey`='" + key + "'");

          event.getGuild().addRoleToMember(event.getMember(), betarole).queue();
          message.getAuthor().openPrivateChannel().complete().sendMessage("""
               Du wurdest erfolgreich zum Betaprogramm hinzugefügt.
               Ab Freitag dem 20.08.21 um 18:00 Uhr kannst du auf das HypeTime.eu Netzwerk joinen.
               Viel Spaß auf unserem Netzwerk""").queue();

          message.getChannel().sendMessage("""
               Du wurdest erfolgreich zum Betaprogramm hinzugefügt.
               Ab Freitag dem 20.08.21 um 18:00 Uhr kannst du auf das HypeTime.eu Netzwerk joinen.
                Viel Spaß auf unserem Netzwerk""").queue(message1 -> message1.delete().queueAfter(25, TimeUnit.SECONDS));
     }

     private boolean checkKey(String key) {
         /* ResultSet resultSet = ProxySystem.getInstance().getMySQL().getResult("SELECT * FROM `proxy_BetaSystem` WHERE `betakey`='" + key + "'");
          if (resultSet == null)
               return false;
          try {
               if (resultSet.next()) {
                    String uuid = resultSet.getString("uuid");
                    System.out.println(uuid);
                    return uuid.equals("INVALID");
               } else {
                    return false;
               }
          } catch (SQLException exception) {
               exception.printStackTrace();
          }*/
          return false;
     }

     private boolean isUUIDForKey(String uuid) {
         /* ResultSet resultSet = ProxySystem.getInstance().getMySQL().getResult("SELECT * FROM `proxy_BetaSystem` WHERE `uuid`='" + uuid + "'");
          if (resultSet == null)
               return false;
          try {
               return resultSet.next();
          } catch (SQLException exception) {
               exception.printStackTrace();
          }*/
          return false;
     }
}
