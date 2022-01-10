package eu.hypetime.proxy.bots.dc.listener;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageUpdateEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/*
    Created by Andre
    At 15:19 Uhr | 19. Apr.. 2021
    Project DCBot
*/
public class ChatFilter extends ListenerAdapter {

     public static String[] words = new String[]{"sieg heil", "judenvergasung", "ficken", "hitler", "adolf hitler", "hurensohn", "fotze", "ficker", "missit", "vergaser", "schwuchtel", "cock", "penis"
             , "pimmel", "hure", "hurensoh", "huren", "pisser", "l2p", "l²p", "e²", "geschlechtsverkehr", "wichskrüppel", "arschlochkind", "penisschlumpf", "sperma", "wichs"
             , "sex", "muschi", "vagina", "erektion", "hoden", "möse", "cracknutte", "tripper", "sack", "pisse", "kotze", "vorhaut", "prostata"
             , "schlampe", "luder", "transe", "pissnelke", "motherfucker", "fick", "wichser", "ddos", "asshole", "dumbass", "bitch", "transe", "huso", "titten", "wixer", "wixxer", "wikser", "noob", "noop"
             , "nigger", "e2", "spast", "bastard", "Fvck", "hvere", "wxer", "Gasdusche", "vergasen", "n4b", "nob", "gaskammer", "eZ", "anal", "hs", "nazi", "analplug", "analsex", "arse"
             , "assassin", "balls", "bimbo", "bloody", "bloodyhell", "blowjob", "bollocks", "boner", "boobies", "boobs", "bugger", "bukkake",
             "bullshit", "chink", "clit", "clitoris", "cocksucker", "condom", "kondom", "coon", "crap", "cumshot", "damm", "dammit", "damn", "dickhead",
             "doggystyle", "f0ck", "fags", "fanny", "fck", "fcker", "fckr", "fcku", "fcuk", "fitta", "fitte", "fucker", "fuckface", "fuckr", "fuct", "genital"
             , "genitalia", "genitals", "glory hole", "gloryhole", "gobshite", "godammet", "godammit", "goddammet", "goddammit", "goddamn", "gypo", "hitler", "hooker", "hore"
             , "horny", "jävla", "jesussucks", "jizz", "jizzum", "jævla", "kaffir", "kill", "killer", "killin", "killing", "lesbo", "masturbate", "milf", "molest", "moron"
             , "motherfuck", "mthrfckr", "murder", "murderer", "nazi", "negro", "nigga", "niggah", "nonce", "paedo", "paedophile", "paki", "pecker", "pedo", "pedofile"
             , "pedophile", "phuk", "pig", "pimp", "poof", "porn", "prick", "pron", "prostitute", "raped", "rapes"};

     public static String[] ads = new String[]{".de", ".d e", "[punkt]", "(punkt)", ".dee", "xxx", ".ip", ".biz", "join now", ".pl", ".net", "mein netzwerk", "noip", "c o m", "x x x", "c om", ".com", "www"
             , ".tk", ",de", ",com", ",tk", ",net", "(,)", "(.)", "sytrex", "s y t r e x", "s ytrex", "sy trex", "syt rex", "sytr ex", "sytre x", "sy tre x", "sytr ex", "s ytrex"
             , "syntaxgaming", "syntax", "blauban", "blau ban", "b lauban", "s yntaxgaming", "[.]", ".tv"};

     @Override
     public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
          if (event.getMessage().getAuthor().isBot()) return;
          if (!event.getMember().getRoles().contains(event.getGuild().getRoleById("693213365342044202"))) {
               for (String word : words) {
                    for (String arg : event.getMessage().getContentRaw().split(" ")) {
                         if (arg.equalsIgnoreCase(word)) {
                              Objects.requireNonNull(event.getGuild().getTextChannelById("724649430514401360"))
                                      .sendMessage("Der User " + event.getAuthor().getAsMention()
                                              + " hat ein unerlaubtes Wort geschrieben. Nachricht: " + event.getMessage().getContentRaw()
                                              + " (" + arg + ")").queue();
                              event.getMessage().delete().queue();
                              checkMember(event);
                              return;
                         }
                    }
               }

               for (String domain : ads) {
                    for (String arg : event.getMessage().getContentRaw().split(" ")) {
                         if (arg.contains(domain) && !arg.contains("discordapp") && !arg.contains("tenor") && !arg.contains("youtube")) {
                              Objects.requireNonNull(event.getGuild().getTextChannelById("724649430514401360"))
                                      .sendMessage("Der User " + event.getAuthor().getAsMention()
                                              + " hat eine Domain gesendet. Nachricht: " + event.getMessage().getContentRaw()).queue();
                              event.getMessage().delete().queue();
                              checkMember(event);
                              return;
                         }
                    }
               }
          }

