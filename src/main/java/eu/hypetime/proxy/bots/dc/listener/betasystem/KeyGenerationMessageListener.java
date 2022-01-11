/*
 *  Created by quele | Muhammed
 *  Copyright (C) all rights reserved.
 *  Website: http://quele.live
 */

package eu.hypetime.proxy.bots.dc.listener.betasystem;

import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class KeyGenerationMessageListener extends ListenerAdapter {


    public void onMessageReceived(MessageReceivedEvent event) {
        if (!event.getAuthor().getId().equals("567694483647627294") && !event.getAuthor().getId().equals("367292204248727553"))
            return;
        if (!event.getMessage().getContentRaw().equalsIgnoreCase("!generateKey"))
            return;
        event.getAuthor().openPrivateChannel().complete().sendMessage(generateNewKey(8)).queue();
    }

    private String generateNewKey(int length) {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = (int)(AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString
                    .charAt(index));
        }
        String key = sb.toString();
       // ProxySystem.getInstance().getMySQL().update("INSERT INTO `proxy_BetaSystem` (`betakey`, `uuid`) VALUES('" + key + "', 'INVALID')");
        return key;
    }
}
