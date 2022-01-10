package eu.hypetime.proxy.bots.dc.ticket;

import net.dv8tion.jda.api.entities.TextChannel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TicketManager {

     private static final List<Ticket> tickets = new ArrayList<>();

     public static Ticket getTicket(String userID) {
          for (Ticket ticket : getTickets()) {
               if (ticket.getUserID().equalsIgnoreCase(userID)) {
                    return ticket;
               }
          }
          return null;
     }

     public static Ticket getTicketByChannel(String channelID) {
          for (Ticket ticket : getTickets()) {
               if (ticket.getChannelID().equalsIgnoreCase(channelID)) {
                    return ticket;
               }
          }
          return null;
     }

     public static List<Ticket> getTickets() {
          return tickets;
     }

     public static void removeTicket(Ticket ticket) {
          if (ticket != null) {
               if (ticket.getChannel() != null) {
                    TextChannel channel = ticket.getChannel().getGuild().createTextChannel(ticket.getChannel().getName(), ticket.getChannel().getGuild().getCategoryById("826068031193088030")).complete();
                    ticket.getMessages().forEach(s -> {
                         String[] messageSplit = s.split(" : ");
                         channel.sendMessage(messageSplit[0] + ": " + messageSplit[1]).queue();
                    });
                    ticket.getChannel().sendMessage("Der Channel wird in 20 Sekunden archiviert.").queue();
                    ticket.getChannel().delete().queueAfter(20, TimeUnit.SECONDS);
                    tickets.remove(ticket);
               }
          }
     }

     public static void addTicket(Ticket ticket) {
          tickets.add(ticket);
     }

     public static void closeTicket(String channelID) {
          Ticket ticket = getTicketByChannel(channelID);
          if (ticket != null) {
               removeTicket(ticket);
          }
     }
}
