package com.iantapply.wynncraft.npc;

import com.iantapply.wynncraft.world.NPCLocation;
import org.bukkit.entity.Player;

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
    void spawn();

    /**
     * Despawns the NPC from the game world
     */
    default void despawn() {}

    /**
     * What should be done when the player interacts with the NPC
     * This is usually starting dialogue or a new quest
     */
    void onInteract();

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
     * Returns the dialogues of the NPC
     * @return The dialogues of the NPC
     */
    default ArrayList<Dialogue> dialogues() {
        return this.dialogues;
    }

    default void startDialogue(Player player) {
        this.playDialogue(player, 0);
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