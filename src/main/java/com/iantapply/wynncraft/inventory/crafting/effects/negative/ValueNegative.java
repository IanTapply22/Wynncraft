package com.iantapply.wynncraft.inventory.crafting.effects.negative;

import com.iantapply.wynncraft.inventory.crafting.effects.base.negative.NegativeValue;
import lombok.Getter;
import lombok.Setter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

@Getter @Setter
public class ValueNegative extends NegativeValue {
    private String name;
    private String nbtKey;
    private int value;

    /**
     * Create a new value stat
     * @param name The name of the stat
     * @param nbtKey The NBT key to store the stat as in the item
     * @param value The value of the stat
     */
    public ValueNegative(String name, String nbtKey, int value) {
        this.name = name;
        this.nbtKey = nbtKey;
        this.value = value;
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public String nbtKey() {
        return this.nbtKey;
    }

    @Override
    public Component lore() {
        return Component.text(this.value).color(NamedTextColor.RED)
                        .append(Component.text(" ")
                        .append(Component.text(this.name).color(NamedTextColor.RED)));
    }
}
