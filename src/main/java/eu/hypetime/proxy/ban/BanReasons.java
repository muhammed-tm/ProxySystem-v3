package eu.hypetime.proxy.ban;

import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

/*
    Created by Andre
    At 19:25 Uhr | 11. Jan.. 2022
    Project ProxySystem-v3
*/
public enum BanReasons {

     HACKING(1, "Cheating", -1, ""),
     ADMIN(98, "Admin", -1, "proxysystem.ban.admin"),
     OTHER(99, "Other", 1, ""),
     NOT_DEFINED(100, "Not defined", -1, "drh69rhihfkghnkfgnfgjoikfj");

     private final int id;
     private final String name;
     private final long time;
     private final String permission;

     BanReasons(int id, String name, long time, String permission) {
          this.id = id;
          this.name = name;
          this.time = time;
          this.permission = permission;
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

     public String getPermission() {
          if(permission.equalsIgnoreCase("")) return "proxysystem.ban";
          return permission;
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
