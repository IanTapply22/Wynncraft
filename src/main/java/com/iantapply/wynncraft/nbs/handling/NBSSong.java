package com.iantapply.wynncraft.nbs.handling;

import com.iantapply.wynncraft.nbs.CustomInstrument;
import lombok.Getter;

import java.io.File;
import java.util.HashMap;

@Getter
public class NBSSong {
    private final HashMap<Integer, NBSLayer> layerHashMap;
    private final short songHeight;
    private final short length;
    private final String title;
    private final File path;
    private final String author;
    private final String originalAuthor;
    private final String description;
    private final float speed;
    private final float delay;
    private final CustomInstrument[] customInstruments;
    private final int firstCustomInstrumentIndex;
    private final boolean isStereo;

    public NBSSong(float speed, HashMap<Integer, NBSLayer> layerHashMap,
                short songHeight, final short length, String title, String author,
                String description, File path, int firstCustomInstrumentIndex, CustomInstrument[] customInstruments) {
        this(speed, layerHashMap, songHeight, length, title, author, description, path, firstCustomInstrumentIndex, customInstruments, false);
    }

    public NBSSong(float speed, HashMap<Integer, NBSLayer> layerHashMap,
                short songHeight, final short length, String title, String author,
                String description, File path, int firstCustomInstrumentIndex, CustomInstrument[] customInstruments, boolean isStereo) {
        this(speed, layerHashMap, songHeight, length, title, author, "", description, path, firstCustomInstrumentIndex, customInstruments, isStereo);
    }

    public NBSSong(float speed, HashMap<Integer, NBSLayer> layerHashMap,
                short songHeight, final short length, String title, String author, String originalAuthor,
                String description, File path, int firstCustomInstrumentIndex, CustomInstrument[] customInstruments, boolean isStereo) {
        this.speed = speed;
        delay = 20 / speed;
        this.layerHashMap = layerHashMap;
        this.songHeight = songHeight;
        this.length = length;
        this.title = title;
        this.author = author;
        this.originalAuthor = originalAuthor;
        this.description = description;
        this.path = path;
        this.firstCustomInstrumentIndex = firstCustomInstrumentIndex;
        this.customInstruments = customInstruments;
        this.isStereo = isStereo;
    }

    public boolean isStereo() {
        return isStereo;
    }
}
