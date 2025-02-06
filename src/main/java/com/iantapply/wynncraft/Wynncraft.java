package com.iantapply.wynncraft;

import com.iantapply.wynncraft.command.CommandCore;
import com.iantapply.wynncraft.configuration.ConfigurationCore;
import com.iantapply.wynncraft.database.DatabaseCore;
import com.iantapply.wynncraft.event.PlayerJoinEvent;
import com.iantapply.wynncraft.logger.Logger;
import com.iantapply.wynncraft.nbs.NBSCore;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Wynncraft extends JavaPlugin {
    @Getter
    public static Wynncraft instance;
    @Getter
    public CommandCore commandCore;
    @Getter
    public ConfigurationCore configurationCore;
    @Getter
    public NBSCore nbsCore;

    @Override
    public void onEnable() {
        instance = this;
        Logger.logInitialization();

        this.configurationCore = new ConfigurationCore(this);
        this.configurationCore.initialize();

        DatabaseCore databaseCore = new DatabaseCore();
        databaseCore.initialize();
        databaseCore.registerModels();

        this.commandCore = new CommandCore(this);
        this.commandCore.initialize();
        this.commandCore.registerCommands();

        this.nbsCore = new NBSCore();

        Bukkit.getServer().getPluginManager().registerEvents(new PlayerJoinEvent(), this);

        Logger.logStartup();
    }

    @Override
    public void onDisable() {
        Logger.logShutdown();
    }
}
