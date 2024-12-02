package com.iantapply.wynncraft.nbs.handling;

import com.iantapply.wynncraft.logger.Logger;
import com.iantapply.wynncraft.logger.LoggingLevel;
import com.iantapply.wynncraft.nbs.CustomInstrument;
import com.iantapply.wynncraft.nbs.utils.VersionUtils;
import com.iantapply.wynncraft.nbs.utils.InstrumentUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

// TODO: Better error handling and rework/lighten up
public class NBSFormatDecoder {

    public static NBSSong parse(File decodeFile) {
        try {
            return parse(new FileInputStream(decodeFile), decodeFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static NBSSong parse(InputStream inputStream) {
        return parse(inputStream, null);
    }

    private static NBSSong parse(InputStream inputStream, File songFile) {
        HashMap<Integer, NBSLayer> layerHashMap = new HashMap<>();
        try {
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            short length = readShort(dataInputStream);
            int firstcustominstrument = 10;
            int firstcustominstrumentdiff;
            int nbsversion = 0;
            if (length == 0) {
                nbsversion = dataInputStream.readByte();
                firstcustominstrument = dataInputStream.readByte();
                if (nbsversion >= 3) {
                    length = readShort(dataInputStream);
                }
            }
            firstcustominstrumentdiff = InstrumentUtils.getCustomInstrumentFirstIndex() - firstcustominstrument;
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

                    if (firstcustominstrumentdiff > 0 && instrument >= firstcustominstrument){
                        instrument += firstcustominstrumentdiff;
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

            if (nbsversion > 0 && nbsversion < 3) {
                length = tick;
            }

            for (int i = 0; i < songHeight; i++) {
                NBSLayer layer = layerHashMap.get(i);

                String name = readString(dataInputStream);
                if (nbsversion >= 4){
                    dataInputStream.readByte(); // layer lock
                }

                byte volume = dataInputStream.readByte();
                if (nbsversion >= 2){
                    dataInputStream.readByte(); // layer stereo
                }
                if (layer != null) {
                    layer.setName(name);
                    layer.setVolume(volume);
                }
            }

            byte customInstrumentAmount = dataInputStream.readByte();
            CustomInstrument[] customInstrumentsArray = new CustomInstrument[customInstrumentAmount];

            for (int index = 0; index < customInstrumentAmount; index++) {
                customInstrumentsArray[index] = new CustomInstrument((byte) index,
                        readString(dataInputStream), readString(dataInputStream));
                dataInputStream.readByte(); // pitch
                dataInputStream.readByte(); // key
            }

            if (firstcustominstrumentdiff < 0){
                ArrayList<CustomInstrument> customInstruments = VersionUtils.getVersionCustomInstrumentsForSong(firstcustominstrument);
                customInstruments.addAll(Arrays.asList(customInstrumentsArray));
                customInstrumentsArray = customInstruments.toArray(customInstrumentsArray);
            } else {
                firstcustominstrument += firstcustominstrumentdiff;
            }

            return new NBSSong(speed, layerHashMap, songHeight, length, title,
                    author, description, songFile, firstcustominstrument, customInstrumentsArray);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (EOFException e) {
            Logger.log(LoggingLevel.ERROR, "File is corrupted: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void setNote(int layer, int ticks, byte instrument, byte key, HashMap<Integer, NBSLayer> layerHashMap) {
        NBSLayer l = layerHashMap.get(layer);
        if (l == null) {
            l = new NBSLayer();
            layerHashMap.put(layer, l);
        }
        l.setNote(ticks, new NBSNote(instrument, key));
    }

    private static short readShort(DataInputStream dis) throws IOException {
        int byte1 = dis.readUnsignedByte();
        int byte2 = dis.readUnsignedByte();
        return (short) (byte1 + (byte2 << 8));
    }

    private static int readInt(DataInputStream dis) throws IOException {
        int byte1 = dis.readUnsignedByte();
        int byte2 = dis.readUnsignedByte();
        int byte3 = dis.readUnsignedByte();
        int byte4 = dis.readUnsignedByte();
        return (byte1 + (byte2 << 8) + (byte3 << 16) + (byte4 << 24));
    }

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
