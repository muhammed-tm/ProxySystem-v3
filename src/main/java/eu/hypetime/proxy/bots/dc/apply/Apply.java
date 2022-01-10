package eu.hypetime.proxy.bots.dc.apply;

import eu.hypetime.proxy.bots.dc.DiscordBot;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;

import java.util.ArrayList;
import java.util.Objects;

public class Apply {

     private final String userID;
     private final String channelID;
     private final ArrayList<String> messages = new ArrayList<>();

     public Apply(ButtonClickEvent event) {
          TextChannel channel = event.getGuild().createTextChannel("apply-" + event.getUser().getName(), event.getGuild().getCategoryById("693213387634638891")).complete();
          this.userID = event.getUser().getId();
          this.channelID = channel.getId();
          getChannel().upsertPermissionOverride(Objects.requireNonNull(event.getMember()))
               .setAllow(Permission.MESSAGE_WRITE)
               .setAllow(Permission.VIEW_CHANNEL)
               .setAllow(Permission.MESSAGE_READ).queue();
          Role role = event.getGuild().getRoleById(717408268678594591L);
          getChannel().upsertPermissionOverride(role)
               .setAllow(Permission.MESSAGE_WRITE)
               .setAllow(Permission.VIEW_CHANNEL)
               .setAllow(Permission.MESSAGE_READ).queue();
          ApplyManager.addTicket(this);
          if (!event.getMember().isOwner()) {
               if(!event.getMember().getId().equalsIgnoreCase("367292204248727553") && !event.getMember().getId().equalsIgnoreCase("367292204248727553")) {
                    channel.sendMessage(event.getUser().getAsMention() + " | <@&717408268678594591>").queue();
               } else {
                    channel.sendMessage("Dies ist ein Test Apply, bitte nicht **bearbeiten**.").queue();
               }
          } else {
               channel.sendMessage("Dies ist ein Test Apply, bitte nicht **bearbeiten**.").queue();
          }
          /*for (ProxiedPlayer player : ProxySystem.getInstance().getProxy().getPlayers()) {
               if (player.hasPermission("proxysystem.team")) {
                    player.sendMessage("§6Team §8» §aDiscord§8: §7Der User §6" + event.getUser().getName() + "§7 hat ein Apply eröffnet§8.");
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

     public ArrayList<String> getMessages() {
          return messages;
     }
}
