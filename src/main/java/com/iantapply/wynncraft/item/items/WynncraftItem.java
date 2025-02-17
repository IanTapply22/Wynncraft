package com.iantapply.wynncraft.item.items;

import com.iantapply.wynncraft.item.WynncraftNBTItem;

public abstract class WynncraftItem {

    /**
     * Used for building the Wynncraft item with NBT data.
     * This includes lore and the entire visuals and properties of the item.
     * @return The built Wynncraft item as an NBT item
     */
    public abstract WynncraftNBTItem build();
}
