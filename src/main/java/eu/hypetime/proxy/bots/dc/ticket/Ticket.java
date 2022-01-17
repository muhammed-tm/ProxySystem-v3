package eu.hypetime.proxy.bots.dc.ticket;

import eu.hypetime.proxy.bots.dc.DiscordBot;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.Button;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class Ticket {

     private final String userID;
     private final String channelID;
     private final ArrayList<String> messages = new ArrayList<>();
     private Member supporter;
     private String create;

     public Ticket(ButtonClickEvent event) {
          EmbedBuilder builder = new EmbedBuilder();
          builder.setColor(Color.YELLOW);
          builder.setTitle("Support Claim");
          builder.addField("Bitte drücke auf den Knopf zum claimen des Tickets.", "(Nur für Teammitglieder).", true);
          MessageBuilder mBuilder = new MessageBuilder();
          mBuilder.setEmbed(builder.build());
          mBuilder.setActionRows(ActionRow.of(Button.success("claimticket", "Ticket claimen")));
          TextChannel channel = event.getGuild().createTextChannel("ticket-" + event.getUser().getName(), event.getGuild().getCategoryById("693213387634638891")).complete();
          channel.sendMessage(mBuilder.build()).queue();
          this.userID = event.getUser().getId();
          this.channelID = channel.getId();
          SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
          this.create = sdf.format(new Date(System.currentTimeMillis()));
          getChannel().upsertPermissionOverride(Objects.requireNonNull(event.getMember()))
               .setAllow(Permission.MESSAGE_WRITE)
               .setAllow(Permission.VIEW_CHANNEL)
               .setAllow(Permission.MESSAGE_READ).queue();
          Role role = event.getGuild().getRoleById(717408268678594591L);
          getChannel().upsertPermissionOverride(role)
               .setAllow(Permission.MESSAGE_WRITE)
               .setAllow(Permission.VIEW_CHANNEL)
               .setAllow(Permission.MESSAGE_READ).queue();
          TicketManager.addTicket(this);
          if (!event.getMember().isOwner()) {
               if(!event.getMember().getId().equalsIgnoreCase("367292204248727553") && !event.getMember().getId().equalsIgnoreCase("367292204248727553")) {
                    channel.sendMessage(event.getUser().getAsMention() + " | <@&717408268678594591>").queue();
               } else {
                    channel.sendMessage("Dies ist ein Test Ticket, bitte nicht **bearbeiten**.").queue();
               }
          } else {
               channel.sendMessage("Dies ist ein Test Ticket, bitte nicht **bearbeiten**.").queue();
          }
          /*for (ProxiedPlayer player : ProxySystem.getInstance().getProxy().getPlayers()) {
               if (player.hasPermission("proxysystem.team")) {
                    player.sendMessage("§6Team §8» §aDiscord§8: §7Der User §6" + event.getUser().getName() + "§7 hat ein Ticket eröffnet§8.");
               }
          }*/
     }

     public String getUserID() {
          return userID;
     }

     public String getChannelID() {
          return channelID;
     }

     public TextChannel getChannel() {
          return DiscordBot.jda.getTextChannelById(channelID);
     }

     public String getCreate() {
          return create;
     }

     public ArrayList<String> getMessages() {
          return messages;
     }

     public Member getSupporter() {
          return supporter;
     }

     public void setSupporterID(Member member, Message message) {
          this.supporter = member;
          message.delete().queue();
          EmbedBuilder builder = new EmbedBuilder();
          builder.setTitle("Ticket");
          builder.addField("Ticket wird bearbeitet von:", member.getEffectiveName(), true);
          builder.setColor(Color.YELLOW);
          MessageBuilder messageBuilder = new MessageBuilder();
          messageBuilder.setEmbed(builder.build());
          getChannel().sendMessage(messageBuilder.build()).queue();
     }
}
