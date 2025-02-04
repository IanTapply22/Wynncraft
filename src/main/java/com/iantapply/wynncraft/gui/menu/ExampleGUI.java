package com.iantapply.wynncraft.gui.menu;

import com.iantapply.wynncraft.gui.GUIClickableItem;
import com.iantapply.wynncraft.gui.WynncraftGUI;
import com.iantapply.wynncraft.gui.item.border.BlackGlassPaneBorder;
import com.iantapply.wynncraft.gui.item.filler.CookieFillerItem;
import com.iantapply.wynncraft.inventory.WynncraftItem;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class ExampleGUI extends WynncraftGUI {
    Inventory inventory;

    /**
     * The inventory that is displayed to the player
     *
     * @return The inventory as an Inventory object
     */
    @Override
    public Inventory inventory() {
        this.inventory = Bukkit.createInventory(null, slots(), name());
        return this.inventory;
    }

    /**
     * The name that is displayed as the GUI title
     *
     * @return The name as a String object
     */
    @Override
    public Component name() {
        return Component.text("Example GUI");
    }

    /**
     * The number of slots that are present within the GUI
     * It should be a multiple of 9. 27 and 54 are common values.
     *
     * @return The number of slots contained within the GUI
     */
    @Override
    public int slots() {
        return 27;
    }

    /**
     * The filler item that is used to fill empty slots in the GUI
     *
     * @return The filler item as a WynncraftItem object
     */
    @Override
    public GUIClickableItem fillerItem(int slot) {
        return CookieFillerItem.getItem(slot);
    }

    /**
     * The item to add to the border
     * @param slot The slot to add the border item to
     * @return The item that will fill the selected slot
     */
    @Override
    public GUIClickableItem borderItem(int slot) {
        return BlackGlassPaneBorder.getItem(slot);
    }

    @Override
    public GUIClickableItem cantPickup(WynncraftItem currentItem, int slot) {
        return new GUIClickableItem() {
            @Override
            public void run(InventoryClickEvent event) {
                event.setCancelled(true);
            }

            @Override
            public int getSlot() {
                return slot;
            }

            @Override
            public WynncraftItem getItem() {
                return currentItem;
            }
        };
    }

    /**
     * Determines what happens when the GUI is closed
     *
     * @param player The player that closed the GUI
     */
    @Override
    public void onClose(Player player) {
        player.sendMessage("You closed the GUI!");
    }

    /**
     * Determines what happens when the GUI is opened
     *
     * @param player The player that opened the GUI
     */
    @Override
    public void open(Player player) {
        fillAllSlots();
    }

    /**
     * Determines what happens when the GUI is updated (this happens every 20 ticks)
     */
    @Override
    public void update() {}
}
