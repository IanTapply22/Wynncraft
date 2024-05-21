package com.iantapply.wynncraft.inventory.crafting.effects.base.positive;

import net.kyori.adventure.text.Component;

public abstract class PositiveValue {

    /**
     * The name of the effect
     * @return The name
     */
    public String name() {
        return null;
    }

    /**
     * The key for the NBT tag that will be used to store the effect
     *
     * @return The NBT key
     */
    public String nbtKey() {
        return null;
    }

    /**
     * The lore that will be displayed on the item
     * @return The lore
     */
    public Component lore() {
        return null;
    }
}