          if (event.getChannel().getId().equalsIgnoreCase("693213409109475359")) {
               if (event.getMessage().getContentRaw().startsWith(".")
                       && event.getMember().hasPermission(Permission.ADMINISTRATOR)) {
                    event.getMessage().delete().queueAfter(1, TimeUnit.SECONDS);
                    if (event.getMessage().getContentRaw().startsWith(".addyes")) {
                         String[] args = event.getMessage().getContentRaw().split(" ");
                         if (args.length != 2) {
                              Message wrongusage = event.getChannel().sendMessage("Falsche nutzung bitte gebe eine ID an").complete();
                              wrongusage.delete().queueAfter(5, TimeUnit.SECONDS);
                              return;
                         }
                         event.getChannel().retrieveMessageById(args[1]).complete().addReaction("🟢").queue();
                         return;
                    } else if (event.getMessage().getContentRaw().startsWith(".addno")) {
                         String[] args = event.getMessage().getContentRaw().split(" ");
                         if (args.length != 2) {
                              Message wrongusage = event.getChannel().sendMessage("Falsche nutzung bitte gebe eine ID an").complete();
                              wrongusage.delete().queueAfter(5, TimeUnit.SECONDS);
                              return;
                         }
                         event.getChannel().retrieveMessageById(args[1]).complete().addReaction("🔴").queue();
                         return;
                    }
               } else if (event.getMessage().getContentRaw().startsWith(".")) {
                    event.getMessage().delete().queue();
                    return;
               }
               event.getMessage().addReaction("👍").queue();
               event.getMessage().addReaction("👎").queue();
          }else if (event.getChannel().getId().equalsIgnoreCase("693213433696485426")) {
               event.getMessage().addReaction("👍").queue();
               event.getMessage().addReaction("👎").queue();

          }else if (event.getChannel().getId().equalsIgnoreCase("739867910876561559")) {
               event.getMessage().addReaction("👍").queue();
               event.getMessage().addReaction("👎").queue();

          }else if (event.getChannel().getId().equalsIgnoreCase("759157055558254634")) {
               event.getMessage().addReaction("👍").queue();
               event.getMessage().addReaction("👎").queue();
          }

     }

     @Override
     public void onGuildMessageUpdate(@NotNull GuildMessageUpdateEvent event) {
          if (event.getMessage().getAuthor().isBot()) return;
          if (!event.getMember().getRoles().contains(event.getGuild().getRoleById("693213365342044202"))) {
               for (String word : words) {
                    for (String arg : event.getMessage().getContentRaw().split(" ")) {
                         if (arg.equalsIgnoreCase(word)) {
                              Objects.requireNonNull(event.getGuild().getTextChannelById("724649430514401360"))
                                   .sendMessage("Der User " + event.getAuthor().getAsMention()
                                        + " hat ein unerlaubtes Wort geschrieben. Nachricht: " + event.getMessage().getContentRaw()
                                        + " (" + arg + ")").queue();
                              event.getMessage().delete().queue();
                              checkMember(event);
                              return;
                         }
                    }
               }

               for (String domain : ads) {
                    for (String arg : event.getMessage().getContentRaw().split(" ")) {
                         if (arg.contains(domain) && !arg.contains("discordapp") && !arg.contains("tenor")) {
                              Objects.requireNonNull(event.getGuild().getTextChannelById("724649430514401360"))
                                   .sendMessage("Der User " + event.getAuthor().getAsMention()
                                        + " hat eine Domain gesendet. Nachricht: " + event.getMessage().getContentRaw()).queue();
                              event.getMessage().delete().queue();
                              checkMember(event);
                              return;
                         }
                    }
               }
          }

          if (event.getChannel().getId().equalsIgnoreCase("693213409109475359")) {
               if (event.getMessage().getContentRaw().startsWith(".")
                    && event.getMember().hasPermission(Permission.ADMINISTRATOR)) {
                    event.getMessage().delete().queueAfter(1, TimeUnit.SECONDS);
                    if (event.getMessage().getContentRaw().startsWith(".addyes")) {
                         String[] args = event.getMessage().getContentRaw().split(" ");
                         if (args.length != 2) {
                              Message wrongusage = event.getChannel().sendMessage("Falsche nutzung bitte gebe eine ID an").complete();
                              wrongusage.delete().queueAfter(5, TimeUnit.SECONDS);
                              return;
                         }
                         event.getChannel().retrieveMessageById(args[1]).complete().addReaction("🟢").queue();
                         return;
                    } else if (event.getMessage().getContentRaw().startsWith(".addno")) {
                         String[] args = event.getMessage().getContentRaw().split(" ");
                         if (args.length != 2) {
                              Message wrongusage = event.getChannel().sendMessage("Falsche nutzung bitte gebe eine ID an").complete();
                              wrongusage.delete().queueAfter(5, TimeUnit.SECONDS);
                              return;
                         }
                         event.getChannel().retrieveMessageById(args[1]).complete().addReaction("🔴").queue();
                         return;
                    }
               } else if (event.getMessage().getContentRaw().startsWith(".")) {
                    event.getMessage().delete().queue();
                    return;
               }
               event.getMessage().addReaction("👍").queue();
               event.getMessage().addReaction("👎").queue();
          }
     }

     public void checkMember(GuildMessageReceivedEvent event) {
          Member member = event.getMember();
          Role warn1 = event.getGuild().getRoleById("693213378537324544");
          Role warn2 = event.getGuild().getRoleById("693213379170664548");
          Role warn3 = event.getGuild().getRoleById("693213380017651835");
          Role modleitung = event.getGuild().getRoleById("804806677890727977");
          if (member != null) {
               if (member.getRoles().contains(warn1)) {
                    event.getGuild().removeRoleFromMember(member, Objects.requireNonNull(warn1)).queue();
                    event.getGuild().addRoleToMember(member, Objects.requireNonNull(warn2)).queue();
                    return;
               } else if (member.getRoles().contains(warn2)) {
                    event.getGuild().removeRoleFromMember(member, Objects.requireNonNull(warn2)).queue();
                    event.getGuild().addRoleToMember(member, Objects.requireNonNull(warn3)).queue();
                    return;
               } else if (member.getRoles().contains(warn3)) {
                    assert modleitung != null;
                    Objects.requireNonNull(event.getGuild().getTextChannelById("833688139511758908"))
                            .sendMessage(modleitung.getAsMention() + ". Achtung der Spieler " + event.getAuthor().getAsMention() + "  hat nun mehr als drei Verwarnungen!").queue();
                    return;
               }
               if (!member.getRoles().contains(warn1) && !member.getRoles().contains(warn2) && !member.getRoles().contains(warn3)) {
                    event.getGuild().addRoleToMember(member, Objects.requireNonNull(warn1)).queue();
               }
          }
     }

     public void checkMember(GuildMessageUpdateEvent event) {
          Member member = event.getMember();
          Role warn1 = event.getGuild().getRoleById("693213378537324544");
          Role warn2 = event.getGuild().getRoleById("693213379170664548");
          Role warn3 = event.getGuild().getRoleById("693213380017651835");
          Role modleitung = event.getGuild().getRoleById("804806677890727977");
          if (member != null) {
               if (member.getRoles().contains(warn1)) {
                    event.getGuild().removeRoleFromMember(member, Objects.requireNonNull(warn1)).queue();
                    event.getGuild().addRoleToMember(member, Objects.requireNonNull(warn2)).queue();
                    return;
               } else if (member.getRoles().contains(warn2)) {
                    event.getGuild().removeRoleFromMember(member, Objects.requireNonNull(warn2)).queue();
                    event.getGuild().addRoleToMember(member, Objects.requireNonNull(warn3)).queue();
                    return;
               } else if (member.getRoles().contains(warn3)) {
                    assert modleitung != null;
                    Objects.requireNonNull(event.getGuild().getTextChannelById("833688139511758908"))
                         .sendMessage(modleitung.getAsMention() + ". Achtung der Spieler " + event.getAuthor().getAsMention() + "  hat nun mehr als drei Verwarnungen!").queue();
                    return;
               }
               if (!member.getRoles().contains(warn1) && !member.getRoles().contains(warn2) && !member.getRoles().contains(warn3)) {
                    event.getGuild().addRoleToMember(member, Objects.requireNonNull(warn1)).queue();
               }
          }
     }
}