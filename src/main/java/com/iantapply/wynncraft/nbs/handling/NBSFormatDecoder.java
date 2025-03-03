package com.iantapply.wynncraft.nbs.handling;

import com.iantapply.wynncraft.logger.Logger;
import com.iantapply.wynncraft.logger.LoggingLevel;
import com.iantapply.wynncraft.nbs.instruments.NBSCustomInstrument;
import com.iantapply.wynncraft.nbs.utils.VersionUtils;
import com.iantapply.wynncraft.nbs.utils.InstrumentUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Credit to the project <a href="https://github.com/koca2000/NoteBlockAPI">NoteBlockAPI</a> and @koca2000 for the decoding of the NBS files in this system
 */
public class NBSFormatDecoder {

    /**
     * Parses a decoded NBS file instance and returns an NBSSong object
     * @param decodeFile The decoded, NBS file
     * @return The NBSSong object for the specified file
     */
    public static NBSSong parse(File decodeFile) {
        try {
            return parse(new FileInputStream(decodeFile), decodeFile);
        } catch (FileNotFoundException e) {
            Logger.log(LoggingLevel.ERROR, "The file provided could not be found. Please provide a valid file.");
        }
        return null;
    }

    /**
     * Parses an InputStream for an NBS file and returns an NBSSong object
     * @param inputStream The input stream of the NBS file
     * @return The NBSSong object for the specified InputStream
     */
    public static NBSSong parse(InputStream inputStream) {
        return parse(inputStream, null);
    }

    /**
     * Reads an NBS format song file InputStream and/or File instance and produces a usable
     * NBSSong object that can be used to play the note block notes.
     * @param inputStream The InputStream object of the NBS file
     * @param songFile The file instance of the NBS file
     * @return A usable NBSSong object used to play the sounds
     */
    private static NBSSong parse(InputStream inputStream, File songFile) {
        HashMap<Integer, NBSLayer> layerHashMap = new HashMap<>();
        try {
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            short length = readShort(dataInputStream);
            int firstCustomInstrument = 10;
            int firstCustomInstrumentDelta;
            int nbsversion = 0;
            if (length == 0) {
                nbsversion = dataInputStream.readByte();
                firstCustomInstrument = dataInputStream.readByte();
                if (nbsversion >= 3) {
                    length = readShort(dataInputStream);
                }
            }
            firstCustomInstrumentDelta = InstrumentUtils.getCustomInstrumentFirstIndex() - firstCustomInstrument;
            short songHeight = readShort(dataInputStream);
            String title = readString(dataInputStream);
            String author = readString(dataInputStream);
            readString(dataInputStream); // original author
            String description = readString(dataInputStream);
            float speed = readShort(dataInputStream) / 100f;
            dataInputStream.readBoolean(); // auto-save
            dataInputStream.readByte(); // auto-save duration
            dataInputStream.readByte(); // x/4ths, time signature
            readInt(dataInputStream); // minutes spent on project
            readInt(dataInputStream); // left clicks (why?)
            readInt(dataInputStream); // right clicks (why?)
            readInt(dataInputStream); // blocks added
            readInt(dataInputStream); // blocks removed
            readString(dataInputStream); // .mid/.schematic file name
            if (nbsversion >= 4) {
                dataInputStream.readByte(); // loop on/off
                dataInputStream.readByte(); // max loop count
                readShort(dataInputStream); // loop start tick
            }
            short tick = -1;
            while (true) {
                short jumpTicks = readShort(dataInputStream);
                if (jumpTicks == 0) break;
                tick += jumpTicks;
                short layer = -1;
                while (true) {
                    short jumpLayers = readShort(dataInputStream);
                    if (jumpLayers == 0) break;
                    layer += jumpLayers;
                    byte instrument = dataInputStream.readByte();

                    if (firstCustomInstrumentDelta > 0 && instrument >= firstCustomInstrument){
                        instrument += (byte) firstCustomInstrumentDelta;
                    }

                    byte key = dataInputStream.readByte();

                    if (nbsversion >= 4) {
                        dataInputStream.readByte(); // note block velocity
                        dataInputStream.readByte(); // note block panning
                        readShort(dataInputStream); // note block pitch
                    }

                    setNote(layer, tick, instrument /* instrument */,
                            key/* note */, layerHashMap);
                }
            }

            if (nbsversion > 0 && nbsversion < 3) length = tick;

            for (int i = 0; i < songHeight; i++) {
                NBSLayer layer = layerHashMap.get(i);

                String name = readString(dataInputStream);
                if (nbsversion >= 4) dataInputStream.readByte(); // layer lock

                byte volume = dataInputStream.readByte();
                if (nbsversion >= 2) dataInputStream.readByte(); // layer stereo

                if (layer != null) {
                    layer.setName(name);
                    layer.setVolume(volume);
                }
            }

            byte customInstrumentAmount = dataInputStream.readByte();
            NBSCustomInstrument[] customInstrumentsArray = new NBSCustomInstrument[customInstrumentAmount];

            for (int index = 0; index < customInstrumentAmount; index++) {
                customInstrumentsArray[index] = new NBSCustomInstrument((byte) index,
                        readString(dataInputStream), readString(dataInputStream));
                dataInputStream.readByte(); // pitch
                dataInputStream.readByte(); // key
            }

            if (firstCustomInstrumentDelta < 0){
                ArrayList<NBSCustomInstrument> customInstruments = VersionUtils.getVersionCustomInstrumentsForSong(firstCustomInstrument);
                customInstruments.addAll(Arrays.asList(customInstrumentsArray));
                customInstrumentsArray = customInstruments.toArray(customInstrumentsArray);
            } else {
                firstCustomInstrument += firstCustomInstrumentDelta;
            }

            return new NBSSong(speed, layerHashMap, songHeight, length, title,
                    author, description, songFile, firstCustomInstrument, customInstrumentsArray);
        } catch (FileNotFoundException e) {
            Logger.log(LoggingLevel.ERROR, "The file provided could not be found. Please provide a valid file.");
        } catch (EOFException e) {
            Logger.log(LoggingLevel.ERROR, "File is corrupted or wrong NBS version/format: " + e.getMessage());
        } catch (IOException e) {
            Logger.log(LoggingLevel.ERROR, "Could not form IO link: " + e.getMessage());
        }
        return null;
    }

