package com.hanielcota.floruittaxar.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import com.hanielcota.floruittaxar.config.MessageConfig;
import com.hanielcota.floruittaxar.service.TaxService;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPlayer;
import lombok.AllArgsConstructor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Arrays;

@CommandAlias("taxar")
@CommandPermission("floruitaxar.factions")
@Description("Taxa uma facção com um motivo e porcentagem")
@AllArgsConstructor
public class TaxarCommands extends BaseCommand {

    private final TaxService taxService;
    private final MessageConfig messages;

    @Default
    public void onTax(Player sender, String[] args) {
        if (args.length < 1) {
            sender.sendMessage(messages.get("usage"));
            return;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null || !target.isOnline()) {
            sender.sendMessage(messages.get("player-not-found"));
            return;
        }

        MPlayer mPlayer = MPlayer.get(target);
        Faction faction = mPlayer.getFaction();
        if (faction.isNone()) {
            sender.sendMessage(messages.get("no-faction"));
            return;
        }

        if (args.length == 1) {
            sender.sendMessage(messages.get("choose-reason"));
            sendClickableOption(sender, target.getName(), "Banimento", "Banido do servidor");
            sendClickableOption(sender, target.getName(), "Trapaça", "Uso de cheats");
            sendClickableOption(sender, target.getName(), "Desrespeito", "Comportamento inadequado");
            return;
        }

        String reason = String.join(" ", Arrays.copyOfRange(args, 1, args.length - 1));
        if (reason.isEmpty()) {
            sender.sendMessage(messages.get("no-reason"));
            return;
        }

        double percentage = 50.0;
        if (args.length > 2) {
            try {

                percentage = Double.parseDouble(args[args.length - 1]);

                if (percentage <= 0 || percentage > 100) {
                    sender.sendMessage(messages.get("invalid-percentage-range"));
                    return;
                }

            } catch (NumberFormatException e) {
                sender.sendMessage(messages.get("invalid-percentage"));
                return;
            }
        }

        taxService.taxFaction(faction, percentage, reason);
        sender.sendMessage(messages.get("tax-success"));
    }

    private void sendClickableOption(Player sender, String target, String option, String description) {
        TextComponent msg = new TextComponent("§a- " + option + " §7(" + description + ")");
        msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/taxar " + target + " " + option + " 50"));
        sender.spigot().sendMessage(msg);
    }
}
