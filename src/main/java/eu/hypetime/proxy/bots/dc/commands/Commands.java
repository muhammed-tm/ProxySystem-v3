package eu.hypetime.proxy.bots.dc.commands;

import eu.hypetime.proxy.bots.dc.DiscordBot;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.Button;
import net.md_5.bungee.api.ProxyServer;

import java.awt.*;
import java.util.Objects;

public class Commands extends ListenerAdapter {

     @Override
     public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
          if (event.getAuthor().isBot()) {
               return;
          }
          String message = event.getMessage().getContentRaw();
          if (message.length() >= 3) {
               if (!message.startsWith(DiscordBot.prefix)) {
                    return;
               }
               String[] args = message.replace(DiscordBot.prefix, "").split(" ");
               String command = args[0];

               if (command.equalsIgnoreCase("top5")) {
                    if (args.length != 2) {
                         event.getChannel().sendMessage("Liste der Top 5 Spieler in GunBattle:").queue();
                         /*if (SQLStats.getInstance().listSize() >= 5) {
                              for (int i = 0; i < 5; i++) {
                                   String name = SQLStats.getInstance().getPlayerFromRank(i);
                                   event.getChannel().sendMessage("Rank: " + (i + 1) +
                                        " | Spieler: " + name).queue();
                              }
                         }*/
                    }
               }
               if (command.equalsIgnoreCase("status")) {
                    if (args.length != 2) {
                         EmbedBuilder embedBuilder = new EmbedBuilder();
                         embedBuilder.setTitle("HypeTime ist aktuell Online!");
                         embedBuilder.setDescription("**hypetime.eu**");
                         embedBuilder.setDescription("**Spieler:** " + ProxyServer.getInstance().getOnlineCount());
                         embedBuilder.setFooter("Hosted by WhatTheHost");
                         event.getChannel().sendMessage(embedBuilder.build()).queue();
                    }
               }

               if (event.getMember().hasPermission(Permission.ADMINISTRATOR)) {
                    if (command.equalsIgnoreCase("warn")) {
                         if (args.length != 2) {
                              event.getChannel().sendMessage("Bitte nutze ht!warn @User").queue();
                              return;
                         }
                         Member member = event.getMessage().getMentionedMembers().get(0);
                         checkMember(event.getGuild(), member);
                    }
                    if (command.equalsIgnoreCase("add")) {

                         if (!event.getChannel().getName().contains("ticket-")) {
                              event.getChannel().sendMessage("Bitte stelle sicher, dass dies in einem Apply geschrieben wird.").queue();

                              return;
                         }
                         if (args.length != 2) {
                              event.getChannel().sendMessage("Bitte stelle sicher, dass die ID des Benutzers richtig eingegeben wurde.").queue();
                              return;
                         }
                         Member member = event.getGuild().retrieveMemberById(args[1]).complete();
                         if (member == null) {
                              event.getChannel().sendMessage("Der Benutzer mit der ID existiert nicht.").queue();
                              return;
                         }
                         event.getChannel().getManager().putPermissionOverride(member, 3072L, 8192L).queue();
                         event.getChannel().sendMessage("Der Benutzer " + member.getAsMention() + " wurde hinzugefWügt.").queue();
                    }
                    if (command.equalsIgnoreCase("remove")) {

                         if (!event.getChannel().getName().contains("ticket-")) {
                              event.getChannel().sendMessage("Bitte stelle sicher, dass dies in einem Ticket geschrieben wird.").queue();

                              return;
                         }
                         if (args.length != 2) {
                              event.getChannel().sendMessage("Bitte stelle sicher, dass die ID des Benutzers richtig eingegeben wurde.").queue();
                              return;
                         }
                         Member member = event.getGuild().retrieveMemberById(args[1]).complete();
                         if (member == null) {
                              event.getChannel().sendMessage("Der Benutzer mit der ID existiert nicht.").queue();
                              return;
                         }
                         event.getChannel().getManager().putPermissionOverride(member, 0L, 1024L).queue();
                         event.getChannel().sendMessage("Der Benutzer " + member.getAsMention() + " wurde entfernt.").queue();
                    }
                    if (command.equalsIgnoreCase("help")) {
                         if (args.length > 1) {
                              event.getChannel().sendMessage("Bitte nutze ht!help").queue();
                              return;
                         }
                         event.getChannel().sendMessage("Seite 1").queue(message1 -> message1.addReaction(Emoji.fromUnicode(":arrow_forward:").toString()).queue());
                    }

                    if (command.equalsIgnoreCase("clear")) {
                         if (args.length != 2) {
                              event.getChannel().sendMessage("Bitte nutze ht!clear Zeilen").queue();
                              return;
                         }
                         try {
                              for (Message message1 : event.getChannel().getHistory().retrievePast(Integer.parseInt(args[1]) + 1).complete()) {
                                   message1.delete().queue();
                              }
                              event.getChannel().sendMessage("Es wurden " + args[1] + " Zeilen gelöscht.").queue();
                         } catch (NumberFormatException ignored) {
                              event.getChannel().sendMessage("Bitte geben eine Nummer an").queue();
                         }
                    }

                    if (command.equalsIgnoreCase("restart")) {
                         event.getChannel().sendMessage("Die Proxy wird neugestartet.").queue();
                         ProxyServer.getInstance().stop(
                                   "§8§m                                       \n"+
                                   "§6§lHypeTimeEU §7is restarting§8."+
                                   "§8§m                                       \n");
                    }
               }

          }
     }

     @Override
     public void onGuildMessageReactionAdd(GuildMessageReactionAddEvent event) {
          Message message = event.getChannel().retrieveMessageById(event.getMessageId()).complete();

          if (event.getUser().isBot()) return;
          if (event.getReactionEmote().getAsReactionCode().equals("??")) {
               message.editMessage("Seite 2").queue();
               event.getReaction().clearReactions().queue();
               message.addReaction("??").queue();
          } else if (event.getReactionEmote().getAsReactionCode().equals("??")) {
               message.editMessage("Seite 1").queue();
               event.getReaction().clearReactions().queue();
               message.addReaction("??").queue();
          }
     }

     public void checkMember(Guild guild, Member member) {
          Role warn1 = guild.getRoleById("693213378537324544");
          Role warn2 = guild.getRoleById("693213379170664548");
          Role warn3 = guild.getRoleById("693213380017651835");
          if (member != null) {
               if (member.getRoles().contains(warn1)) {
                    guild.removeRoleFromMember(member, Objects.requireNonNull(warn1)).queue();
                    guild.addRoleToMember(member, Objects.requireNonNull(warn2)).queue();
                    guild.getTextChannelById("771081440473514047").sendMessage("Der Spieler " + member.getAsMention() + " wurde verwarnt.").queue();
                    return;
               } else if (member.getRoles().contains(warn2)) {
                    guild.removeRoleFromMember(member, Objects.requireNonNull(warn2)).queue();
                    guild.addRoleToMember(member, Objects.requireNonNull(warn3)).queue();
                    guild.getTextChannelById("771081440473514047")
                         .sendMessage("Der Spieler " + member.getAsMention() + " wurde das 3 mal verwarnt. Bitte denke dran ihn zu bannen.").queue();

                    return;
               }
               if (!member.getRoles().contains(warn1) && !member.getRoles().contains(warn2) && !member.getRoles().contains(warn3)) {
                    guild.addRoleToMember(member, Objects.requireNonNull(warn1)).queue();
                    guild.getTextChannelById("771081440473514047").sendMessage("Der Spieler " + member.getAsMention() + " wurde verwarnt.").queue();
               }
          }
     }
}