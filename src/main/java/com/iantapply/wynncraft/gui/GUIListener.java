package com.iantapply.wynncraft.gui;

import com.iantapply.wynncraft.inventory.WynncraftItem;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * A listener to handle GUI click events
 */
public class GUIListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getCurrentItem() == null) {
            return;
        }

        WynncraftItem item = (WynncraftItem) event.getCurrentItem();

        if (item.getType() != Material.AIR) {
            return;
        }

        if (!item.hasKey(GUIHelpers.getClickableItemFlag())) {
            return;
        }

        // Get the clickable item and run it, and/or cancel it if it can't be picked up
        GUIClickableItem clickableItem = GUIClickableItem.itemData.get(item.getStringFlag(GUIHelpers.getClickableItemFlag()));
        clickableItem.run(event);
        if (!clickableItem.canPickup()) {
            event.setCancelled(true);
        }
    }
}
