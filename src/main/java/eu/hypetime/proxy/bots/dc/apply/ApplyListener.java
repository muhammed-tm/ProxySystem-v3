package eu.hypetime.proxy.bots.dc.apply;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.Button;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class ApplyListener extends ListenerAdapter {

     @Override
     public void onButtonClick(ButtonClickEvent event) {
          if (event.getUser().isBot()) return;
          if (event.getButton().getId().equalsIgnoreCase("apply") && event.getChannel().getId().equals("833688139511758908")) {
               event.deferReply(true).queue(response -> {
                    Role role = event.getGuild().getRoleById(793602273954496532L);
                    if (!event.getMember().getRoles().contains(role)) {
                         if (ApplyManager.getTicket(event.getUser().getId()) == null) {
                              response.editOriginal(":white_check_mark: Dein Apply wird erstellt...").queue();
                         } else if (event.getGuild().getTextChannelsByName("apply-" + event.getUser().getName(), true).size() == 0) {
                              response.editOriginal(":white_check_mark: Dein Apply wird erstellt...").queue();
                         } else {
                              response.editOriginal("❌ Du hast bereits ein Apply offen!").queue();
                         }
                    } else {
                         response.editOriginal("❌ Du hast einen Support Bann!").queue();
                    }
               });
               Role role = event.getGuild().getRoleById(793602273954496532L);
               if (!event.getMember().getRoles().contains(role)) {
                    if (ApplyManager.getTicket(event.getUser().getId()) == null) {
                         new Apply(event);
                    } else if (event.getGuild().getTextChannelsByName("apply-" + event.getUser().getName(), true).size() == 0) {
                         new Apply(event);
                    } else {
                         if (ApplyManager.getTicket(event.getUser().getId()) != null) {
                              ApplyManager.getTicket(event.getUser().getId()).getChannel().sendMessage(event.getUser().getAsMention()).queue();

                         } else {
                              TextChannel textChannel = event.getGuild().getTextChannelsByName("apply-" + event.getUser().getName(), true).get(0);
                              textChannel.sendMessage(event.getUser().getAsMention()).queue();
                         }
                    }
               }
          }
     }

     @Override
     public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
          if (event.getMember() == null) return;
          if (event.getMember().getUser().isBot() || event.getMember().equals(event.getGuild().getSelfMember())) return;
          if (event.getChannel().getName().contains("apply-") && !event.getChannel().getName().equals("apply-support")) {
               if(ApplyManager.getTicketByChannel(event.getChannel().getId()) != null) {
                    Apply apply = ApplyManager.getTicketByChannel(event.getChannel().getId());
                    apply.getMessages().add(event.getAuthor().getAsTag() + " : " + event.getMessage().getContentRaw());
               }
               if (event.getMessage().getContentDisplay().equalsIgnoreCase("ht!close")) {
                    if (event.getChannel().getParent().getId().equalsIgnoreCase("693213391724216321")) {
                         Role role = event.getGuild().getRoleById(717408268678594591L);
                         if (event.getMember().getRoles().contains(role)) {
                              if (ApplyManager.getTicketByChannel(event.getChannel().getId()) != null) {
                                   ApplyManager.closeTicket(event.getChannel().getId());
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
                         } else if (ApplyManager.getTicket(event.getMember().getUser().getId()) != null
                              && ApplyManager.getTicket(event.getMember().getUser().getId()).getChannel() != null) {
                              if (ApplyManager.getTicket(event.getMember().getUser().getId()).getChannel().equals(event.getChannel())) {
                                   ApplyManager.closeTicket(event.getChannel().getId());
                              }
                         } else {
                              if (event.getChannel().getName().replace("apply-", "").equalsIgnoreCase(event.getMember().getUser().getName())) {
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
                         event.getChannel().sendMessage("Das Apply in 20 Sekunden entgültig gelöscht.").queue();
                         event.getChannel().delete().queueAfter(20, TimeUnit.SECONDS);
                    }
               }
          } else if (event.getMember().getUser().getId().equals("567694483647627294") && event.getMessage().getContentDisplay().equalsIgnoreCase("-apply")) {
               event.getMessage().delete().queue();
               EmbedBuilder builder = new EmbedBuilder();
               builder.setTitle("Apply-Support");
               builder.setColor(Color.YELLOW);
               builder.setDescription("Drücke den Knopf um ein Apply zu eröffnen.");
               builder.setAuthor(event.getGuild().getSelfMember().getEffectiveName(), event.getGuild().getIconUrl());
               event.getGuild().getTextChannelById("833688139511758908").sendMessage(builder.build()).setActionRow(Button.success("appl", "🎫️ Apply öffnen")).queue();
          }
     }
}
