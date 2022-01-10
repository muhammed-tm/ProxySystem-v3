/*
 *  Created by quele | Muhammed
 *  Copyright (C) all rights reserved.
 *  Website: http://quele.live
 */

package eu.hypetime.proxy.bots.dc.commands.verifysystem;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class VerifySystemCommand extends ListenerAdapter {

    @Override

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        Message message = event.getMessage();

        String[] args = event.getMessage().getContentRaw().split(" ");
        if (args.length == 0) {
            if (message.getContentRaw().startsWith("!verify")) {

            }
        }
    }
}
