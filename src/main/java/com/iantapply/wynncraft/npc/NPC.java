package com.iantapply.wynncraft.npc;

import com.iantapply.wynncraft.world.NPCLocation;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;

/**
 * Represents an NPC in the game. This interface is used to define the properties of an NPC
 * such as their name, description, location, and dialogues.
 */
public interface NPC {
    ArrayList<Dialogue> dialogues = new ArrayList<>();

    /**
     * Contains any of the initialization code for the NPC previously to spawning
     */
    default void initialize() {}

    /**
     * Spawns the NPC in the game world with all of its settings
     * including the entity type
     */
    void spawn(Location location);

    /**
     * Despawns the NPC from the game world
     */
    default void despawn() {}

    /**
     * What should be done when the player interacts with the NPC
     * This is usually starting dialogue or a new quest
     *
     * @param player The player that executed the interaction
     */
    void onInteract(Player player);

    default void setVisibility(Player player, boolean visibility) {}

    /**
     * The name of the NPC displayed at the top of the entity
     * @return The name of the NPC
     */
    String name();

    /**
     * The description of the NPC that is displayed below the name
     * @return The description of the NPC
     */
    String description();

    /**
     * The location of the NPC as a region or area
     * @return The location name of the NPC
     */
    NPCLocation location();

    World serverWorld();

    /**
     * Adds a dialogue to the NPC
     * @param dialogue The dialogue to add
     */
    default void addDialogue(Dialogue dialogue) {
        this.dialogues.add(dialogue);
    }

    /**
     * Removes a dialogue from the NPC
     * @param dialogue The dialogue to remove
     */
    default void removeDialogue(Dialogue dialogue) {
        this.dialogues.remove(dialogue);
    }

    /**
     * Removes all dialogues from the NPC
     */
    default void removeAllDialogue() {
        this.dialogues.clear();
    }

    /**
     * Sets the dialogues of the NPC to the given array of dialogues
     * @param dialogues The dialogues to set
     */
    default void setDialogues(ArrayList<Dialogue> dialogues) {
        this.dialogues.clear();
        this.dialogues.addAll(dialogues);
    }

    /**
     * Returns the dialogues of the NPC
     * @return The dialogues of the NPC
     */
    default ArrayList<Dialogue> dialogues() {
        return this.dialogues;
    }

    /**
     * Starts the dialogue sequence for the player when they interact with the NPC
     * @param player The player to start the dialogue for
     * @param plugin The plugin instance to run the task scheduler on
     */
    default void startDialogue(Player player, Plugin plugin) {
        // Play the dialogues in order and for the length specified in seconds after the one before it is done
        for (int i = 0; i < this.dialogues.size(); i++) {
            final int dialogueIndex = i;
            Dialogue dialogue = this.dialogues.get(i);

            // Schedule the dialogue to play after the previous one is done
            player.getServer().getScheduler().runTaskLater(plugin, () -> {
                playDialogue(player, dialogueIndex);
            }, dialogue.getDialogueLength() * 20L);
        }
    }

    /**
     * Plays the dialogue for the player through action bar with the given NPC name, dialogue index, and dialogue count.
     * @param player The player to play the dialogue for
     * @param dialogueIndex The index of the dialogue in the sequence
     */
    default void playDialogue(Player player, Integer dialogueIndex) {
        if (dialogueIndex >= this.dialogues.size()) return;

        Dialogue dialogue = this.dialogues.get(dialogueIndex);
        dialogue.play(player, this.name(), dialogueIndex + 1, this.dialogues.size());
    }
}