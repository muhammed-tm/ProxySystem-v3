package eu.hypetime.proxy.party;

import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

/*
    Created by Andre
    At 23:40 Uhr | 10. Jan.. 2022
    Project ProxySystem-v3
*/
public class Party {

     public static HashMap<ProxiedPlayer, Party> partyHashMap = new HashMap<>();
     private final ArrayList<ProxiedPlayer> members = new ArrayList<>();
     private ProxiedPlayer leader;

     public Party(ProxiedPlayer player) {
          this.leader = player;

          partyHashMap.put(player, this);
     }

     public static Party getPartyByLeader(ProxiedPlayer partyLeader) {
          if (partyHashMap.get(partyLeader) != null) {
               return partyHashMap.get(partyLeader);
          } else {
               return new Party(partyLeader);
          }
     }

     public ProxiedPlayer getLeader() {
          return leader;
     }

     public void randomizePartyLeader(ProxiedPlayer player) {
          setLeader(partyHashMap.get(player).getMembers().get(ThreadLocalRandom.current().nextInt(0, partyHashMap.get(player).getMembers().size())));
     }

     public void setLeader(ProxiedPlayer player) {
          partyHashMap.get(this.leader).leader = player;
     }

     public ArrayList<ProxiedPlayer> getMembers() {
          return members;
     }
}
