package com.iantapply.wynncraft.gui.item.filler;

import com.iantapply.wynncraft.gui.GUIClickableItem;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * An example class to represent how a premade filler item can be created
 */
public class CookieFillerItem {

    /**
     * The item to add as a filler
     * @param slot The slot to add the filler item to
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
            public ItemStack getItem() {
                ItemStack item = new ItemStack(new ItemStack(Material.COOKIE));
                ItemMeta meta = item.getItemMeta();
                meta.displayName(Component.text(" "));
                item.setItemMeta(meta);
                return item;
            }
        };
    }
}
