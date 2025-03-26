package com.hanielcota.floruittaxar;

import co.aikar.commands.PaperCommandManager;
import com.hanielcota.floruittaxar.commands.TaxarCommands;
import com.hanielcota.floruittaxar.config.MessageConfig;
import com.hanielcota.floruittaxar.service.TaxService;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class FloruitTaxarPlugin extends JavaPlugin {

    private Economy economy;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        if (!setupEconomy()) {
            getLogger().severe("Vault não encontrado ou economia não configurada!");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        // Carrega mensagens do config.yml
        MessageConfig messageConfig = new MessageConfig(getConfig());

        // Instancia serviço de taxação
        TaxService taxService = new TaxService(economy, messageConfig);

        // Registra comandos com dependências injetadas
        PaperCommandManager commandManager = new PaperCommandManager(this);
        commandManager.registerCommand(new TaxarCommands(taxService, messageConfig));
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) return false;

        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) return false;

        economy = rsp.getProvider();
        return economy != null;
    }
}
