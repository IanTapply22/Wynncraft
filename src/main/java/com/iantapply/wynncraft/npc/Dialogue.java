package com.iantapply.wynncraft.npc;

import lombok.Getter;
import lombok.Setter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;

import static net.kyori.adventure.text.Component.text;

/**
 * An object used to generate and create dialogue used for cutscenes,
 * npc interactions, and more in Wynncraft
 */
@Setter @Getter
public class Dialogue {
    private String dialogueText; // The text of the dialogue
    private Integer dialogueLength = 0; // The length of the dialogue in seconds until it can be/is skipped
    private boolean skippable = true; // Whether the dialogue can be skipped by the player with the shift key

    /**
     * Creates a new dialogue object with the given dialogue text, dialogue length, and skippable status.
     * @param dialogueText The text contents of the dialogue
     * @param dialogueLength The length of the dialogue in seconds until it can be skipped
     * @param skippable Whether the dialogue can be skipped by the player with the shift key
     */
    public Dialogue(String dialogueText, Integer dialogueLength, boolean skippable) {
        this.dialogueText = dialogueText;
        this.dialogueLength = dialogueLength;
        this.skippable = skippable;
    }

    /**
     * Creates a new dialogue object with the given dialogue text, and dialogue length.
     * @param dialogueText The text contents of the dialogue
     * @param dialogueLength The length of the dialogue in seconds until it can be skipped
     */
    public Dialogue(String dialogueText, Integer dialogueLength) {
        this.dialogueText = dialogueText;
        this.dialogueLength = dialogueLength;
    }

    /**
     * Creates a new dialogue object with the given dialogue text.
     * @param dialogueText The text contents of the dialogue
     */
    public Dialogue(String dialogueText) {
        this.dialogueText = dialogueText;
    }

    /**
     * Plays the dialogue for the player with the given NPC name, dialogue index, and dialogue count.
     * @param player The player to play the dialogue for
     * @param npcName The name of the NPC that is speaking
     * @param dialogueIndex The index of the dialogue in the sequence
     * @param dialogueCount The total number of dialogues in the sequence
     */
    public void play(Player player, String npcName, Integer dialogueIndex, Integer dialogueCount) {
        // Build the message using the new chat components
        Component messageComponent = text().content("[" + dialogueIndex + "/" + dialogueCount + "]").color(NamedTextColor.GRAY)
                .append(text(" " + npcName + ": ").color(NamedTextColor.DARK_GREEN))
                .append(text(dialogueText).color(NamedTextColor.GREEN)).build();

        // Send the message to the player
        player.sendActionBar(messageComponent);
    }
}