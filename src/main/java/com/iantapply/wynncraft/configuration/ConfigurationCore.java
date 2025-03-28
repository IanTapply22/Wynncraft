package com.iantapply.wynncraft.configuration;

import com.iantapply.wynncraft.Wynncraft;
import com.iantapply.wynncraft.logger.Logger;
import com.iantapply.wynncraft.logger.LoggingLevel;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.util.Arrays;

/**
 * The configuration core that handles all configuration related tasks
 */
@Getter
public class ConfigurationCore {

    private final Wynncraft plugin;
    private final FileConfiguration configuration;

    public ConfigurationCore(Wynncraft plugin) {
        this.plugin = plugin;
        this.configuration = plugin.getConfig();
    }

    /**
     * Initializes the configuration core by creating and copying the configuration file
     * if needed and sets all default values.
     */
    public void initialize() {
        Logger.log(LoggingLevel.INFO, "Initializing the configuration...");

        // Create the configuration file if it does not exist
        File configFile = new File(plugin.getDataFolder(), "config.yml");
        if (!configFile.exists()) plugin.saveDefaultConfig();

        // TODO: Have default configuration values set internally
        // Not needed yet as currently it is preloaded with defaults and copied

        Logger.log(LoggingLevel.INFO, "Configuration initialized.");
    }

    /**
     * Gets a string from the configuration file via a path to the name of the configuration
     * @param path The path, or name of the variable in the configuration file
     * @return A string if the value is found, otherwise the return is null and a warning is thrown
     */
    public String getString(String path) {
        if (!configuration.contains(path)) {
            Logger.log(LoggingLevel.WARNING, String.format("Configuration %s does not exist in %s. Please add the path if needed.", PluginConfigurations.MAIN_CONFIG_FILE, path));
            return null;
        }

        if (configuration.getString(path) == null) {
            Logger.log(LoggingLevel.WARNING, String.format("Configuration %s is null in %s. Please set the value.", PluginConfigurations.MAIN_CONFIG_FILE, path));
            return null;
        }

        return configuration.getString(path);
    }

    /**
     * Retrieves a location from the configuration file. The location is stored as a string in the format and the
     * config prefixes must align and end with _WORLD and _COORDINATES.
     * @param path The path to the coordinate configuration line. NOT the world path
     * @return A location object if the location is found, otherwise null
     */
    public Location getLocation(String path) {
        String rawCoordinates = this.getString(path);
        double[] parsedCoordinates = Arrays.stream(rawCoordinates.split(",")).mapToDouble(Double::parseDouble).toArray(); // X, Y, Z
        String configWorldPath = path.replace("COORDINATES", "WORLD");
        String worldName = this.getString(configWorldPath);
        if (worldName == null) {
            Logger.log(LoggingLevel.WARNING, String.format("World %s does not exist in the configuration file. Please add the world if needed.", worldName));
            return null;
        }

        World worldInstance = Bukkit.getWorld(worldName);
        if (worldInstance == null) {
            Logger.log(LoggingLevel.WARNING, String.format("World %s does not exist in the server. Please add the world if needed.", worldName));
            return null;
        }

        return new Location(worldInstance, parsedCoordinates[0], parsedCoordinates[1], parsedCoordinates[2]);
    }

    /**
     * Gets an integer from the configuration file via a path to the name of the configuration
     * @param path The path, or name of the variable in the configuration file
     * @return An integer if the value is found, otherwise the return is -1 and a warning is thrown
     */
    public int getInteger(String path) {
        if (!configuration.contains(path)) {
            Logger.log(LoggingLevel.WARNING, String.format("Configuration %s does not exist in %s. Please add the path if needed.", PluginConfigurations.MAIN_CONFIG_FILE, path));
            return -1;
        }

        if (configuration.getInt(path) == -1) {
            Logger.log(LoggingLevel.WARNING, String.format("Configuration %s is null in %s. Please set the value.", PluginConfigurations.MAIN_CONFIG_FILE, path));
            return -1;
        }

        return configuration.getInt(path);
    }

    /**
     * Gets a boolean from the configuration file via a path to the name of the configuration
     * @param path The path, or name of the variable in the configuration file
     * @return A boolean if the value is found, otherwise the return is false and a warning is thrown
     */
    public boolean getBoolean(String path) {
        if (!configuration.contains(path)) {
            Logger.log(LoggingLevel.WARNING, String.format("Configuration %s does not exist in %s. Please add the path if needed.", PluginConfigurations.MAIN_CONFIG_FILE, path));
            return false;
        }

        if (configuration.get(path) == null) {
            Logger.log(LoggingLevel.WARNING, String.format("Configuration %s is null in %s. Please set the value.", PluginConfigurations.MAIN_CONFIG_FILE, path));
            return false;
        }

        return configuration.getBoolean(path);
    }

    /**
     * Gets a double from the configuration file via a path to the name of the configuration
     * @param path The path, or name of the variable in the configuration file
     * @return A double if the value is found, otherwise the return is -1 and a warning is thrown
     */
    public double getDouble(String path) {
        if (!configuration.contains(path)) {
            Logger.log(LoggingLevel.WARNING, String.format("Configuration %s does not exist in %s. Please add the path if needed.", PluginConfigurations.MAIN_CONFIG_FILE, path));
            return -1.0;
        }

        if (configuration.getDouble(path) == -1) {
            Logger.log(LoggingLevel.WARNING, String.format("Configuration %s is null in %s. Please set the value.", PluginConfigurations.MAIN_CONFIG_FILE, path));
            return -1.0;
        }

        return configuration.getDouble(path);
    }
}
