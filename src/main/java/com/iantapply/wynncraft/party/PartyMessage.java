package com.iantapply.wynncraft.party;

import com.iantapply.wynncraft.world.LandmarkLocation;
import com.iantapply.wynncraft.world.NPCLocation;
import com.iantapply.wynncraft.world.WorldLocation;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * Handles the sending of messages and only messages to party members
 * and the party leader
 * @param party The party that the messages are being sent to
 */
public record PartyMessage(Party party) {
     // TODO: Create a method that can send a message that is from a player used for private chat
    // TODO: Party list message

    /**
     * Sends a message to the party leader with regular text formatting
     * @param message The message to send to the party leader
     */
    public void sendPartLeaderMessage(String message) {
        this.party().getPartyLeader().sendMessage(message);
    }

    /**
     * Sends a message to the party leader with a red color to signify an error
     * @param message The message to send to the party leader
     */
    public void sendPartyLeaderError(String message) {
        Component messageComponent = Component.text(message).color(NamedTextColor.DARK_RED);
        this.party().getPartyLeader().sendMessage(messageComponent);
    }

    /**
     * Sends a message to the party leader with a green color to signify success
     * @param message The message to send to the party leader
     */
    public void sendPartyLeaderSuccess(String message) {
        Component messageComponent = Component.text(message).color(NamedTextColor.DARK_GREEN);
        this.party().getPartyLeader().sendMessage(messageComponent);
    }

    /**
     * Sends a message to the party leader with a gold color to signify an override
     * @param message The message to send to the party leader
     */
    public void sendPartyLeaderOverride(String message) {
        Component messageComponent = Component.text(message).color(NamedTextColor.GOLD);
        this.party().getPartyLeader().sendMessage(messageComponent);
    }

    /**
     * Sends a message to all party members with regular text formatting
     * @param message The message to send to all party members except the leader
     */
    public void sendPartyMembers(String message) {
        for (Player partyMember : this.party().getPartyMembers()) {
            partyMember.sendMessage(message);
        }
    }

    /**
     * Sends a message to all party members with a red color to signify an error
     * @param message The message to send to all party members except the leader
     */
    public void sendPartyMembersError(String message) {
        Component messageComponent = Component.text(message).color(NamedTextColor.DARK_RED);
        for (Player partyMember : this.party().getPartyMembers()) {
            partyMember.sendMessage(messageComponent);
        }
    }

    /**
     * Sends a message to all party members with a green color to signify success
     * @param message The message to send to all party members
     */
    public void sendPartyMembersSuccess(String message) {
        Component messageComponent = Component.text(message).color(NamedTextColor.DARK_GREEN);
        for (Player partyMember : this.party().getPartyMembers()) {
            partyMember.sendMessage(messageComponent);
        }
    }

    /**
     * Sends a message to all party members with a gold color to signify an override
     * @param message The message to send to all party members
     */
    public void sendPartyMembersOverride(String message) {
        Component messageComponent = Component.text(message).color(NamedTextColor.GOLD);
        for (Player partyMember : this.party().getPartyMembers()) {
            partyMember.sendMessage(messageComponent);
        }
    }

    /**
     * Sends a message to all party members notifying about a world location change
     * @param location The world location to notify the party members about
     */
    public void sendLocationTeleportMessage(WorldLocation location) {
        Component messageComponent = Component.text(String.format("Teleporting to %s...", location.name())).color(NamedTextColor.DARK_GRAY);
        for (Player partyMember : this.party().getPartyMembers()) {
            partyMember.sendMessage(messageComponent);
        }
    }

    /**
     * Sends a message to all party members notifying about a landmark location change
     * @param location The landmark location to notify the party members about
     */
    public void sendLocationTeleportMessage(LandmarkLocation location) {
        Component messageComponent = Component.text(String.format("Teleporting to %s...", location.name())).color(NamedTextColor.DARK_GRAY);
        for (Player partyMember : this.party().getPartyMembers()) {
            partyMember.sendMessage(messageComponent);
        }
    }

    /**
     * Sends a message to all party members notifying about an NPC location change
     * @param location The NPC location to notify the party members about
     */
    public void sendLocationTeleportMessage(NPCLocation location) {
        Component messageComponent = Component.text(String.format("Teleporting to the NPC %s...", location.name())).color(NamedTextColor.DARK_GRAY);
        for (Player partyMember : this.party().getPartyMembers()) {
            partyMember.sendMessage(messageComponent);
        }
    }

    /**
     * Sends a message to all party members notifying about a general location change
     * @param location The location to notify the party members about
     */
    public void sendLocationTeleportMessage(Location location) {
        Component messageComponent = Component.text(String.format("Teleporting to world %s (%s, %s, %s)...",
                location.getWorld().getName(),
                location.getX(), location.getY(), location.getZ())).color(NamedTextColor.DARK_GRAY);
        for (Player partyMember : this.party().getPartyMembers()) {
            partyMember.sendMessage(messageComponent);
        }
    }
}
