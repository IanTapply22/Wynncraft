package com.iantapply.wynncraft.inventory.crafting;

import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;

@Getter
public enum CraftingIngredientTier {
    TIER_0("Tier 0", 0, Component.text("[", NamedTextColor.GRAY)
            .append(Component.text("✫✫✫", NamedTextColor.DARK_GRAY))
            .append(Component.text("]", NamedTextColor.GRAY))),
    TIER_1("Tier 1", 1, Component.text("[", NamedTextColor.GOLD)
            .append(Component.text("✫", NamedTextColor.YELLOW))
            .append(Component.text("✫✫", NamedTextColor.DARK_GRAY))
            .append(Component.text("]", NamedTextColor.GOLD))),
    TIER_2("Tier 2", 2, Component.text("[", NamedTextColor.DARK_PURPLE)
            .append(Component.text("✫✫", NamedTextColor.LIGHT_PURPLE))
            .append(Component.text("✫", NamedTextColor.DARK_GRAY))
            .append(Component.text("]", NamedTextColor.DARK_PURPLE))),
    TIER_3("Tier 3", 3, Component.text("[", NamedTextColor.DARK_AQUA)
            .append(Component.text("✫✫✫", NamedTextColor.AQUA))
            .append(Component.text("]", NamedTextColor.DARK_AQUA)));

    private final String tierName;
    private final Integer tier;
    private final TextComponent display;

    CraftingIngredientTier(String tierName, Integer tier, TextComponent display) {
        this.tierName = tierName;
        this.tier = tier;
        this.display = display;
    }
}
