package com.iantapply.wynncraft;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public final class Wynncraft extends JavaPlugin {
    @Getter
    private static Wynncraft instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
