package eu.hypetime.proxy.bots.dc.listener;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;


public class Suggestion extends ListenerAdapter {

     @Override
     public void onGuildMessageReactionAdd(GuildMessageReactionAddEvent event) {
          if (event.getUser().isBot()) return;
          if (event.getChannel().getId().equalsIgnoreCase("931950724995235870")) {
               try {
                    Message message = event.getChannel().retrieveMessageById(event.getMessageId()).complete();
                    if (message != null) {
                         if (event.getReactionEmote().getAsReactionCode().equals("🟢") || event.getReactionEmote().getAsReactionCode().equals("🔴")) {
                              if (!event.getMember().hasPermission(Permission.ADMINISTRATOR)) {
                                   event.getReaction().removeReaction(event.getUser()).queue();
                                   return;
                              }
                         }
                         if (message.getAuthor() == event.getUser()) {
                              event.getReaction().removeReaction(event.getUser()).queue();
                         }
                    } else {
                         System.out.print("Message = null");
                    }

               } catch (NullPointerException ignored) {
               }
          }
     }
}
