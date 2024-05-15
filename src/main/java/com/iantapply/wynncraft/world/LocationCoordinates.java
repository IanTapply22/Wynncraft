package com.iantapply.wynncraft.world;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents the coordinates of a location in the game. This class is used to define the
 * x, y, and z coordinates of a location in the game.
 */
@Setter @Getter
public class LocationCoordinates {
    private double x;
    private double y;
    private double z;

    /**
     * Creates a new LocationCoordinates object with the given x, y, and z coordinates.
     * @param x The x coordinate of the location
     * @param y The y coordinate of the location
     * @param z The z coordinate of the location
     */
    public LocationCoordinates(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
