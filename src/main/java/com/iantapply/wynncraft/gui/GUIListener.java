package com.iantapply.wynncraft.gui;

import com.iantapply.wynncraft.item.WynncraftItemMeta;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

/**
 * A listener to handle GUI click events
 */
public class GUIListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getCurrentItem() == null) return;

        ItemStack item = new ItemStack(event.getCurrentItem());

        if (item.getType() == Material.AIR) return;

        if (!WynncraftItemMeta.hasKey(GUIHelpers.getClickableItemFlag(), item.getItemMeta())) return;
        // Get the clickable item and run it, and/or cancel it if it can't be picked up
        GUIClickableItem clickableItem = GUIClickableItem.itemData.get(WynncraftItemMeta.getStringFlag(GUIHelpers.getClickableItemFlag(), item.getItemMeta()));
        clickableItem.run(event);
        if (!clickableItem.canPickup()) {
            event.setCancelled(true);
        }
    }
}
