package com.iantapply.wynncraft.pack;

import com.iantapply.wynncraft.Wynncraft;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ResourcePackHelpers {

    /**
     * Gets a file with a hex hash from the uploaded directory
     * @param hexHash hex hash to find
     * @return byte array for that file
     */
    public static byte[] getFileFromDirectory(String hexHash) {
        File file = new File(String.format("%s%s%s%s%s.zip", Wynncraft.getInstance().getDataFolder(), File.separator, "resource_packs", File.separator, hexHash));

        if (!file.exists()) return null;

        try {
            return Files.readAllBytes(file.toPath());
        } catch (IOException ioTrouble) {
            return null;
        }
    }

    /**
     * Gets the SHA1 hash of a file
     * @param hexHash hex hash to find
     * @return SHA1 hash of the file
     */
    public static String getFileSha1Hash(String hexHash) {
        File file = new File(String.format("%s%s%s%s%s.zip", Wynncraft.getInstance().getDataFolder(), File.separator, "resource_packs", File.separator, hexHash));

        if (!file.exists()) return null;

        try {
            return org.apache.commons.codec.digest.DigestUtils.sha1Hex(Files.readAllBytes(file.toPath()));
        } catch (IOException ioTrouble) {
            return null;
        }
    }
}
