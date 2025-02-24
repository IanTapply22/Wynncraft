package com.iantapply.wynncraft.party;

import com.iantapply.wynncraft.Wynncraft;
import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Handles all party instances within Wynncraft. This is the party registry
 */
@Getter
public class PartyCore {
    private final ArrayList<Party> parties;
    private final Wynncraft plugin;

    /**
     * The main constructor for creating the party core instance.
     * Initializes parties as empty and none exist.
     * @param plugin The instance of the plugin
     */
    public PartyCore(Wynncraft plugin) {
        this.plugin = plugin;
        this.parties = new ArrayList<>();
    }

    /**
     * The main constructor for creating the party core instance.
     * @param plugin The instance of the plugin
     * @param parties The party objects that exist and that should be provided in the new instance
     */
    public PartyCore(Wynncraft plugin, ArrayList<Party> parties) {
        this.plugin = plugin;
        this.parties = parties;
    }

    /**
     * Gets a party instance from the parties ArrayList via the UUID of the party
     * @param uuid The UUID of the party instance to get. This is the unique identifier of the party
     * @return The party instance if found, otherwise null
     */
    public Party getParty(UUID uuid) {
        return this.parties.stream().filter(p -> p.getUuid().equals(uuid)).findFirst().orElse(null);
    }

    /**
     * Gets a party instance from the parties ArrayList via the party leader reference.
     * The party leader is still unique as each player can only host one party instance. UUID reference is preferred for retrieval.
     * @see PartyCore#getParty(UUID) for UUID referenced party retrieval
     * @param partyLeader The leader of the party instance
     * @return The party instance if found, otherwise null
     */
    public Party getParty(Player partyLeader) {
        return this.parties.stream().filter(p -> p.getPartyLeader().equals(partyLeader)).findFirst().orElse(null);
    }

    /**
     * Adds a party instance to the parties Arraylist
     * @param party The party instance to add to the registry
     */
    public void addParty(Party party) {
        if (party == null) return;
        if (this.parties.contains(party)) return;

        this.parties.add(party);
    }

    /**
     * Removes a party from the parties ArrayList via the UUID of the party
     * @param uuid The UUID of the party instance to remove. This is the unique identifier of the party
     */
    public void removeParty(UUID uuid) {
        Party party = this.parties.stream().filter(p -> p.getUuid().equals(uuid)).findFirst().orElse(null);
        if (party == null) return;

        this.parties.remove(party);
    }

    /**
     * Removes a party from the parties ArrayList via the party leader reference.
     * The party leader is still unique as each player can only host one party instance. UUID reference is preferred for removal.
     * @see PartyCore#removeParty(UUID) for UUID referenced party removal
     * @param partyLeader The leader of the party instance
     */
    public void removeParty(Player partyLeader) {
        Party party = this.parties.stream().filter(p -> p.getPartyLeader().equals(partyLeader)).findFirst().orElse(null);
        if (party == null) return;

        this.parties.remove(party);
    }

    /**
     * Updates a currently existing registered party instance in the registry.
     * @param party The party instance to update. The UUID must remain the same
     */
    public void updateParty(Party party) {
        if (party == null) return;
        if (!this.parties.contains(party)) return;

        this.removeParty(party.getUuid());
        this.addParty(party);
    }
}
