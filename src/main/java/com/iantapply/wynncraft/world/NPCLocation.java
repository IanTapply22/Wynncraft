package com.iantapply.wynncraft.world;

import lombok.Getter;

/**
 * Represents an NPC location in the game. This enum is used to define the different locations
 * for NPC that are registered in the game.
 */
@Getter
public enum NPCLocation {
    RAGNIS_KING("Ragni", new LocationCoordinates(0, 0, 0), LandmarkLocation.RAGNI);

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
}