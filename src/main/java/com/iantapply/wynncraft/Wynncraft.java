package com.iantapply.wynncraft;

import com.iantapply.wynncraft.command.CommandCore;
import com.iantapply.wynncraft.configuration.ConfigurationCore;
import com.iantapply.wynncraft.configuration.PluginConfigurations;
import com.iantapply.wynncraft.database.DatabaseCore;
import com.iantapply.wynncraft.database.influx.InfluxDatabaseCore;
import com.iantapply.wynncraft.event.minecraft.PlayerChatEvent;
import com.iantapply.wynncraft.event.minecraft.PlayerJoinEvent;
import com.iantapply.wynncraft.event.minecraft.PlayerLeaveEvent;
import com.iantapply.wynncraft.item.ItemCore;
import com.iantapply.wynncraft.logger.Logger;
import com.iantapply.wynncraft.metrics.Metrics;
import com.iantapply.wynncraft.metrics.UpdateChecker;
import com.iantapply.wynncraft.nbs.NBSCore;
import com.iantapply.wynncraft.party.PartyCore;
import com.iantapply.wynncraft.player.PlayerCore;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class Wynncraft extends JavaPlugin {
    @Getter
    public static Wynncraft instance;
    public CommandCore commandCore;
    public PlayerCore playerCore;
    public ConfigurationCore configurationCore;
    public UpdateChecker updateChecker;
    public InfluxDatabaseCore influxDatabaseCore;
    public NBSCore nbsCore;
    public ItemCore itemCore;
    public PartyCore partyCore;

    @Override
    public void onEnable() {
        instance = this;
        Logger.logInitialization();

        this.configurationCore = new ConfigurationCore(this);
        this.configurationCore.initialize();

        DatabaseCore databaseCore = new DatabaseCore();
        databaseCore.initialize();
        databaseCore.registerModels();

        this.influxDatabaseCore = new InfluxDatabaseCore();
        this.influxDatabaseCore.stageInfluxDatabases();
        this.influxDatabaseCore.registerDatabases();

        this.commandCore = new CommandCore(this);
        this.commandCore.initialize();
        this.commandCore.registerCommands();

        this.playerCore = new PlayerCore(this, Bukkit.getOnlinePlayers());
        this.playerCore.initialize();

        this.nbsCore = new NBSCore();

        this.itemCore = new ItemCore(this);
        this.itemCore.initialize();

        this.partyCore = new PartyCore(this);

        Bukkit.getServer().getPluginManager().registerEvents(new PlayerJoinEvent(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerLeaveEvent(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerChatEvent(), this);

        new Metrics(this, PluginConfigurations.BSTATS_PLUGIN_ID);

        this.updateChecker = new UpdateChecker(this, PluginConfigurations.BSTATS_PLUGIN_ID);
        this.updateChecker.check();

        Logger.logStartup();
    }

    @Override
    public void onDisable() {
        this.influxDatabaseCore.unregisterDatabases();

        Logger.logShutdown();
    }
}
