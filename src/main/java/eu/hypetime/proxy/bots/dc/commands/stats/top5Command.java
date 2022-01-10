/*
 *  Created by quele | Muhammed
 *  Copyright (C) all rights reserved.
 *  Website: http://quele.live
 */

package eu.hypetime.proxy.bots.dc.commands.stats;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class top5Command extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        Message message = event.getMessage();
        if (message.getContentRaw().startsWith("ht!top5")) {

            message.getChannel().sendMessage("§7Liste der Top §65 §7Spieler in GunBattle§8:");
          /*  if (SQLStats.getInstance().listSize() >= 5) {
                for (int i = 0; i < 6; i++) {
                    String name = SQLStats.getInstance().getPlayerFromRank(i);
                    message.getChannel().sendMessage("§7Rank§8: §6" + (i + 1) +
                            " §8| §7Spieler§8: §6" + name);
                }
            } else {
                for (int i = 0; i < SQLStats.getInstance().listSize(); i++) {
                    String name = SQLStats.getInstance().getPlayerFromRank(i);
                    message.getChannel().sendMessage("§7Rank§8: §6" + (i + 1) +
                            " §8| §7Spieler§8: §6" + name);
                }
            }*/
        }
    }
}

