package com.hanielcota.floruittaxar.service;

import com.hanielcota.floruittaxar.config.MessageConfig;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPlayer;
import lombok.AllArgsConstructor;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@AllArgsConstructor
public class TaxService {

    private final Economy economy;
    private final MessageConfig messages;

    public void taxFaction(Faction faction, double percentage, String reason) {
        double factionBalance = economy.getBalance(faction.getName());
        economy.withdrawPlayer(faction.getName(), factionBalance * (percentage / 100.0));

        for (MPlayer member : faction.getMPlayers()) {
            Player player = member.getPlayer();

            if (player != null && player.isOnline()) {
                double memberBalance = economy.getBalance(player);
                economy.withdrawPlayer(player, memberBalance * (percentage / 100.0));
            }
        }

        for (String line : messages.getList("tax-broadcast")) {

            String formatted = line
                    .replace("{faction}", faction.getTag())
                    .replace("{percentage}", String.valueOf(percentage))
                    .replace("{reason}", reason);

            Bukkit.getOnlinePlayers().forEach(p -> p.sendMessage(formatted));
        }
    }
}
