package com.iantapply.wynncraft.party;

import com.iantapply.wynncraft.world.LandmarkLocation;
import com.iantapply.wynncraft.world.NPCLocation;
import com.iantapply.wynncraft.world.WorldLocation;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public record PartyTeleportation(Party party) {

    public void teleportPartyMembers(WorldLocation location) {
        for (Player player : party().getPartyMembers()) {
            player.teleport(location.getBukkitLocation(player.getWorld()));
        }
    }

    public void teleportPartyMembers(LandmarkLocation location) {
        for (Player player : party().getPartyMembers()) {
            player.teleport(location.getBukkitLocation(player.getWorld()));
        }
    }

    public void teleportPartyMembers(NPCLocation location) {
        for (Player player : party().getPartyMembers()) {
            player.teleport(location.getBukkitLocation(player.getWorld()));
        }
    }

    public void teleportPartyMembers(Location location) {
        for (Player player : party().getPartyMembers()) {
            player.teleport(location);
        }
    }
}
