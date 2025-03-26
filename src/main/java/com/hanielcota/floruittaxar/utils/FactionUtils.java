package com.hanielcota.floruittaxar.utils;

import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPlayer;
import lombok.experimental.UtilityClass;
import org.bukkit.entity.Player;

@UtilityClass
public class FactionUtils {

    /**
     * Obtém a facção de um jogador.
     *
     * @param player O jogador.
     * @return A facção do jogador ou null se não estiver em uma facção.
     */
    public static Faction getFaction(Player player) {
        MPlayer mPlayer = MPlayer.get(player);
        return mPlayer.getFaction();
    }

    /**
     * Verifica se um jogador está em uma facção.
     *
     * @param player O jogador.
     * @return true se o jogador está em uma facção, false caso contrário.
     */
    public static boolean isInFaction(Player player) {
        MPlayer mPlayer = MPlayer.get(player);
        return !mPlayer.getFaction().isNone();
    }

    /**
     * Obtém o nome da facção de um jogador.
     *
     * @param player O jogador.
     * @return O nome da facção ou "Nenhuma" se não estiver em uma facção.
     */
    public static String getFactionName(Player player) {
        Faction faction = getFaction(player);
        return faction != null ? faction.getName() : "Nenhuma";
    }

    /**
     * Verifica se dois jogadores estão na mesma facção.
     *
     * @param player1 Primeiro jogador.
     * @param player2 Segundo jogador.
     * @return true se ambos estão na mesma facção, false caso contrário.
     */
    public static boolean areInSameFaction(Player player1, Player player2) {
        Faction faction1 = getFaction(player1);
        Faction faction2 = getFaction(player2);
        return faction1 != null && faction1.equals(faction2);
    }
}