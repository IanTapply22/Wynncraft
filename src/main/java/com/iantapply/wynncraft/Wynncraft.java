package com.iantapply.wynncraft;

import com.iantapply.wynncraft.command.CommandCore;
import com.iantapply.wynncraft.database.DatabaseCore;
import com.iantapply.wynncraft.event.PlayerJoinEvent;
import com.iantapply.wynncraft.event.PlayerLeaveEvent;
import com.iantapply.wynncraft.logger.Logger;
import com.iantapply.wynncraft.nbs.NBSCore;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Wynncraft extends JavaPlugin {
    @Getter
    public static Wynncraft instance;
    @Getter
    public CommandCore commandCore;
    @Getter
    public NBSCore nbsCore;

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

        this.nbsCore = new NBSCore();

        Bukkit.getServer().getPluginManager().registerEvents(new PlayerJoinEvent(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerLeaveEvent(), this);

        Logger.logStartup();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
