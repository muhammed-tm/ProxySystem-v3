package eu.hypetime.proxy.bots.dc;

import eu.hypetime.proxy.bots.dc.commands.Commands;
import eu.hypetime.proxy.bots.dc.commands.betasystem.BetaSystemCommands;
import eu.hypetime.proxy.bots.dc.commands.stats.top5Command;
import eu.hypetime.proxy.bots.dc.commands.verifysystem.VerifySystemCommand;
import eu.hypetime.proxy.bots.dc.listener.*;
import eu.hypetime.proxy.bots.dc.listener.betasystem.KeyGenerationMessageListener;
import eu.hypetime.proxy.bots.dc.ticket.TicketListener;
import eu.hypetime.proxy.bots.dc.utils.Logger;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import javax.security.auth.login.LoginException;
import java.awt.*;

public class DiscordBot extends ListenerAdapter {

    public static JDA jda;
    public static String prefix = "htb!";

    public static void start() {
        try {
            jda = JDABuilder.createDefault("OTMwNTg3OTI5MzIyMDk0NTkz.Yd4Diw.E04gLo9MW7UaZT-23zjH94r5aHs").build();

            jda.getPresence().setActivity(Activity.playing("HypeTime.eu"));

            registerListener(new Commands());
            registerListener(new OwnChannel());
            registerListener(new Suggestion());
            registerListener(new ChatFilter());
            registerListener(new TicketListener());
            registerListener(new JoinListener());
            registerListener(new DiscordBot());
            registerListener(new SelfRoles());
            registerListener(new RulesListener());
            registerListener(new KeyGenerationMessageListener());
            registerListener(new BetaSystemCommands());
            registerListener(new VerifySystemCommand());
            registerListener(new top5Command());

            jda.getPresence().setActivity(Activity.playing("HypeTime v3"));
            jda.getPresence().setStatus(OnlineStatus.DO_NOT_DISTURB);

            Logger.info("Erfolgreich gestartet");
        } catch (LoginException exception) {
            Logger.error("Discord bot konnte nicht gestartet werden." +
                    " Es ist ein Fehler aufgetreten." +
                    " Fehlermeldung: " + exception.getMessage());
        }
    }

    public static void registerListener(Object object) {
        jda.addEventListener(object);
        Logger.info("Register Listener " + object.getClass().getSimpleName());
    }

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(Color.GREEN);
        builder.setTitle("HypeTime Beta Proxy");
        builder.addField("Status:", "Online", true);

        DiscordBot.jda.getGuildById("693086914491842570").getTextChannelById("786243505319051284").sendMessage(builder.build()).queue();
    }


}
