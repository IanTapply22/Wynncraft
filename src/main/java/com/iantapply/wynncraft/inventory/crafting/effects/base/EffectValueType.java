package com.iantapply.wynncraft.inventory.crafting.effects.base;

import lombok.Getter;

@Getter
public enum EffectValueType {
    PERCENT(0, "Percent", "%"),
    FLAT(1, "Flat", ""),
    DURATION(2, "Duration", "s");

    private final int id;
    private final String name;
    private final String suffix;

    EffectValueType(int id, String name, String suffix) {
        this.id = id;
        this.name = name;
        this.suffix = suffix;
    }
}
