package com.iantapply.wynncraft.nbs.utils;

import com.iantapply.wynncraft.nbs.enums.Sound;

public class InstrumentUtils {
    public static String getInstrumentName(byte instrument) {
        return switch (instrument) {
            case 0 -> Sound.getFromBukkitName("BLOCK_NOTE_BLOCK_HARP").toString();
            case 1 -> Sound.getFromBukkitName("BLOCK_NOTE_BLOCK_BASS").toString();
            case 2 -> Sound.getFromBukkitName("BLOCK_NOTE_BLOCK_BASEDRUM").toString();
            case 3 -> Sound.getFromBukkitName("BLOCK_NOTE_BLOCK_SNARE").toString();
            case 4 -> Sound.getFromBukkitName("BLOCK_NOTE_BLOCK_HAT").toString();
            case 5 -> Sound.getFromBukkitName("BLOCK_NOTE_BLOCK_GUITAR").toString();
            case 6 -> Sound.getFromBukkitName("BLOCK_NOTE_BLOCK_FLUTE").toString();
            case 7 -> Sound.getFromBukkitName("BLOCK_NOTE_BLOCK_BELL").toString();
            case 8 -> Sound.getFromBukkitName("BLOCK_NOTE_BLOCK_CHIME").toString();
            case 9 -> Sound.getFromBukkitName("BLOCK_NOTE_BLOCK_XYLOPHONE").toString();
            case 10 -> Sound.getFromBukkitName("BLOCK_NOTE_BLOCK_IRON_XYLOPHONE").toString();
            case 11 -> Sound.getFromBukkitName("BLOCK_NOTE_BLOCK_COW_BELL").toString();
            case 12 -> Sound.getFromBukkitName("BLOCK_NOTE_BLOCK_DIDGERIDOO").toString();
            case 13 -> Sound.getFromBukkitName("BLOCK_NOTE_BLOCK_BIT").toString();
            case 14 -> Sound.getFromBukkitName("BLOCK_NOTE_BLOCK_BANJO").toString();
            case 15 -> Sound.getFromBukkitName("BLOCK_NOTE_BLOCK_PLING").toString();
            default -> Sound.getFromBukkitName("BLOCK_NOTE_BLOCK_HARP").toString();
        };
    }

    public static byte getCustomInstrumentFirstIndex() {
        if (VersionUtils.getServerVersion() >= 0.0114f) return 16;
        if (VersionUtils.getServerVersion() >= 0.0112f) return 10;
        return 5;
    }
}
