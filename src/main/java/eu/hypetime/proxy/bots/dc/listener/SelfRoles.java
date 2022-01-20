package eu.hypetime.proxy.bots.dc.listener;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.SelectionMenuEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.selections.SelectOption;
import net.dv8tion.jda.api.interactions.components.selections.SelectionMenu;
import org.jetbrains.annotations.NotNull;

public class SelfRoles extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;
        if (event.getMember().getId().equals("367292204248727553")
                || event.getMember().isOwner()) {
            if (event.getMessage().getContentDisplay().contains("htb!roles")) {
                MessageBuilder builder = new MessageBuilder();
                EmbedBuilder embed = new EmbedBuilder();
                embed.setTitle("Self Roles - Pings", "https://cdn.discordapp.com/attachments/826890041183043584/840690828502630410/HypeTimeLogoclean1wei.png");
                embed.addField("Auswahl", "Bitte wähle die Rolle aus die du haben möchtest!", false);
                builder.setEmbed(embed.build()).build();
                builder.setActionRows(ActionRow.of(SelectionMenu.create("pings")
                        .addOption("Giveaway", "ga")
                        .addOption("Announcement", "an")
                        .addOption("Alle entfernen", "da").build()));
                event.getChannel().sendMessage(builder.build()).queue();
            }
        }
    }

    @Override
    public void onSelectionMenu(@NotNull SelectionMenuEvent event) {
        if(event.getSelectionMenu() == null) return;
        if(event.getSelectionMenu().getId() == null) return;
        SelectionMenu menu = event.getSelectionMenu();
        Member member = event.getMember();
        Role announce = event.getGuild().getRoleById("773510776031805440");
        Role giveaway = event.getGuild().getRoleById("773514638574747648");
        if(menu.getId().equalsIgnoreCase("pings")) {
            if(event.getSelectedOptions().size() < 1) return;
            SelectOption option = event.getSelectedOptions().get(0);
            if(option.getValue().equalsIgnoreCase("ga")) {
                if(member.getRoles().contains(giveaway)) {
                    event.getGuild().removeRoleFromMember(member, giveaway).queue();
                    event.reply("Rolle rem " + giveaway.getName()).queue();
                } else {
                    event.getGuild().addRoleToMember(member, giveaway).queue();
                    event.reply("Rolle add " + giveaway.getName()).queue();
                }
            }
            if(option.getValue().equalsIgnoreCase("an")) {
                if(member.getRoles().contains(announce)) {
                    event.getGuild().removeRoleFromMember(member, announce).queue();
                    event.reply("Rolle rem " + announce.getName()).queue();
                } else {
                    event.getGuild().addRoleToMember(member, announce).queue();
                    event.reply("Rolle add " + announce.getName()).queue();
                }
            }
        }
    }
}
