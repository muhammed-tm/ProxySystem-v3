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
public class UnbanCommand extends Command {
     public UnbanCommand() {
          super("unban", "system.unban");
     }

     @Override
     public void execute(CommandSender sender, String[] args) {
          if(args.length == 1) {
               UUID uuid = UUIDFetcher.getUUID(args[0]);
               if(ProxySystem.getInstance().getBanManager().isBanned(uuid)) {
                    ProxySystem.getInstance().getBanManager().unban(uuid, sender);
               } else {
                    sender.sendMessage("The Player is not banned");
               }
          } else {
               sender.sendMessage("§7Please use /unban <player>");
          }
     }

}
