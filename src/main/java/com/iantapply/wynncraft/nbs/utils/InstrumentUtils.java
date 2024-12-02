package com.iantapply.wynncraft.nbs.utils;

import com.iantapply.wynncraft.nbs.enums.Sound;
import org.bukkit.Instrument;

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

    public static Instrument getBukkitInstrument(byte instrument) {
        switch (instrument) {
            case 0:
                return Instrument.PIANO;
            case 1:
                return Instrument.BASS_GUITAR;
            case 2:
                return Instrument.BASS_DRUM;
            case 3:
                return Instrument.SNARE_DRUM;
            case 4:
                return Instrument.STICKS;
            default: {
                if (VersionUtils.getServerVersion() >= 0.0112f) {
                    return switch (instrument) {
                        case 5 -> Instrument.valueOf("GUITAR");
                        case 6 -> Instrument.valueOf("FLUTE");
                        case 7 -> Instrument.valueOf("BELL");
                        case 8 -> Instrument.valueOf("CHIME");
                        case 9 -> Instrument.valueOf("XYLOPHONE");
                        default -> {
                            if (VersionUtils.getServerVersion() >= 0.0114f) {
                                switch (instrument) {
                                    case 10:
                                        yield Instrument.valueOf("IRON_XYLOPHONE");
                                    case 11:
                                        yield Instrument.valueOf("COW_BELL");
                                    case 12:
                                        yield Instrument.valueOf("DIDGERIDOO");
                                    case 13:
                                        yield Instrument.valueOf("BIT");
                                    case 14:
                                        yield Instrument.valueOf("BANJO");
                                    case 15:
                                        yield Instrument.valueOf("PLING");
                                }
                            }
                            yield Instrument.PIANO;
                        }
                    };
                }
                return Instrument.PIANO;
            }
        }
    }

    public static byte getCustomInstrumentFirstIndex() {
        if (VersionUtils.getServerVersion() >= 0.0114f) {
            return 16;
        }
        if (VersionUtils.getServerVersion() >= 0.0112f) {
            return 10;
        }
        return 5;
    }

}
