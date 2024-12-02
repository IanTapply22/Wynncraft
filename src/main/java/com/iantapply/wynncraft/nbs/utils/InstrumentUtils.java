package com.iantapply.wynncraft.nbs.utils;

public class InstrumentUtils {
    public static String getNamespacedKey(byte instrument) {
        return switch (instrument) {
            case 1 -> "block.note_block.bass";
            case 2 -> "block.note_block.basedrum";
            case 3 -> "block.note_block.snare";
            case 4 -> "block.note_block.hat";
            case 5 -> "block.note_block.guitar";
            case 6 -> "block.note_block.flute";
            case 7 -> "block.note_block.bell";
            case 8 -> "block.note_block.chime";
            case 9 -> "block.note_block.xylophone";
            case 10 -> "block.note_block.iron_xylophone";
            case 11 -> "block.note_block.cow_bell";
            case 12 -> "block.note_block.didgeridoo";
            case 13 -> "block.note_block.bit";
            case 14 -> "block.note_block.banjo";
            case 15 -> "block.note_block.pling";
            default -> "block.note_block.harp";
        };
    }

    public static byte getCustomInstrumentFirstIndex() {
        if (VersionUtils.getServerVersion() >= 0.0114f) return 16;
        if (VersionUtils.getServerVersion() >= 0.0112f) return 10;
        return 5;
    }
}
