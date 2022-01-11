package eu.hypetime.proxy.party;

import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.ArrayList;
import java.util.HashMap;

/*
    Created by Andre
    At 23:40 Uhr | 10. Jan.. 2022
    Project ProxySystem-v3
*/
public class Party {

     public static HashMap<ProxiedPlayer, Party> partys = new HashMap<>();
     private final ArrayList<ProxiedPlayer> members = new ArrayList<>();
     private ProxiedPlayer leader;

     public Party(ProxiedPlayer player) {
          this.leader = player;

          partys.put(player, this);
     }

     public static Party getPartyByLeader(ProxiedPlayer partyLeader) {
          if (partys.get(partyLeader) != null) {
               return partys.get(partyLeader);
          } else {
               return new Party(partyLeader);
          }
     }

     public ProxiedPlayer getLeader() {
          return leader;
     }

     public void setLeader(ProxiedPlayer player) {
          partys.get(this.leader).leader = player;
     }

     public ArrayList<ProxiedPlayer> getMembers() {
          return members;
     }
}
