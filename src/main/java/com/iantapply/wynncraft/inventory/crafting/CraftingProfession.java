package com.iantapply.wynncraft.inventory.crafting;

import lombok.Getter;

@Getter
public enum CraftingProfession {
    WEAPONSMITHING(0, "Weaponsmithing", "⚔️"),
    TAILORING(1, "Tailoring", "🧵"),
    WOODWORKING(2, "Woodworking", "🪵"),
    ARMOURING(3, "Armouring", "🛡️"),
    SCRIBING(4, "Scribing", "📜"),
    JEWLING(5, "Jewling", "💍"),
    ALCHEMISM(6 , "Alchemism", "🧪"),
    COOKING(7, "Cooking", "🍳");

    private final Integer id;
    private final String name;
    private final String icon;

    CraftingProfession(Integer id, String name, String icon) {
        this.id = id;
        this.name = name;
        this.icon = icon;
    }
}
