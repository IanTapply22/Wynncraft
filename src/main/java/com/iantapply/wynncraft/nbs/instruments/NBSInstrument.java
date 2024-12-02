package com.iantapply.wynncraft.nbs.instruments;

import com.iantapply.wynncraft.nbs.utils.InstrumentUtils;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.Sound;

public class NBSInstrument {
    /**
     * Fetches a Bukkit Sound object from the Registry that represents the instrument
     * @param instrument The byte of the instrument being played
     * @return A Bukkit Sound object that is the Bukkit Sound of the instrument
     */
    public static Sound getInstrument(byte instrument) {
        String instrumentName = getInstrumentName(instrument);
        NamespacedKey key = NamespacedKey.minecraft(instrumentName);
        Sound sound = Registry.SOUNDS.get(key);

        if (sound == null) {
            throw new IllegalArgumentException("Invalid instrument: " + instrumentName);
        }

        return sound;
    }

    /**
     * Fetches the name of an instrument based on the byte read from an NBS file
     * @param instrument The instrument byte in the NBS file
     * @return A string that represents the name of the sound in the Minecraft namespace
     */
    public static String getInstrumentName(byte instrument) {
        return InstrumentUtils.getNamespacedKey(instrument);
    }
}
