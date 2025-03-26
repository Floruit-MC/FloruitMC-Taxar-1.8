package com.hanielcota.floruittaxar.config;

import lombok.AllArgsConstructor;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

@AllArgsConstructor
public class MessageConfig {

    private final FileConfiguration config;

    public String get(String path) {
        return config.getString("messages." + path, "Mensagem não configurada.");
    }

    public List<String> getList(String path) {
        return config.getStringList("messages." + path);
    }
}
