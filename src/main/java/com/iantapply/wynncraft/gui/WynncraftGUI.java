package com.iantapply.wynncraft.gui;

import com.iantapply.wynncraft.event.wynncraft.WynncraftEvent;
import com.iantapply.wynncraft.item.WynncraftNBTItem;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

/**
 * The WynncraftGUI class is an abstract class that is used to create custom GUIs
 * for the Wynncraft server easily and flawlessly.
 */
public abstract class WynncraftGUI {
    BukkitTask updater;

    /**
     * The inventory that is displayed to the player
     * @return The inventory as an Inventory object
     */
    public abstract Inventory inventory();

    /**
     * The name that is displayed as the GUI title
     * @return The name
     */
    public abstract Component name();

    /**
     * The number of slots that are present within the GUI
     * It should be a multiple of 9. 27 and 54 are common values.
     * @return The number of slots contained within the GUI
     */
    public abstract int slots();

    /**
     * The filler item that is used to fill empty slots in the GUI
     * @param slot The slot to add the border item to
     * @return The filler item as a WynncraftItem object
     */
    public abstract GUIClickableItem fillerItem(int slot);

    /**
     * The border item that is used to create a border around the GUI
     * @param slot The slot to add the border item to
     * @return The border item as a WynncraftItem object
     */
    public abstract GUIClickableItem borderItem(int slot);

    /**
     * The item that is displayed in the GUI but can't be picked up
     * @param item The item to remove the ability to pickup for
     * @param slot The slot that the item is in
     * @return The item as a WynncraftItem object
     */
    public abstract GUIClickableItem cantPickup(WynncraftNBTItem item, int slot);

    /**
     * The event that is fired when the GUI is opened or triggered
     * @return The event as a WynncraftEvent object
     */
    public WynncraftEvent triggerEvent() {
        return null;
    }

    /**
     * Determines what happens when the GUI is closed
     *
     * @param player The player to close the GUI menu for
     */
    public abstract void onClose(Player player);

    /**
     * Determines what happens when the GUI is opened
     *
     * @param player The player to open the GUI menu for
     */
    public abstract void open(Player player);

    /**
     * Determines what happens when the GUI is updated (this happens every 20 ticks)
     */
    public abstract void update();

    /**
     * Starts the GUI updater to update the GUI every 20 ticks
     * This should be executed upon the opening of the GUI
     *
     * @param plugin The plugin instance that will have the task time schedules thread attached to it
     */
    public void startUpdater(JavaPlugin plugin) {
        this.updater = Bukkit.getScheduler().runTaskTimer(plugin, this::update, 0, 20);
    }

    /**
     * Cancels the GUI updater to stop updating the GUI
     * This should be executed upon the closing of the GUI
     */
    public void stopUpdater() {
        this.updater.cancel();
    }

    /**
     * Adds an item to the GUI
     * @param item The item to add
     */
    public void addItem(GUIClickableItem item) {
        inventory().setItem(item.getSlot(), item.getFinalizedItem().getItem());
    }

    /**
     * Adds an item to the GUI at a specific slot
     * @param item The item to add
     * @param slot The slot to add the item to
     */
    public void addItem(GUIClickableItem item, int slot) {
        inventory().setItem(slot, item.getItem());
    }

    /**
     * Sets a border around the GUI with a specific item
     */
    public void setBorder() {
        int size = inventory().getSize();
        if (size < 27) return;

        for (int i = 0; i < 9; i++) {
            addItem(borderItem(i), i);
        }

        for(int i = 0; i < Math.ceil(size / 10.0); i++) {
            addItem(borderItem(i), (8 + (9 * i)));
            if(9 + (9 * i) > size - 1) continue;
            addItem(borderItem(i), (9 + (9 * i)));
        }

        for(int i = size - 9; i < size; i++) {
            addItem(borderItem(i), (i));
        }
    }

    /**
     * Fills all empty slots in the GUI with the filler item
     */
    public void fillEmptySlots() {
        for (int i = 0; i < slots(); i++) {
            if (inventory().getItem(i) == null) {
                inventory().setItem(i, fillerItem(i).getItem());
            }
        }
    }

    /**
     * Fills all slots in the GUI with the filler item
     */
    public void fillAllSlots() {
        for (int i = 0; i < slots(); i++) {
            inventory().setItem(i, fillerItem(i).getItem());
        }
    }
}
