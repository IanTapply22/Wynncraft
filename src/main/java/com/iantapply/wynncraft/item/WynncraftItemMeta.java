package com.iantapply.wynncraft.item;

import com.iantapply.wynncraft.Wynncraft;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

/**
 * Handles all interactions with the PDC data of an items meta
 */
public class WynncraftItemMeta {

    /**
     * Sets a string flag in the NBT data of the item
     * @param key The key to set
     * @param value The value to set the key to
     */
    public static void setStringFlag(String key, String value, ItemMeta itemMeta) {
        NamespacedKey nbtKey = new NamespacedKey(Wynncraft.getInstance(), key);
        if (itemMeta != null) {
            itemMeta.getPersistentDataContainer().set(nbtKey, PersistentDataType.STRING, value);
        }
    }

    /**
     * Gets a string flag from the NBT data of the item
     * @param key The key to get
     * @return The value of the key
     */
    public static String getStringFlag(String key, ItemMeta itemMeta) {
        NamespacedKey nbtKey = new NamespacedKey(Wynncraft.getInstance(), key);
        if (itemMeta != null) {
            PersistentDataContainer container = itemMeta.getPersistentDataContainer();
            return container.getOrDefault(nbtKey, PersistentDataType.STRING, "");
        }
        return "";
    }

    /**
     * Sets an integer flag in the NBT data of the item
     * @param key The key to set
     * @param value The value to set the key to
     */
    public static void setIntegerFlag(String key, int value, ItemMeta itemMeta) {
        NamespacedKey nbtKey = new NamespacedKey(Wynncraft.getInstance(), key);
        if (itemMeta != null) {
            itemMeta.getPersistentDataContainer().set(nbtKey, PersistentDataType.INTEGER, value);
        }
    }

    /**
     * Gets an integer flag from the NBT data of the item
     * @param key The key to get
     * @return The value of the key
     */
    public static int getIntegerFlag(String key, ItemMeta itemMeta) {
        NamespacedKey nbtKey = new NamespacedKey(Wynncraft.getInstance(), key);
        if (itemMeta != null) {
            PersistentDataContainer container = itemMeta.getPersistentDataContainer();
            return container.getOrDefault(nbtKey, PersistentDataType.INTEGER, -1);
        }
        return -1;
    }

    /**
     * Gets the PersistentDataContainer of the item
     * @return The PersistentDataContainer of the item
     */
    public static PersistentDataContainer getPersistentDataContainer(ItemStack itemStack) {
        ItemMeta meta = itemStack.getItemMeta();
        return meta != null ? meta.getPersistentDataContainer() : null;
    }

    /**
     * Checks if a flag exists in the NBT data of the item
     */
    public static boolean hasKey(String key, ItemMeta itemMeta) {
        NamespacedKey nbtKey = new NamespacedKey(Wynncraft.getInstance(), key);
        if (itemMeta != null) {
            PersistentDataContainer container = itemMeta.getPersistentDataContainer();
            return container.has(nbtKey, PersistentDataType.STRING);
        }
        return false;
    }
}
