package eu.hypetime.proxy.bots.dc.listener;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

public class JoinListener extends ListenerAdapter {

     @Override
     public void onGuildMemberJoin(GuildMemberJoinEvent event) {
          EmbedBuilder builder = new EmbedBuilder();
          builder.setTitle("HypeTime Discord", "https://www.hypetime.eu/");
          builder.addField("<:Pfeil:773176356699373588> Willkommen", "Herzlich Willkommen auf dem HypeTime Discord Server.", false);
          builder.addField("Regeln", "> Im <#693213422518796338> Kanal musst du die Regeln lesen und akzeptieren, damit du den Discord Server nutzen kannst :wink:", false);
          builder.addField("Support", "> In <#717408277255684242> kannst du ein Apply eröffnen, bei Fragen oder Probleme", false);
          builder.setFooter("Fall into the hype");

          builder.setColor(Color.ORANGE);
          event.getMember().getUser().openPrivateChannel().queue(privateChannel -> {
               privateChannel.sendMessageEmbeds(builder.build()).queue();
          });
     }


}
