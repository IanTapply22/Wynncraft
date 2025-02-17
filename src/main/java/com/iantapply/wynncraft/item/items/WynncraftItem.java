package com.iantapply.wynncraft.item.items;

import com.iantapply.wynncraft.item.enums.ItemType;
import com.iantapply.wynncraft.item.icon.ItemIcon;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public abstract class WynncraftItem {

    /**
     * The UUID of the item, this should differ from every other item and should be unique
     * @return The UUID of the item
     */
    public abstract UUID uuid();

    /**
     * The latest version of the item. This will trigger lore and items to update if the version is different
     * @return The version of the item (i.e. 1.0.0.0)
     */
    public abstract String version();

    /**
     * The internal name of the item, this is also what's displayed on the item
     * @return The internal name of the item
     */
    public abstract String internalName();

    /**
     * The type of item it is, this will determine the handling of the item
     * @return The type of item
     */
    public abstract ItemType type();

    /**
     * The icon of the item, this is what the physical item is
     * @return The icon of the item
     */
    public abstract ItemIcon icon();

    /**
     * Used for building the Wynncraft item with PDC data
     * This will rebuild lore if PDC data exists or will initially create it if it doesn't
     * This includes lore and the entire visuals and properties of the item.
     * @return The built Wynncraft item as an NBT item
     */
    public abstract ItemStack build(ItemStack currentItem);
}
