package com.iantapply.wynncraft.party;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Represents a party that can move players together and have private chat capabilities
 */
@Getter @Setter
public class Party {
    private UUID uuid = UUID.randomUUID();
    private PartyMessage partyMessage;
    private PartyTeleportation partyTeleportation;

    private ArrayList<Player> bannedPlayers = new ArrayList<>();

    private Player partyLeader;
    private ArrayList<Player> partyMembers = new ArrayList<>();
    private Integer partyPlayerLimit = 5;

    /**
     * Creates a new party instance that can move players together and have private
     * chat capabilities
     * @param partyLeader The leader of the party that has the administrator controls
     * @param partyMembers The array of party members that are added to the party when
     *                     it is initialized
     * @param partyPlayerLimit The party player limit, this does not include the party leader
     */
    public Party(Player partyLeader, ArrayList<Player> partyMembers, Integer partyPlayerLimit) {
        this.partyLeader = partyLeader;
        this.partyMembers = partyMembers;
        this.partyPlayerLimit = partyPlayerLimit;
    }

    /**
     * Creates a new party instance that can move players together and have private
     * chat capabilities
     * @param partyLeader The leader of the party that has the administrator controls
     * @param partyPlayerLimit The party player limit, this does not include the party leader
     */
    public Party(Player partyLeader, Integer partyPlayerLimit) {
        this.partyLeader = partyLeader;
        this.partyPlayerLimit = partyPlayerLimit;
    }

    /**
     * Creates a new party instance that can move players together and have private
     * chat capabilities
     * @param partyLeader The leader of the party that has the administrator controls
     * @param partyMembers The party player limit, this does not include the party leader
     */
    public Party(Player partyLeader, ArrayList<Player> partyMembers) {
        this.partyLeader = partyLeader;
        this.partyMembers = partyMembers;
    }

    /**
     * Creates a new party instance that can move players together and have private
     * chat capabilities
     * @param partyLeader The leader of the party that has administrator controls
     */
    public Party(Player partyLeader) {
        this.partyLeader = partyLeader;
    }

    /**
     * Disbands the party and removes all party members
     * The instance of the party still remains, but an even will be fired hear to
     * remove it from the leaders and members party list
     */
    public void disband() {
        // TODO: Automatically send disband message
        this.partyMembers.clear();
    }

    /**
     * Promotes a player to be the leader of the party
     * @param player The player to promote to the leader of the party
     */
    public void promote(Player player) {
        // TODO: Send a message to all players in the party that the leader has changed here
        // TODO: Send a message that they have been promoted to the new leader
        this.partyMembers.add(this.partyLeader);
        this.partyLeader = player;
    }

    /**
     * Bans a player from the current party, they cannot be invited until they are unbanned
     * @param player The player to ban from the party
     */
    public void ban(Player player) {
        if (this.partyMembers.contains(player)) {
            this.bannedPlayers.add(player);
            this.partyMembers.remove(player);
        } else {
            this.bannedPlayers.add(player);
        }
    }

    /**
     * Unbans a player from the current party, they can now be invited back to the party
     * @param player The player to unban from the party
     */
    public void unban(Player player) {
        this.bannedPlayers.remove(player);
    }

    /**
     * Adds a new player to the party and abides by the party player limit
     * @param player The player to add to the party
     */
    public void addPartyMember(Player player) {
        if (this.partyMembers.size() < this.partyPlayerLimit) {
            this.partyMembers.add(player);
            this.getPartyMessage().sendPartyLeaderSuccess(String.format("Added %s to the party.", player.getName()));
        } else {
            this.getPartyMessage().sendPartyLeaderError("The party is full and cannot add any more members");
        }
    }

    /**
     * Adds a new player to the party and overrides the party player limit
     * @param player The player to add to the party
     */
    public void addPartyMemberOverride(Player player) {
        this.partyMembers.add(player);
        this.getPartyMessage().sendPartyLeaderOverride(String.format("Added %s to the party with override", player.getName()));
    }

    /**
     * Removes a player from the party
     * @param player The player to remove from the party
     */
    public void removePartyMember(Player player) {
        this.partyMembers.remove(player);
    }
}
