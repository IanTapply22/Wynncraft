package com.iantapply.wynncraft.party;

import com.iantapply.wynncraft.world.LandmarkLocation;
import com.iantapply.wynncraft.world.NPCLocation;
import com.iantapply.wynncraft.world.WorldLocation;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * Handles the teleportation of party members to a specific location
 * @param party The party that is being teleported
 */
public record PartyTeleportation(Party party) {

    /**
     * Teleports all party members to a custom World Location
     * @param location The custom World Location to teleport the party members to
     */
    public void teleportPartyMembers(WorldLocation location) {
        for (Player player : party().getPartyMembers()) {
            player.teleport(location.getBukkitLocation(player.getWorld()));
        }
    }

    /**
     * Teleports all party members to a custom Landmark Location
     * @param location The custom Landmark Location to teleport the party members to
     */
    public void teleportPartyMembers(LandmarkLocation location) {
        for (Player player : party().getPartyMembers()) {
            player.teleport(location.getBukkitLocation(player.getWorld()));
        }
    }

    /**
     * Teleports all party members to a custom NPC Location
     * @param location The custom NPC Location to teleport the party members to
     */
    public void teleportPartyMembers(NPCLocation location) {
        for (Player player : party().getPartyMembers()) {
            player.teleport(location.getBukkitLocation(player.getWorld()));
        }
    }

    /**
     * Teleports all party members to a custom Bukkit Location
     * @param location The custom Bukkit Location to teleport the party members to
     */
    public void teleportPartyMembers(Location location) {
        for (Player player : party().getPartyMembers()) {
            player.teleport(location);
        }
    }
}
