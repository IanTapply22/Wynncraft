package com.iantapply.wynncraft.nbs.instruments;

import com.iantapply.wynncraft.nbs.enums.Sound;
import lombok.Getter;

@Getter
public class NBSCustomInstrument {
    private final byte index;
    private final String name;
    private final String soundFileName;
    private org.bukkit.Sound sound;

    public NBSCustomInstrument(byte index, String name, String soundFileName) {
        this.index = index;
        this.name = name;
        this.soundFileName = soundFileName.replaceAll(".ogg", "");
        if (this.soundFileName.equalsIgnoreCase("pling")){
            this.sound = Sound.NOTE_PLING.bukkitSound();
        }
    }
}
