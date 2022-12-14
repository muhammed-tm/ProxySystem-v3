package eu.hypetime.proxy.bots.dc.ticket;

import eu.hypetime.proxy.bots.dc.DiscordBot;
import eu.hypetime.proxy.bots.dc.ticket.Ticket;
import eu.hypetime.proxy.bots.dc.ticket.TicketManager;
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
import java.text.SimpleDateFormat;
import java.util.Date;

public class TicketRatingListener extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;
        if (event.getMember().getId().equals("367292204248727553")
                || event.getMember().isOwner() || event.getChannel().getParent().getId().equalsIgnoreCase("693213391724216321")) {
            if (event.getMessage().getContentDisplay().contains("htb!rating")) {
                MessageBuilder builder = new MessageBuilder();
                EmbedBuilder embed = new EmbedBuilder();
                if (event.getMessage().getContentDisplay().contains("de")) {
                    event.getMessage().delete().queue();
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

                } else if (event.getMessage().getContentDisplay().contains("en")) {
                    event.getMessage().delete().queue();
                    embed.setTitle("**How did you like the ticket support?**");
                    embed.addField("", "Hey <:rocket:931635869465579620>\n" +
                            "Thank you for visiting Ticket Support. We would be happy if you would leave us a short review, so that we can further improve our support.\n\ngreetings,\nHypeTime Team ✨", false);
                    builder.setEmbed(embed.build()).build();
                    builder.setActionRows(ActionRow.of(SelectionMenu.create("rating")
                            .addOption("⭐ 5 Sterne", "5star")
                            .addOption("⭐ 4 Sterne", "4star")
                            .addOption("⭐ 3 Sterne", "3star")
                            .addOption("⭐ 2 Sterne", "2star")
                            .addOption("⭐ 1 Stern", "1star").build()));
                    event.getChannel().sendMessage(builder.build()).queue();

                } else {
                    event.getMessage().delete().queue();
                    embed.setTitle("**How did you like the ticket support?**");
                    embed.addField("", "Hey <:rocket:931635869465579620>\n" +
                            "Thank you for visiting Ticket Support. We would be happy if you would leave us a short review, so that we can further improve our support.\n\ngreetings,\nHypeTime Team ✨", false);
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
        thanks5a4.addField("Wir danken dir für deine Bewertung. ", option.getLabel(), false);
        MessageBuilder builder = new MessageBuilder();
        builder.setEmbed(thanks5a4.build());
        if (option.getValue().equalsIgnoreCase("5star")) {
            //DiscordBot.jda.getTextChannelById("931631956515495996").sendMessage(member.getEffectiveName() + " hat eine **5 Sterne** Bewertung da gelassen, für den Ticket Support. ```⭐⭐⭐⭐⭐``` ").queue();
            event.getMessage().delete().queue();
            event.getChannel().sendMessage(builder.build()).queue();

        }
        if (option.getValue().equalsIgnoreCase("4star")) {
            //DiscordBot.jda.getTextChannelById("931631956515495996").sendMessage(member.getEffectiveName() + " hat eine **4 Sterne** Bewertung da gelassen, für den Ticket Support. ```⭐⭐⭐⭐``` ").queue();
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
            //DiscordBot.jda.getTextChannelById("931631956515495996").sendMessage(member.getEffectiveName() + " hat eine **3 Sterne** Bewertung da gelassen, für den Ticket Support. ```⭐⭐⭐``` ").queue();
            event.getMessage().delete().queue();
            event.getChannel().sendMessage(threetwoonem).queue();
        }
        if (option.getValue().equalsIgnoreCase("2star")) {
            //DiscordBot.jda.getTextChannelById("931631956515495996").sendMessage(member.getEffectiveName() + " hat eine **2 Sterne** Bewertung da gelassen, für den Ticket Support. ```⭐⭐``` ").queue();
            event.getMessage().delete().queue();
            event.getChannel().sendMessage(threetwoonem).queue();
        }
        if (option.getValue().equalsIgnoreCase("1star")) {
            //DiscordBot.jda.getTextChannelById("931631956515495996").sendMessage(member.getEffectiveName() + " hat eine **1 Sterne** Bewertung da gelassen, für den Ticket Support. ```⭐``` ").queue();
            event.getMessage().delete().queue();
            event.getChannel().sendMessage(threetwoonem).queue();
        }
        Ticket ticket = TicketManager.getTicketByChannel(event.getChannel().getId());
        if(ticket != null) {
            EmbedBuilder ratingBuilder = new EmbedBuilder();
            ratingBuilder.setTitle("Rating");
            ratingBuilder.addField("Ticket von:", event.getGuild().getMemberById(ticket.getUserID()).getEffectiveName(), false);
            ratingBuilder.addField("Bewertung: ", option.getLabel(), false);
            ratingBuilder.addField("Bearbeiter: ", ticket.getSupporter().getEffectiveName(), false);
            ratingBuilder.addField("Erstellt: ", ticket.getCreate(), false);
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
            ratingBuilder.setFooter("Gesendet: " + sdf.format(new Date(System.currentTimeMillis())), "https://images.hypetime.eu/images/2022/01/15/HypeTimeLogoclean.png");
            ratingBuilder.setColor(Color.YELLOW);
            MessageBuilder ratingMessageBuilder = new MessageBuilder();
            ratingMessageBuilder.setEmbed(ratingBuilder.build());
            DiscordBot.jda.getTextChannelById("931631956515495996").sendMessage(ratingMessageBuilder.build()).queue();
        }
    }
}