    /**
     * Sets a note object in the NBS layer hashmap
     * @param noteLayer The integer identifier of the note layer
     * @param ticks The ticks the note lasts for
     * @param instrument The ID of the instrument
     * @param key The byte for the note
     * @param layerHashMap The layer hashmap object
     */
    private static void setNote(int noteLayer, int ticks, byte instrument, byte key, HashMap<Integer, NBSLayer> layerHashMap) {
        NBSLayer layer = layerHashMap.get(noteLayer);
        if (layer == null) {
            layer = new NBSLayer();
            layerHashMap.put(noteLayer, layer);
        }
        layer.setNote(ticks, new NBSNote(instrument, key));
    }

    /**
     * Reads a value of a short from a DataInputStream
     * @param dis The DataInputStream object
     * @return The short value from the data stream
     * @throws IOException An IO exception thrown from not being able to read data stream
     */
    private static short readShort(DataInputStream dis) throws IOException {
        int byte1 = dis.readUnsignedByte();
        int byte2 = dis.readUnsignedByte();
        return (short) (byte1 + (byte2 << 8));
    }

    /**
     * Reads a value of an int from a DataInputStream
     * @param dis The DataInputStream object
     * @return The int value from the data stream
     * @throws IOException An IO exception thrown from not being able to read data stream
     */
    private static int readInt(DataInputStream dis) throws IOException {
        int byte1 = dis.readUnsignedByte();
        int byte2 = dis.readUnsignedByte();
        int byte3 = dis.readUnsignedByte();
        int byte4 = dis.readUnsignedByte();
        return (byte1 + (byte2 << 8) + (byte3 << 16) + (byte4 << 24));
    }

    /**
     * Reads a value of a string from a DataInputStream
     * @param dis The DataInputStream object
     * @return The string value from the data stream
     * @throws IOException An IO exception thrown from not being able to read data stream
     */
    private static String readString(DataInputStream dis) throws IOException {
        int length = readInt(dis);
        StringBuilder sb = new StringBuilder(length);
        for (; length > 0; --length) {
            char c = (char) dis.readByte();
            if (c == (char) 0x0D) {
                c = ' ';
            }
            sb.append(c);
        }
        return sb.toString();
    }
}
