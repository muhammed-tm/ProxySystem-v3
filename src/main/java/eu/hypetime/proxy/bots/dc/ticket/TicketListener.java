package eu.hypetime.proxy.bots.dc.ticket;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.Button;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class TicketListener extends ListenerAdapter {

     @Override
     public void onButtonClick(ButtonClickEvent event) {
          if (event.getUser().isBot()) return;
          if (event.getButton().getId().equalsIgnoreCase("ticket1")
                  && event.getChannel().getId().equals("833688139511758908")) {
               event.deferReply(true).queue(response -> {
                    Role role = event.getGuild().getRoleById(793602273954496532L);
                    if (!event.getMember().getRoles().contains(role)) {
                         if (TicketManager.getTicket(event.getUser().getId()) == null) {
                              response.editOriginal(":white_check_mark: Dein Ticket wird erstellt...").queue();
                         } else if (event.getGuild().getTextChannelsByName("ticket-" + event.getUser().getName(), true).size() == 0) {
                              response.editOriginal(":white_check_mark: Dein Ticket wird erstellt...").queue();
                         } else {
                              response.editOriginal("❌ Du hast bereits ein Ticket offen!").queue();
                         }
                    } else {
                         response.editOriginal("❌ Du hast einen Support Bann!").queue();
                    }
               });
               Role role = event.getGuild().getRoleById(793602273954496532L);
               if (!event.getMember().getRoles().contains(role)) {
                   if (TicketManager.getTicket(event.getUser().getId()) == null) {
                         new Ticket(event);
                    } else if (event.getGuild().getTextChannelsByName("ticket-" + event.getUser().getName(), true).size() == 0) {
                         new Ticket(event);
                    } else {
                         if (TicketManager.getTicket(event.getUser().getId()) != null) {
                              TicketManager.getTicket(event.getUser().getId()).getChannel().sendMessage(event.getUser().getAsMention()).queue();

                         } else {
                              TextChannel textChannel = event.getGuild().getTextChannelsByName("ticket-" + event.getUser().getName(), true).get(0);
                              textChannel.sendMessage(event.getUser().getAsMention()).queue();
                         }
                    }
               }
          } else if(event.getButton().getId().contains("claimticket")) {
               Role role = event.getGuild().getRoleById(717408268678594591L);
               if(event.getMember().getRoles().contains(role)) {
                    TicketManager.getTicketByChannel(event.getChannel().getId()).setSupporterID(event.getMember(), event.getMessage());
               }
          }
     }

     @Override
     public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
          if (event.getMember() == null) return;
          if (event.getMember().getUser().isBot() || event.getMember().equals(event.getGuild().getSelfMember())) return;
          if (event.getChannel().getName().contains("ticket-") && !event.getChannel().getName().equals("ticket-support")) {
               if(TicketManager.getTicketByChannel(event.getChannel().getId()) != null) {
                    Ticket ticket = TicketManager.getTicketByChannel(event.getChannel().getId());
                    ticket.getMessages().add(event.getAuthor().getAsTag() + " : " + event.getMessage().getContentRaw());
               }
               if (event.getMessage().getContentDisplay().equalsIgnoreCase("htb!close")) {
                    if (event.getChannel().getParent().getId().equalsIgnoreCase("693213391724216321")) {
                         Role role = event.getGuild().getRoleById(717408268678594591L);
                         if (event.getMember().getRoles().contains(role)) {
                              if (TicketManager.getTicketByChannel(event.getChannel().getId()) != null) {
                                   TicketManager.closeTicket(event.getChannel().getId());
                              } else {
                                   if (event.getChannel() != null) {
                                        event.getChannel().sendMessage("Der Channel wird in 20 Sekunden archviert.").queue();
                                        TextChannel channel = event.getGuild().createTextChannel(event.getChannel().getName() + " (Beta)", event.getGuild().getCategoryById("826068031193088030")).complete();
                                        event.getChannel().getHistory().getRetrievedHistory().forEach(message -> channel.sendMessage(message.getAuthor().getAsTag() + ": " + message.getContentRaw()).queue());
                                        if(event.getChannel() != null) {
                                             event.getChannel().delete().queueAfter(20, TimeUnit.SECONDS);
                                        }
                                   }
                              }
                         } else if (TicketManager.getTicket(event.getMember().getUser().getId()) != null
                              && TicketManager.getTicket(event.getMember().getUser().getId()).getChannel() != null) {
                              if (TicketManager.getTicket(event.getMember().getUser().getId()).getChannel().equals(event.getChannel())) {
                                   TicketManager.closeTicket(event.getChannel().getId());
                              }
                         } else {
                              if (event.getChannel().getName().replace("ticket-", "").equalsIgnoreCase(event.getMember().getUser().getName())) {
                                   if (event.getChannel() != null) {
                                        event.getChannel().sendMessage("Der Channel wird in 20 Sekunden archviert.").queue();
                                        TextChannel channel = event.getGuild().createTextChannel(event.getChannel().getName() + " (Beta)", event.getGuild().getCategoryById("826068031193088030")).complete();
                                        event.getChannel().getHistory().getRetrievedHistory().forEach(message -> channel.sendMessage(message.getAuthor().getAsTag() + ": " + message.getContentRaw()).queue());
                                        if(event.getChannel() != null) {
                                             event.getChannel().delete().queueAfter(20, TimeUnit.SECONDS);
                                        }
                                   }
                              }
                         }
                    } else {
                         event.getChannel().sendMessage("Das Ticket in 20 Sekunden entgültig gelöscht.").queue();
                         event.getChannel().delete().queueAfter(20, TimeUnit.SECONDS);
                    }
               }
          } else if (event.getMember().getUser().getId().equals("367292204248727553")
                  && event.getMessage().getContentDisplay().equalsIgnoreCase("ht!ticket")) {
               event.getMessage().delete().queue();
               EmbedBuilder builder = new EmbedBuilder();
               builder.setTitle("Ticket-Support");
               builder.setColor(Color.YELLOW);
               builder.setDescription("Drücke den Knopf um ein Ticket zu eröffnen.");
               builder.setAuthor(event.getGuild().getSelfMember().getEffectiveName(), event.getGuild().getIconUrl());
               event.getGuild().getTextChannelById("833688139511758908")
                       .sendMessage(builder.build()).setActionRow(Button.success("ticket1", "🎫️ Ticket öffnen")).queue();
          }
     }
}
