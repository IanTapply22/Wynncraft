package com.iantapply.wynncraft.nbs.instruments;

import com.iantapply.wynncraft.nbs.utils.InstrumentUtils;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.Sound;

public class NBSInstrument {
    public static Sound getInstrument(byte instrument) {
        String instrumentName = getInstrumentName(instrument);
        NamespacedKey key = NamespacedKey.minecraft(instrumentName);
        Sound sound = Registry.SOUNDS.get(key);

        if (sound == null) {
            throw new IllegalArgumentException("Invalid instrument: " + instrumentName);
        }

        return sound;
    }

    public static String getInstrumentName(byte instrument) {
        return InstrumentUtils.getNamespacedKey(instrument);
    }
}
