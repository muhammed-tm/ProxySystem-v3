/*
 *  Created by quele | Muhammed
 *  Copyright (C) all rights reserved.
 *  Website: http://quele.live
 */

package eu.hypetime.proxy.bots.dc.listener;

import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

/*
    Created by Andre
    At 18:19 Uhr | 28. Aug.. 2021
    Project ProxySystem-2-4
*/
public class RulesListener extends ListenerAdapter {

     @Override
     public void onGuildMessageReactionAdd(GuildMessageReactionAddEvent event) {
          if (event.getUser().isBot()) return;
          if (event.getMessageId().equalsIgnoreCase("762710349874593832")) {
               try {
                    if (event.getReactionEmote().getAsReactionCode().equals("✅")) {
                         event.getReaction().removeReaction(event.getUser()).queue();
                         if(!event.getMember().getRoles().contains(event.getGuild().getRoleById("693213372468035604"))) {
                              event.getGuild().addRoleToMember(event.getMember(), event.getGuild().getRoleById("693213372468035604")).queue();
                         } else {
                              event.getGuild().removeRoleFromMember(event.getMember(), event.getGuild().getRoleById("693213372468035604")).queue();
                         }
                    }
               } catch (NullPointerException ignored) {
               }
          }
     }

}
