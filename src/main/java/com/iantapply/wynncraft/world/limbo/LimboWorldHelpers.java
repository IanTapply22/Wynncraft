package com.iantapply.wynncraft.world.limbo;

import com.iantapply.wynncraft.Wynncraft;
import com.iantapply.wynncraft.configuration.ConfigurationCore;
import com.iantapply.wynncraft.logger.Logger;
import com.iantapply.wynncraft.logger.LoggingLevel;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.generator.ChunkGenerator;

import java.util.Arrays;
import java.util.Random;

public class LimboWorldHelpers {

    public static void initialize() {
        ConfigurationCore configurationCore = Wynncraft.getInstance().getConfigurationCore();
        String worldName = configurationCore.getString("WYNNCRAFT_LIMBO_WORLD");
        World world = Bukkit.getWorld(worldName);

        if (world != null) {
            configureGameRules(world);
            return;
        }

        String rawCoordinates = configurationCore.getString("WYNNCRAFT_LIMBO_COORDINATES");
        double[] parsedCoordinates = Arrays.stream(rawCoordinates.split(",")).mapToDouble(Double::parseDouble).toArray(); // X, Y, Z
        World worldInstance = LimboWorldHelpers.generateLimboWorld(worldName);
        Location limboLocation = new Location(worldInstance, parsedCoordinates[0], parsedCoordinates[1], parsedCoordinates[2]);
        generateLimboStructure(limboLocation);
        configureGameRules(worldInstance);

        Logger.log(LoggingLevel.INFO, String.format("Limbo world %s has been generated initially. This is a one time execution.", worldName));
    }

    /**
     * Generates a void world with the specified name
     * @param worldName The name of the world to generate
     * @return The generated world
     */
    public static World generateLimboWorld(String worldName) {
        WorldCreator worldCreator = new WorldCreator(worldName);
        worldCreator.generator(new ChunkGenerator() {
            @Override
            public ChunkData generateChunkData(World world, Random random, int x, int z, BiomeGrid biome) {
                return createChunkData(world);
            }
        });

        World world = worldCreator.createWorld();

        if (world != null) {
            Logger.log(LoggingLevel.INFO, "Created void world " + worldName);
        } else {
            Logger.log(LoggingLevel.WARNING, "Could not create void world " + worldName);
        }

        return world;
    }

    /**
     * Generates a limbo structure at the specified location. This is usually a wool box
     * @param location The location to generate the structure
     */
    public static void generateLimboStructure(Location location) {
        int x = location.getBlockX();
        int y = location.getBlockY() - 1; // Start floor one block below the provided Y location
        int z = location.getBlockZ();

        int radius = 2; // Half the size of the box (5x5)

        Bukkit.getScheduler().runTaskLater(Wynncraft.getInstance(), () -> {
            for (int dx = -radius; dx <= radius; dx++) {
                for (int dy = 0; dy <= 4; dy++) { // Height of the box (5 blocks)
                    for (int dz = -radius; dz <= radius; dz++) {
                        // Skip inner blocks to make the structure hollow
                        if (Math.abs(dx) == radius || Math.abs(dy) == 0 || Math.abs(dy) == 4 || Math.abs(dz) == radius) {
                            Block block = location.getWorld().getBlockAt(x + dx, y + dy, z + dz);
                            block.setType(Material.BLACK_WOOL);
                        }
                    }
                }
            }
        }, 40L); // Delay for world to load
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
