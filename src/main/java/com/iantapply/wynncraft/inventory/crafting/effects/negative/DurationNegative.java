package com.iantapply.wynncraft.inventory.crafting.effects.negative;

import com.iantapply.wynncraft.inventory.crafting.effects.base.negative.NegativeValue;
import lombok.Getter;
import lombok.Setter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

/**
 * This will look like "Value:TypeId:EffectId" under the key of a random UUID
 * You can find a list of TypeIds in the {@link com.iantapply.wynncraft.inventory.crafting.effects.base.EffectValueType} enum
 */
@Getter @Setter
public class DurationNegative extends NegativeValue {
    private String name;
    private String nbtKey;
    private int value;

    public DurationNegative(String name, String nbtKey, int value) {
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
                        .append(Component.text("s ").color(NamedTextColor.RED)
                        .append(Component.text(this.name)).color(NamedTextColor.RED));
    }
}
