package com.iantapply.wynncraft.world;

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.World;

/**
 * Represents an NPC location in the game. This enum is used to define the different locations
 * for NPC that are registered in the game.
 */
@Getter
public enum NPCLocation {
    EXAMPLE_NPC("Example", new LocationCoordinates(0, 0, 0), LandmarkLocation.RAGNI);

    private final String npcName;
    private final LocationCoordinates location;
    private final LandmarkLocation landmarkLocation;

    /**
     * Creates a new NPC location object with the given name and location coordinates.
     * @param npcName The name of the location on the map (detlas, nemract, etc.)
     * @param location The coordinates of the location (x, y, z)
     * @param landmarkLocation The landmark location of the NPC
     */
    NPCLocation(String npcName, LocationCoordinates location, LandmarkLocation landmarkLocation) {
        this.npcName = npcName;
        this.location = location;
        this.landmarkLocation = landmarkLocation;
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
     * Gets the NPC locations X value
     * @return The X value as a double
     */
    public double getLocationX() {
        return getLocation().getX();
    }

    /**
     * Gets the NPC locations Y value
     * @return The Y value as a double
     */
    public double getLocationY() {
        return getLocation().getY();
    }

    /**
     * Gets the NPC locations Z value
     * @return The Z value as a double
     */
    public double getLocationZ() {
        return getLocation().getZ();
    }
}