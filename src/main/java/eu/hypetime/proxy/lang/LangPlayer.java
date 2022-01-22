package eu.hypetime.proxy.lang;

import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.HashMap;
import java.util.UUID;

public class LangPlayer {

    public static HashMap<ProxiedPlayer, LangPlayer> langPlayers = new HashMap<>();
    private final int lang;

    public LangPlayer(ProxiedPlayer player) {
        this.lang = LanguageManager.getDataBaseLanguage(player);
        langPlayers.put(player, this);
    }

    public static LangPlayer getLangPlayer(ProxiedPlayer player) {
        return langPlayers.get(player);
    }

    public int getLang() {
        return lang;
    }
}
