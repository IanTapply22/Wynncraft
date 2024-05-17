package com.iantapply.wynncraft.world;

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.World;

/**
 * Represents a world location in the game. This enum is used to define the different locations
 * that can be used in the game that are important. It provides information like the name and
 * coordinates of the location.
 */
@Getter
public enum WorldLocation {
    EXAMPLE("Example location", new LocationCoordinates(0, 0, 0));

    private final String name;
    private final LocationCoordinates location;

    /**
     * Creates a new location object with the given name and location coordinates.
     * @param name The name of the location on the map (detlas, nemract, etc.)
     * @param location The coordinates of the location (x, y, z)
     */
    WorldLocation(String name, LocationCoordinates location) {
        this.name = name;
        this.location = location;
    }

    /**
     * Gets the location as a Bukkit location with the current
     * coordinates and the specified world
     * @param world The Bukkit world to spawn the entity in
     * @return A new formulated location that can be used with the Bukkit/Paper API
     */
    public Location getBukkitLocation(World world) {
        return new Location(world, getLocationX(), getLocationY(), getLocationZ());
    }

    /**
     * Gets the locations X value
     * @return The X value as a double
     */
    public double getLocationX() {
        return getLocation().getX();
    }

    /**
     * Gets the locations Y value
     * @return The Y value as a double
     */
    public double getLocationY() {
        return getLocation().getY();
    }

    /**
     * Gets the locations Z value
     * @return The Z value as a double
     */
    public double getLocationZ() {
        return getLocation().getZ();
    }
}

