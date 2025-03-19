package com.iantapply.wynncraft.gui.item.border;

import com.iantapply.wynncraft.gui.GUIClickableItemAbstract;
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
    public static GUIClickableItemAbstract getItem(int slot) {
        return new GUIClickableItemAbstract() {
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
                ItemStack item = new ItemStack(new ItemStack(Material.BLACK_STAINED_GLASS_PANE));
                ItemMeta meta = item.getItemMeta();
                meta.displayName(Component.text(" "));
                item.setItemMeta(meta);
                return item;
            }
        };
    }
}
