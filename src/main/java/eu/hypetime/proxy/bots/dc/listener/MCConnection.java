package eu.hypetime.proxy.bots.dc.listener;

import eu.hypetime.proxy.bots.dc.DiscordBot;
import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;
import java.text.SimpleDateFormat;

/*
    Created by Andre
    At 19:54 Uhr | 16. Juni. 2021
    Project HypeCloud
*/
public class MCConnection {

     public MCConnection(String userName, String uuid, String ip, String type) {
          if (type.equalsIgnoreCase("join")) {
               EmbedBuilder builder = new EmbedBuilder();
               builder.setColor(Color.GREEN);
               builder.setTitle("[+] " + userName);
               builder.addField("UUID:", uuid, true);
               builder.addField("IP: ", ip, true);

               SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

               builder.setFooter("Betreten: " + sdf.format(System.currentTimeMillis()));

               DiscordBot.jda.getGuildById("693086914491842570").getTextChannelById("786243505319051284").sendMessage(builder.build()).queue();
          } else {
               EmbedBuilder builder = new EmbedBuilder();
               builder.setColor(Color.RED);
               builder.setTitle("[-] " + userName);
               builder.addField("UUID:", uuid, true);
               builder.addField("IP: ", ip, true);

               SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

               builder.setFooter("Verlassen: " + sdf.format(System.currentTimeMillis()));

               DiscordBot.jda.getGuildById("693086914491842570").getTextChannelById("786243505319051284").sendMessage(builder.build()).queue();
          }
     }
}
