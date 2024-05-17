package com.iantapply.wynncraft.party;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

import java.util.ArrayList;

@Getter @Setter
public class Party {
    private PartyMessage partyMessage;
    private PartyTeleportation partyTeleportation;

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
     * Adds a new player to the party and abides by the party player limit
     * @param player The player to add to the party
     */
    public void addPartyMember(Player player) {
        if (partyMembers.size() < partyPlayerLimit) {
            partyMembers.add(player);
            getPartyMessage().sendPartyLeaderSuccess(String.format("Added %s to the party.", player.getName()));
        } else {
            getPartyMessage().sendPartyLeaderError("The party is full and cannot add any more members.");
        }
    }

    /**
     * Adds a new player to the party and overrides the party player limit
     * @param player The player to add to the party
     */
    public void addPartyMemberOverride(Player player) {
        this.partyMembers.add(player);
        getPartyMessage().sendPartyLeaderOverride(String.format("Added %s to the party with override", player.getName()));
    }

    /**
     * Removes a player from the party
     * @param player The player to remove from the party
     */
    public void removePartyMember(Player player) {
        partyMembers.remove(player);
    }
}
