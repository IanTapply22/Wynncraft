package com.iantapply.wynncraft.nbs;

import com.iantapply.wynncraft.nbs.utils.InstrumentUtils;

public class Instrument {

    public static org.bukkit.Sound getInstrument(byte instrument) {
        return org.bukkit.Sound.valueOf(getInstrumentName(instrument));
    }

    public static String getInstrumentName(byte instrument) {
        return InstrumentUtils.getInstrumentName(instrument);
    }

    public static org.bukkit.Instrument getBukkitInstrument(byte instrument) {
        return InstrumentUtils.getBukkitInstrument(instrument);
    }
}
