package com.iantapply.wynncraft.inventory.crafting.effects.positive;

import com.iantapply.wynncraft.inventory.crafting.effects.base.positive.PositiveValue;
import lombok.Getter;
import lombok.Setter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

@Getter @Setter
public class DurationPositive extends PositiveValue {
    private String name;
    private String nbtKey;
    private int value;

    public DurationPositive(String name, String nbtKey, int value) {
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
        return Component.text(this.value).color(NamedTextColor.GREEN)
                .append(Component.text("s ").color(NamedTextColor.GREEN)
                        .append(Component.text(this.name)).color(NamedTextColor.GRAY));
    }
}
