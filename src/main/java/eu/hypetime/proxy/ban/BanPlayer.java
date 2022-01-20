package eu.hypetime.proxy.ban;

/*
    Created by Andre
    At 21:20 Uhr | 20. Jan.. 2022
    Project ProxySystem-v3
*/
public class BanPlayer {

     public String name;
     public String reason;
     public long end;
     public String banner;

     public BanPlayer(String name, BanReasons reason, String banner) {
          this.name = name;
          this.reason = reason.getName();
          this.end = reason.getTime();
          this.banner = banner;
     }

     public String getName() {
          return name;
     }

     public String getReason() {
          return reason;
     }

     public long getEnd() {
          return end;
     }

     public String getBanner() {
          return banner;
     }
}
