package com.iantapply.wynncraft.nbs.instruments;

import com.iantapply.wynncraft.nbs.utils.InstrumentUtils;

public class NBSInstrument {

    public static org.bukkit.Sound getInstrument(byte instrument) {
        return org.bukkit.Sound.valueOf(getInstrumentName(instrument));
    }

    public static String getInstrumentName(byte instrument) {
        return InstrumentUtils.getInstrumentName(instrument);
    }
}
