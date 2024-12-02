package com.iantapply.wynncraft;

import com.iantapply.wynncraft.command.CommandCore;
import com.iantapply.wynncraft.database.DatabaseCore;
import com.iantapply.wynncraft.logger.Logger;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public final class Wynncraft extends JavaPlugin {
    @Getter
    public static Wynncraft instance;
    @Getter
    public CommandCore commandCore;

    @Override
    public void onEnable() {
        instance = this;
        Logger.logInitialization();

        DatabaseCore databaseCore = new DatabaseCore();
        databaseCore.initialize();
        databaseCore.registerModels();

        this.commandCore = new CommandCore(this);
        this.commandCore.initialize();
        this.commandCore.registerCommands();

        Logger.logStartup();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
