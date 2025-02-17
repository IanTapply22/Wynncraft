package com.iantapply.wynncraft.item.enums.stat;

import lombok.Getter;
import net.kyori.adventure.text.format.NamedTextColor;

@Getter
public enum WeaponDamageBase {
    BASE_DAMAGE("✣", "Neutral Damage", NamedTextColor.GOLD, "minecraft:default"),
    BASE_EARTH_DAMAGE("✤", "Earth", NamedTextColor.DARK_GREEN, "minecraft:default"),
    BASE_THUNDER_DAMAGE("✦", "Thunder", NamedTextColor.YELLOW, "minecraft:default"),
    BASE_WATER_DAMAGE("❉", "Water", NamedTextColor.AQUA, "minecraft:default"),
    BASE_FIRE_DAMAGE("✹", "Fire", NamedTextColor.RED, "minecraft:default"),
    BASE_AIR_DAMAGE("❋", "Air", NamedTextColor.WHITE, "minecraft:default"),;

    private final String icon;
    private final String damageIdentifier;
    private final NamedTextColor color;
    private final String font;

    WeaponDamageBase(String icon, String damageIdentifier, NamedTextColor color, String font) {
        this.icon = icon;
        this.damageIdentifier = damageIdentifier;
        this.color = color;
        this.font = font;
    }
}
