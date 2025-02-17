package com.iantapply.wynncraft.item;

import com.iantapply.wynncraft.Wynncraft;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

/**
 * An extension of an Item with the utilities to manage NBT
 * data easier and more efficiently
 */
@Getter
@Setter
public class WynncraftNBTItem extends ItemStack {
    private ItemStack item;

    /**
     * Creates a new WynncraftItem with NBT data from an existing ItemStack
     * @param item The item to create the WynncraftItem from
     */
    public WynncraftNBTItem(ItemStack item) {
        this.item = item;
    }

    /**
     * Checks if the item has a key in its NBT data
     * @param key The key to check for
     * @return Whether the key exists in the NBT data
     */
    public boolean hasKey(String key) {
        NamespacedKey nbtKey = new NamespacedKey(Wynncraft.getInstance(), key);
        PersistentDataContainer container = item.getItemMeta().getPersistentDataContainer();

        return container.getKeys().contains(nbtKey);
    }

    /**
     * Sets a string flag in the NBT data of the item
     * @param key The key to set
     * @param value The value to set the key to
     */
    public void setStringFlag(String key, String value) {
        NamespacedKey nbtKey = new NamespacedKey(Wynncraft.getInstance(), key);

        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().set(nbtKey, PersistentDataType.STRING, value);

        this.item.setItemMeta(meta);
    }

    /**
     * Gets a string flag from the NBT data of the item
     * @param key The key to get
     * @return The value of the key
     */
    public String getStringFlag(String key) {
        NamespacedKey nbtKey = new NamespacedKey(Wynncraft.getInstance(), key);
        PersistentDataContainer container = item.getItemMeta().getPersistentDataContainer();

        if (!container.has(nbtKey, PersistentDataType.STRING)) return null;

        return container.get(nbtKey, PersistentDataType.STRING);
    }

    /**
     * Sets an integer flag in the NBT data of the item
     * @param key The key to set
     * @param value The value to set the key to
     */
    public void setIntegerFlag(String key, int value) {
        NamespacedKey nbtKey = new NamespacedKey(Wynncraft.getInstance(), key);

        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().set(nbtKey, PersistentDataType.INTEGER, value);

        this.item.setItemMeta(meta);
    }

    /**
     * Gets an integer flag from the NBT data of the item
     * @param key The key to get
     * @return The value of the key
     */
    public int getIntegerFlag(String key) {
        NamespacedKey nbtKey = new NamespacedKey(Wynncraft.getInstance(), key);
        PersistentDataContainer container = item.getItemMeta().getPersistentDataContainer();

        if (!container.has(nbtKey, PersistentDataType.INTEGER)) return 0;

        return container.get(nbtKey, PersistentDataType.INTEGER);
    }

    /**
     * Sets a double flag in the NBT data of the item
     * @param key The key to set
     * @param value The value to set the key to
     */
    public void setDoubleFlag(String key, double value) {
        NamespacedKey nbtKey = new NamespacedKey(Wynncraft.getInstance(), key);

        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().set(nbtKey, PersistentDataType.DOUBLE, value);

        this.item.setItemMeta(meta);
    }

    /**
     * Gets a double flag from the NBT data of the item
     * @param key The key to get
     * @return The value of the key
     */
    public double getDoubleFlag(String key) {
        NamespacedKey nbtKey = new NamespacedKey(Wynncraft.getInstance(), key);
        PersistentDataContainer container = item.getItemMeta().getPersistentDataContainer();

        if (!container.has(nbtKey, PersistentDataType.DOUBLE)) return 0.0;

        return container.get(nbtKey, PersistentDataType.DOUBLE);
    }

    /**
     * Sets a boolean flag in the NBT data of the item
     * @param key The key to set
     * @param value The value to set the key to
     */
    public void setBooleanFlag(String key, boolean value) {
        NamespacedKey nbtKey = new NamespacedKey(Wynncraft.getInstance(), key);

        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().set(nbtKey, PersistentDataType.BOOLEAN, value);

        this.item.setItemMeta(meta);
    }

    /**
     * Gets a boolean flag from the NBT data of the item
     * @param key The key to get
     * @return The value of the key
     */
    public boolean getBooleanFlag(String key) {
        NamespacedKey nbtKey = new NamespacedKey(Wynncraft.getInstance(), key);
        PersistentDataContainer container = item.getItemMeta().getPersistentDataContainer();

        if (!container.has(nbtKey, PersistentDataType.BOOLEAN)) return false;

        return Boolean.TRUE.equals(container.get(nbtKey, PersistentDataType.BOOLEAN));
    }
}
