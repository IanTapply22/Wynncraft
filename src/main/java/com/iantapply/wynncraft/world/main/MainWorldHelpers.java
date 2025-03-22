package com.iantapply.wynncraft.world.main;

import com.iantapply.wynncraft.Wynncraft;
import com.iantapply.wynncraft.configuration.ConfigurationCore;
import com.iantapply.wynncraft.logger.Logger;
import com.iantapply.wynncraft.logger.LoggingLevel;
import org.bukkit.*;

public class MainWorldHelpers {

    public static void initialize() {
        ConfigurationCore configurationCore = Wynncraft.getInstance().getConfigurationCore();
        String worldName = configurationCore.getString("WYNNCRAFT_MAIN_WORLD");
        World world = Bukkit.getWorld(worldName);

        if (world != null) {
            configureGameRules(world);
            return;
        }

        world = generateMainWorld(worldName);
        configureGameRules(world);

        Logger.log(LoggingLevel.INFO, String.format("Main world %s has been generated initially. This is a one time execution.", worldName));
    }

    /**
     * Generates a world with the specified name
     * @param worldName The name of the world to generate
     * @return The generated world
     */
    public static World generateMainWorld(String worldName) {
        WorldCreator worldCreator = new WorldCreator(worldName);

        World world = worldCreator.createWorld();

        if (world != null) {
            Logger.log(LoggingLevel.INFO, "Created world " + worldName);
        } else {
            Logger.log(LoggingLevel.WARNING, "Could not create world " + worldName);
        }

        return world;
    }

    /**
     * Configures the gamerules for the specified world
     * @param world The world to configure the gamerules for
     */
    public static void configureGameRules(World world) {
        world.setGameRule(GameRule.DISABLE_RAIDS, true);
        world.setGameRule(GameRule.DO_FIRE_TICK, false);
        world.setGameRule(GameRule.DO_IMMEDIATE_RESPAWN, true);
        world.setGameRule(GameRule.DO_INSOMNIA, false);
        world.setGameRule(GameRule.DO_MOB_SPAWNING, false);
        world.setGameRule(GameRule.DO_PATROL_SPAWNING, false);
        world.setGameRule(GameRule.DO_TRADER_SPAWNING, false);
        world.setGameRule(GameRule.DO_VINES_SPREAD, false);
        world.setGameRule(GameRule.DO_WARDEN_SPAWNING, false);
        world.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
        world.setGameRule(GameRule.KEEP_INVENTORY, true);
        world.setGameRule(GameRule.MOB_GRIEFING, false);
        world.setGameRule(GameRule.SHOW_DEATH_MESSAGES, false);
        world.setGameRule(GameRule.NATURAL_REGENERATION, false);
        world.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);
        world.setGameRule(GameRule.PROJECTILES_CAN_BREAK_BLOCKS, false);
    }
}
