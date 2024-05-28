package com.iantapply.wynncraft.gui.item;

import com.iantapply.wynncraft.gui.GUIHelpers;
import com.iantapply.wynncraft.inventory.WynncraftItem;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.HashMap;
import java.util.UUID;

public abstract class GUIClickableItem {
    public static HashMap<String, GUIClickableItem> items = new HashMap<>();
    private final String uuid;

    /**
     * Creates a new clickable item
     */
    public GUIClickableItem() {
        this.uuid = UUID.randomUUID().toString();
        items.put(uuid, this);
    }

    /**
     * Gets the finalized item with the GUI NBT data attached
     * @return The finalized item
     */
    public WynncraftItem getFinalizedItem() {
        WynncraftItem item = getItem();
        item.setStringFlag(GUIHelpers.getClickableItemFlag(), uuid);
        return item;
    }

    /**
     * What runs when the item is clicked
     * @param event The event that is fired when the item is clicked
     */
    public abstract void run(InventoryClickEvent event);

    /**
     * The slot that the item is in
     * @return The slot that the item is in as an Integer
     */
    public abstract int getSlot();

    /**
     * The item that is displayed in the GUI
     * @return The item as a WynncraftItem object
     */
    public abstract WynncraftItem getItem();

    /**
     * Whether the item can be picked up
     * @return Whether the item can be picked up
     */
    public boolean canPickup() {
        return false;
    }
}
