package com.iantapply.wynncraft.gui.item.border;

import com.iantapply.wynncraft.gui.GUIClickableItem;
import com.iantapply.wynncraft.inventory.WynncraftItem;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * An example class to represent how a premade border item can be created
 */
public class BlackGlassPaneBorder {

    /**
     * The item to add to the border
     * @param slot The slot to add the border item to
     * @return The item that will fill the selected slot
     */
    public static GUIClickableItem getItem(int slot) {
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
                WynncraftItem item = new WynncraftItem(new ItemStack(Material.BLACK_STAINED_GLASS_PANE));
                ItemMeta meta = item.getItemMeta();
                meta.displayName(Component.text(" "));
                item.setItemMeta(meta);
                return item;
            }
        };
    }
}
