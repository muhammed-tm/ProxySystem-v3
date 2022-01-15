package eu.hypetime.proxy.bots.dc.listener;

import eu.hypetime.proxy.bots.dc.DiscordBot;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.interaction.SelectionMenuEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.selections.SelectOption;
import net.dv8tion.jda.api.interactions.components.selections.SelectionMenu;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class TicketRatingListener extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;
        if (event.getMember().getId().equals("367292204248727553")
                || event.getMember().isOwner() || event.getChannel().getParent().getId().equalsIgnoreCase("693213391724216321")) {
            if (event.getMessage().getContentDisplay().contains("htb!rating")) {
                MessageBuilder builder = new MessageBuilder();
                EmbedBuilder embed = new EmbedBuilder();
                embed.setTitle("**Wie hat dir der Ticket-Support gefallen?**");
                embed.addField("", "Hey <:rocket:931635869465579620>\n" +
                        "Vielen Dank für deinen Besuch im Ticket-Support. Wir würden uns freuen, wenn du uns eine kurze Bewertung hinterlassen würdest, damit wir unseren Support weiter verbessern können.\n\nGrüße,\nHypeTime Team ✨", false);
                builder.setEmbed(embed.build()).build();
                builder.setActionRows(ActionRow.of(SelectionMenu.create("rating")
                        .addOption("⭐ 5 Sterne", "5star")
                        .addOption("⭐ 4 Sterne", "4star")
                        .addOption("⭐ 3 Sterne", "3star")
                        .addOption("⭐ 2 Sterne", "2star")
                        .addOption("⭐ 1 Stern", "1star").build()));
                event.getChannel().sendMessage(builder.build()).queue();
            }
        }
    }

    @Override
    public void onSelectionMenu(@NotNull SelectionMenuEvent event) {
        if (event.getSelectionMenu() == null) return;
        if (event.getSelectionMenu().getId() == null) return;
        SelectionMenu menu = event.getSelectionMenu();
        Member member = event.getMember();
        if (menu.getId().equalsIgnoreCase("rating"))
            if (event.getSelectedOptions().size() < 1) return;
        SelectOption option = event.getSelectedOptions().get(0);
        EmbedBuilder thanks5a4 = new EmbedBuilder();
        thanks5a4.setColor(Color.GREEN);
        thanks5a4.setTitle("Support Bewertung");
        thanks5a4.addField("Wir danken dir für deine Bewerbung. ", option.getLabel(), false);
        MessageBuilder builder = new MessageBuilder();
        builder.setEmbed(thanks5a4.build());
        if (option.getValue().equalsIgnoreCase("5star")) {
            DiscordBot.jda.getTextChannelById("931631956515495996").sendMessage(member.getEffectiveName() + " hat eine **5 Sterne** Bewertung da gelassen, für den Ticket Support. ```⭐⭐⭐⭐⭐``` ").queue();
            event.getMessage().delete().queue();
            event.getChannel().sendMessage(builder.build()).queue();

        }
        if (option.getValue().equalsIgnoreCase("4star")) {
            DiscordBot.jda.getTextChannelById("931631956515495996").sendMessage(member.getEffectiveName() + " hat eine **4 Sterne** Bewertung da gelassen, für den Ticket Support. ```⭐⭐⭐⭐``` ").queue();
            event.getMessage().delete().queue();
            event.getChannel().sendMessage(builder.build()).queue();

        }
        EmbedBuilder threetwoone = new EmbedBuilder();
        threetwoone.setColor(Color.YELLOW);
        threetwoone.setTitle("Support Bewertung");
        threetwoone.addField("Vielen Dank für die Bewertung.", "" + option.getLabel(), false);
        threetwoone.addField("Schade das der Support dir nicht so gefallen hat.",
                "Bei Vorschlägen bitte den <#693213433696485426> Kanal nutzen.", false);
        MessageBuilder threetwoonemb = new MessageBuilder();
        threetwoonemb.setEmbed(threetwoone.build());
        Message threetwoonem = threetwoonemb.build();
        if (option.getValue().equalsIgnoreCase("3star")) {
            DiscordBot.jda.getTextChannelById("931631956515495996").sendMessage(member.getEffectiveName() + " hat eine **3 Sterne** Bewertung da gelassen, für den Ticket Support. ```⭐⭐⭐``` ").queue();
            event.getMessage().delete().queue();
            event.getChannel().sendMessage(threetwoonem).queue();
        }
        if (option.getValue().equalsIgnoreCase("2star")) {
            DiscordBot.jda.getTextChannelById("931631956515495996").sendMessage(member.getEffectiveName() + " hat eine **2 Sterne** Bewertung da gelassen, für den Ticket Support. ```⭐⭐``` ").queue();
            event.getMessage().delete().queue();
        event.getChannel().sendMessage(threetwoonem).queue();;
        }
        if (option.getValue().equalsIgnoreCase("1star")) {
            DiscordBot.jda.getTextChannelById("931631956515495996").sendMessage(member.getEffectiveName() + " hat eine **1 Sterne** Bewertung da gelassen, für den Ticket Support. ```⭐``` ").queue();
            event.getMessage().delete().queue();
            event.getChannel().sendMessage(threetwoonem).queue();
        }
    }
}