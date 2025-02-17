package com.iantapply.wynncraft.item.enums.tier;

import lombok.Getter;
import net.kyori.adventure.text.format.NamedTextColor;

@Getter
public enum ItemRarity {
    DEPRESSING(NamedTextColor.GRAY),
    NORMAL(NamedTextColor.WHITE),
    UNIQUE(NamedTextColor.YELLOW),
    RARE(NamedTextColor.LIGHT_PURPLE),
    LEGENDARY(NamedTextColor.AQUA),
    FABLED(NamedTextColor.RED),
    MYTHIC(NamedTextColor.DARK_PURPLE),
    SET(NamedTextColor.DARK_GREEN);

    private final NamedTextColor color;

    ItemRarity(NamedTextColor color) {
        this.color = color;
    }
}
