package eu.hypetime.proxy.ban;

import eu.hypetime.proxy.ProxySystem;
import eu.hypetime.proxy.utils.UUIDFetcher;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

import java.util.UUID;

/*
    Created by Andre
    At 21:43 Uhr | 20. Jan.. 2022
    Project ProxySystem-v3
*/
public class BanCommand extends Command {
     public BanCommand() {
          super("ban", "system.ban", "verbannen");
     }

     @Override
     public void execute(CommandSender sender, String[] args) {
          if(args.length == 2) {
               try {
                    BanReasons reason = BanReasons.getReason(Integer.parseInt(args[1]));
                    if(reason == BanReasons.NOT_DEFINED) {
                         sender.sendMessage("Ungültige Ban ID");
                         sendBanReasons(sender);
                         return;
                    }
                    BanManager.ban(UUIDFetcher.getUUID(args[0]), reason, sender);
               } catch(NumberFormatException exception) {
                    sender.sendMessage("§7Bitte nutze /ban <Spieler> <ID(Grund)>");
                    sendBanReasons(sender);
               }
          } else {
               sender.sendMessage("§7Bitte nutze /ban <Spieler> <ID(Grund)>");
               sendBanReasons(sender);
          }
     }

     public void sendBanReasons(CommandSender sender) {
          sender.sendMessage("§8§m          §r§8[§6BanSystem§8]§m          ");
          for (BanReasons reason : BanReasons.values()) {
               if(reason.getId() != 100) {
                    sender.sendMessage("§7Reason§8: §6" + reason.getName() + " §8| §7Id§8: §6" + reason.getId());
               }
          }
          sender.sendMessage("§8§m          §r§8[§6BanSystem§8]§m          ");
     }
}
