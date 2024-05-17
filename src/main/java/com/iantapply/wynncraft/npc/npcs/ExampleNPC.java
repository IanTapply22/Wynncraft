package com.iantapply.wynncraft.npc.npcs;

import com.iantapply.wynncraft.npc.Dialogue;
import com.iantapply.wynncraft.npc.NPC;
import com.iantapply.wynncraft.npc.types.Villager;
import com.iantapply.wynncraft.world.NPCLocation;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class ExampleNPC implements NPC {

    /**
     * Spawns the NPC in the game world with all of its settings
     * including the entity type
     */
    @Override
    public void spawn() {
        // Spawn the NPC entity at the designated server world location
        new Villager(location().getBukkitLocation(serverWorld()));

        // Add example dialogue line to the NPC
        addDialogue(new Dialogue("This is an example first dialogue"));
    }

    /**
     * What should be done when the player interacts with the NPC
     * This is usually starting dialogue or a new quest
     */
    @Override
    public void onInteract(Player player) {
        // Play the first and only dialogue line in the NPC
        playDialogue(player, 0);
    }

    /**
     * The name of the NPC displayed at the top of the entity
     *
     * @return The name of the NPC
     */
    @Override
    public String name() {
        return "Example";
    }

    /**
     * The description of the NPC that is displayed below the name
     *
     * @return The description of the NPC
     */
    @Override
    public String description() {
        return "NPC";
    }

    /**
     * The location of the NPC as a region or area
     *
     * @return The location name of the NPC
     */
    @Override
    public NPCLocation location() {
        return NPCLocation.EXAMPLE_NPC;
    }

    @Override
    public World serverWorld() {
        return Bukkit.getWorld("world");
    }
}
