package com.iantapply.wynncraft.nbs.handling;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter @Setter
public class NBSLayer {
    private HashMap<Integer, NBSNote> hashMap = new HashMap<>();
    private byte volume = 100;
    private String name = "";

    /**
     * Gets a note inside the NBS layer
     * @param tick The tick to get the note of
     * @return The note from the specified tick
     */
    public NBSNote getNote(int tick) {
        return hashMap.get(tick);
    }

    /**
     * Sets a note inside the NBS layer
     * @param tick The tick to set the note for
     * @param note The note from teh specified tick
     */
    public void setNote(int tick, NBSNote note) {
        hashMap.put(tick, note);
    }
}
