package com.iantapply.wynncraft.gui;

import com.iantapply.wynncraft.item.WynncraftItemMeta;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.UUID;

/**
 * A class that represents a clickable item in a GUI, both interactable and
 * non-interactable.
 */
public abstract class GUIClickableItemAbstract {
    public static HashMap<String, GUIClickableItemAbstract> itemData = new HashMap<>();
    private final String uuid;

    /**
     * Creates a new clickable item
     */
    public GUIClickableItemAbstract() {
        this.uuid = UUID.randomUUID().toString();
        itemData.put(uuid, this);
    }

    /**
     * Gets the finalized item with the GUI NBT data attached
     * @return The finalized item
     */
    public ItemStack getFinalizedItem() {
        ItemStack item = getItem();
        WynncraftItemMeta.setStringFlag(GUIHelpers.getClickableItemFlag(), uuid, item.getItemMeta());
        return item;
    }

    /**
     * What runs when the item is clicked
     * @param event The event that is fired when the item is clicked
     */
    public void run(InventoryClickEvent event) {
        event.setCancelled(true);
    }

    /**
     * The slot that the item is in
     * @return The slot that the item is in as an Integer
     */
    public int getSlot() {
        return 0;
    }

    /**
     * The item that is displayed in the GUI
     * @return The item as a WynncraftItem object
     */
    public abstract ItemStack getItem();

    /**
     * Whether the item can be picked up
     * @return Whether the item can be picked up
     */
    public boolean canPickup() {
        return false;
    }
}
