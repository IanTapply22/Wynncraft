package com.iantapply.wynncraft.world;

import lombok.Getter;

/**
 * Represents a landmark location in the game. This enum is used to define the different locations
 * that can be used in the game. It provides information like the name and
 * coordinates of the location.
 */
@Getter
public enum LandmarkLocation {
    DETLAS("Detlas", new LocationCoordinates(0, 0, 0)),
    RAGNI("Ragni", new LocationCoordinates(0, 0, 0)),
    NEMRACT("Nemract", new LocationCoordinates(0, 0, 0));

    private final String name;
    private final LocationCoordinates location;

    /**
     * Creates a new location object with the given name and location coordinates.
     * @param name The name of the location on the map (detlas, nemract, etc.)
     * @param location The coordinates of the location (x, y, z)
     */
    LandmarkLocation(String name, LocationCoordinates location) {
        this.name = name;
        this.location = location;
    }
}

