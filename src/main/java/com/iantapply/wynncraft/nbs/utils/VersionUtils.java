package com.iantapply.wynncraft.nbs.utils;

import com.iantapply.wynncraft.nbs.instruments.NBSCustomInstrument;

import java.util.ArrayList;

import org.bukkit.Bukkit;

public class VersionUtils {
    private static float serverVersion = -1;

    public static ArrayList<NBSCustomInstrument> getVersionCustomInstruments(float serverVersion){
        ArrayList<NBSCustomInstrument> instruments = new ArrayList<>();
        if (serverVersion == 0.0112f){
            instruments.add(new NBSCustomInstrument((byte) 0, "Guitar", "block.note_block.guitar.ogg"));
            instruments.add(new NBSCustomInstrument((byte) 0, "Flute", "block.note_block.flute.ogg"));
            instruments.add(new NBSCustomInstrument((byte) 0, "Bell", "block.note_block.bell.ogg"));
            instruments.add(new NBSCustomInstrument((byte) 0, "Chime", "block.note_block.icechime.ogg"));
            instruments.add(new NBSCustomInstrument((byte) 0, "Xylophone", "block.note_block.xylobone.ogg"));
            return instruments;
        }

        if (serverVersion == 0.0114f){
            instruments.add(new NBSCustomInstrument((byte) 0, "Iron Xylophone", "block.note_block.iron_xylophone.ogg"));
            instruments.add(new NBSCustomInstrument((byte) 0, "Cow Bell", "block.note_block.cow_bell.ogg"));
            instruments.add(new NBSCustomInstrument((byte) 0, "Didgeridoo", "block.note_block.didgeridoo.ogg"));
            instruments.add(new NBSCustomInstrument((byte) 0, "Bit", "block.note_block.bit.ogg"));
            instruments.add(new NBSCustomInstrument((byte) 0, "Banjo", "block.note_block.banjo.ogg"));
            instruments.add(new NBSCustomInstrument((byte) 0, "Pling", "block.note_block.pling.ogg"));
            return instruments;
        }
        return instruments;
    }

    public static ArrayList<NBSCustomInstrument> getVersionCustomInstrumentsForSong(int firstCustomInstrumentIndex){
        ArrayList<NBSCustomInstrument> instruments = new ArrayList<>();

        if (getServerVersion() < 0.0112f){
            if (firstCustomInstrumentIndex == 10) {
                instruments.addAll(getVersionCustomInstruments(0.0112f));
            } else if (firstCustomInstrumentIndex == 16){
                instruments.addAll(getVersionCustomInstruments(0.0112f));
                instruments.addAll(getVersionCustomInstruments(0.0114f));
            }
        } else if (getServerVersion() < 0.0114f){
            if (firstCustomInstrumentIndex == 16){
                instruments.addAll(getVersionCustomInstruments(0.0114f));
            }
        }

        return instruments;
    }

    public static float getServerVersion(){
        if (serverVersion != -1){
            return serverVersion;
        }

        String versionInfo = Bukkit.getServer().getVersion();
        int start = versionInfo.lastIndexOf('(');
        int end = versionInfo.lastIndexOf(')');

        String[] versionParts = versionInfo.substring(start + 5, end).split("\\.");

        StringBuilder versionString = new StringBuilder("0.");
        for (String part : versionParts){
            if (part.length() == 1){
                versionString.append("0");
            }

            versionString.append(part);
        }
        serverVersion = Float.parseFloat(versionString.toString());
        return serverVersion;
    }
}
