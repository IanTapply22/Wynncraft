package com.iantapply.wynncraft;

import com.iantapply.wynncraft.command.CommandCore;
import com.iantapply.wynncraft.database.database.WynncraftDatabase;
import com.iantapply.wynncraft.logger.Logger;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public final class Wynncraft extends JavaPlugin {
    @Getter
    private static Wynncraft instance;

    @Override
    public void onEnable() {
        Logger.logInitialization();

        new WynncraftDatabase().connect();

        CommandCore commandCore = new CommandCore(this);
        commandCore.initialize();
        commandCore.registerCommands();

        Logger.logStartup();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
