package eu.hypetime.proxy.ban;

/*
    Created by Andre
    At 19:25 Uhr | 11. Jan.. 2022
    Project ProxySystem-v3
*/
public enum BanReasons {

     HACKING(1, "Cheating", -1),
     OTHER(99, "Other", 1),
     NOT_DEFINED(100, "Not defined", -1);

     private int id;
     private String name;
     private long time;

     BanReasons(int id, String name, long time) {
          this.id = id;
          this.name = name;
          this.time = time;
     }

     public String getName() {
          return name;
     }

     public int getId() {
          return id;
     }

     public long getTime() {
          return time;
     }

     public static BanReasons getReason(int id) {
          for (BanReasons reason : BanReasons.values()) {
               if(reason.getId() == id) {
                    return reason;
               }
          }
          return NOT_DEFINED;
     }
}
