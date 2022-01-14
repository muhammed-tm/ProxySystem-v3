package eu.hypetime.proxy.bots.dc.listener;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.selections.SelectionMenu;
import org.jetbrains.annotations.NotNull;

public class SelfRoles extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        if(event.getGuild() == null) return;
        if(event.getMember() == null) return;
        if(event.getAuthor().isBot()) return;
        if(event.getMember().getId().equals("367292204248727553")
            || event.getMember().isOwner()) {
            if(event.getMessage().getContentDisplay().equalsIgnoreCase("htb!roles")) {
                MessageBuilder builder = new MessageBuilder();
                EmbedBuilder embed = new EmbedBuilder();
                embed.setTitle("Self Roles - Pings", "https://cdn.discordapp.com/attachments/826890041183043584/840690828502630410/HypeTimeLogoclean1wei.png");
                embed.addField("", "", false);
                builder.setEmbed(embed.build()).build();
                builder.setActionRows(ActionRow.of(SelectionMenu.create("pings")
                        .addOption("GiveAway", "ga")
                        .addOption("News", "ne").build()));
                event.getChannel().sendMessage(builder.build()).queue();
            }
        }
    }
}
