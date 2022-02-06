package eu.hypetime.proxy.party;

import eu.hypetime.proxy.ProxySystem;
import eu.hypetime.proxy.lang.Language;
import eu.hypetime.proxy.lang.LanguageManager;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.HashMap;
import java.util.concurrent.Executors;

/*
    Created by Andre
    At 02:31 Uhr | 06. Feb.. 2022
    Project ProxySystem-v3
*/
public class PartyManager {

     public static HashMap<Party, Integer> partyCountdown = new HashMap<>();

     public static void addMember(ProxiedPlayer member, ProxiedPlayer leader) {
          Party.getPartyByLeader(leader).getMembers().add(member);
     }

     public static void removeMember(ProxiedPlayer member, ProxiedPlayer leader) {
          Party.getPartyByLeader(leader).getMembers().remove(member);
     }

     public static void startCountdown() {
          ProxySystem.getInstance().getExecutors().execute(() -> {
               partyCountdown.forEach((party, integer) -> {
                    if(integer >= 1) {
                         if(party.getMembers().size() == 0) {
                              int newCount = ((integer) - (1));
                              partyCountdown.replace(party, integer, newCount);
                         } else {
                              partyCountdown.remove(party);
                         }
                    } else if(integer == 0) {
                         if(Language.getLanguage(LanguageManager.getLanguage(party.getLeader())) == Language.ENGLISH) {
                              party.getLeader().sendMessage(ProxySystem.getInstance().getPrefix() + "Your Party has been closed with the reason not enough players.");
                         } else{
                              party.getLeader().sendMessage(ProxySystem.getInstance().getPrefix() + "§7Deine Party wird aufgrund von zu wenig Spielern aufgelöst.");
                         }
                         Party.partyHashMap.remove(party.getLeader(), party);
                         partyCountdown.remove(party);
                    }
               });
               try {
                    Thread.sleep(1000);
               } catch (InterruptedException e) {
                    e.printStackTrace();
               }
          });
     }
}
