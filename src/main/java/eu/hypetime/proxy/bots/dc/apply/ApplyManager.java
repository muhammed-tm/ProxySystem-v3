package eu.hypetime.proxy.bots.dc.apply;

import net.dv8tion.jda.api.entities.TextChannel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ApplyManager {

     private static final List<Apply> APPLIES = new ArrayList<>();

     public static HashMap<Apply, Integer> steps = new HashMap<>();

     public static Apply getTicket(String userID) {
          for (Apply apply : getTickets()) {
               if (apply.getUserID().equalsIgnoreCase(userID)) {
                    return apply;
               }
          }
          return null;
     }

     public static Apply getTicketByChannel(String channelID) {
          for (Apply apply : getTickets()) {
               if (apply.getChannelID().equalsIgnoreCase(channelID)) {
                    return apply;
               }
          }
          return null;
     }

     public static List<Apply> getTickets() {
          return APPLIES;
     }

     public static void removeTicket(Apply apply) {
          if (apply != null) {
               if (apply.getChannel() != null) {
                    TextChannel channel = apply.getChannel().getGuild().createTextChannel(apply.getChannel().getName(), apply.getChannel().getGuild().getCategoryById("826068031193088030")).complete();
                    apply.getMessages().forEach(s -> {
                         String[] messageSplit = s.split(" : ");
                         channel.sendMessage(messageSplit[0] + ": " + messageSplit[1]).queue();
                    });
                    apply.getChannel().sendMessage("Der Channel wird in 20 Sekunden archiviert.").queue();
                    apply.getChannel().delete().queueAfter(20, TimeUnit.SECONDS);
                    APPLIES.remove(apply);
               }
          }
     }

     public static void addTicket(Apply apply) {
          APPLIES.add(apply);
     }

     public static void closeTicket(String channelID) {
          Apply apply = getTicketByChannel(channelID);
          if (apply != null) {
               removeTicket(apply);
          }
     }

     public static Integer getIntegerByTicket(Apply apply) {

          return steps.get(apply);
     }
}
