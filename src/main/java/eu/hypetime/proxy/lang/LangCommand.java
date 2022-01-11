package eu.hypetime.proxy.lang;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.Locale;

/*
    Created by Andre
    At 22:56 Uhr | 11. Jan.. 2022
    Project ProxySystem-v3
*/
public class LangCommand extends Command {

     public LangCommand() {
          super("lang", null, "language");
     }


     @Override
     public void execute(CommandSender sender, String[] args) {
          if (sender instanceof ProxiedPlayer player) {
               if (args.length == 1) {
                    if (Language.getShorts().contains(args[0].toLowerCase()) || args[0].equalsIgnoreCase("toggle")) {
                         if(args[0].equalsIgnoreCase("toggle")) {
                              LanguageManager.changeLanguage(player, 0, true);
                         } else {
                              LanguageManager.changeLanguage(player, Language.getLanguageByShort(args[0].substring(0, 1)).getId(), false);
                         }
                         LanguageManager.sendMessage(player, "changeSuc");
                    } else {
                         LanguageManager.sendMessage(player, "changeErr");
                    }
               }
          } else {
               sender.sendMessage("Only Player");
          }
     }
}
