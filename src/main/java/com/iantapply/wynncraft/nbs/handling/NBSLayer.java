package com.iantapply.wynncraft.nbs.handling;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter @Setter
public class NBSLayer {
    private HashMap<Integer, NBSNote> hashMap = new HashMap<>();
    private byte volume = 100;
    private String name = "";

    public NBSNote getNote(int tick) {
        return hashMap.get(tick);
    }

    public void setNote(int tick, NBSNote note) {
        hashMap.put(tick, note);
    }
}
