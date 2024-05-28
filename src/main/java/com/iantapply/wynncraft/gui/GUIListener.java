package com.iantapply.wynncraft.gui;

import com.iantapply.wynncraft.gui.item.GUIClickableItem;
import com.iantapply.wynncraft.inventory.WynncraftItem;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

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

        GUIClickableItem clickableItem = GUIClickableItem.items.get(item.getStringFlag(GUIHelpers.getClickableItemFlag()));
        clickableItem.run(event);
        if (!clickableItem.canPickup()) {
            event.setCancelled(true);
        }
    }
}
