package eu.hypetime.proxy.bots.dc.commands;

import eu.hypetime.proxy.bots.dc.DiscordBot;
import eu.hypetime.proxy.bots.dc.utils.MessageUtil;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.List;

public abstract class Command extends ListenerAdapter {

    public abstract void executeCommand(String[] args, String command, GuildMessageReceivedEvent e, MessageSender chat);

    public abstract List<String> getAlias();

    public boolean allowsPrivate() {
        return false;
    }

    public boolean authorExclusive() {
        return false;
    }

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        // Checks related to the Event's objects, to prevent concurrency issues.

        if (e.getAuthor().isBot())
            return; // Ignore message if it's not a command or sent by a bot
        MessageSender chat = new MessageSender(e);
        String message = e.getMessage().getContentRaw();
        if (message.length() >= 3) {
            if (!message.startsWith(DiscordBot.prefix)) {
                return;
            }
            String[] args = message.replace(DiscordBot.prefix, "").split(" ");
            String command = args[0];

            try {
                executeCommand(args, command, e, chat);
            } catch (Exception ex) {
                ex.printStackTrace();
                String msg = "User: **" + MessageUtil.userDiscrimSet(e.getAuthor())
                        + "**\nMessage:\n*" + MessageUtil.stripFormatting(e.getMessage().getContentDisplay())
                        + "*\n\nError:```java\n" + ex.getMessage() + "```";
                if (msg.length() <= 2000) {
                    chat.sendPrivateMessageToUser(msg, e.getMember().getUser());
                }

            }
        }
    }

    public static class MessageSender {
        private final GuildMessageReceivedEvent event;

        MessageSender(GuildMessageReceivedEvent event) {
            this.event = event;
        }

        void sendMessage(String msgContent, MessageChannel tChannel) {
            if (tChannel == null) return;
            MessageUtil.sendMessage(msgContent, tChannel);
        }

        public void sendMessage(String msgContent) {
            sendMessage(msgContent, event.getChannel());
        }

        public void sendEmbed(String title, String description) {
            if (event.getGuild().getSelfMember().hasPermission(event.getChannel(), Permission.MESSAGE_EMBED_LINKS)) {
                MessageUtil.sendMessage(new EmbedBuilder().setTitle(title, null).setDescription(description).build(), event.getChannel());
            } else {
                sendMessage("Please give the bot permissions to `EMBED LINKS`.");
            }
        }

        void sendPrivateMessageToUser(String content, User user) {
            user.openPrivateChannel().queue(c -> sendMessage(content, c));
        }
    }
}
