package com.iantapply.wynncraft.item.enums.advanced;

import lombok.Getter;

@Getter
public enum AttackSpeed {
    SUPER_SLOW("Super Slow"),
    VERY_SLOW("Very Slow"),
    SLOW("Slow"),
    NORMAL("Normal"),
    FAST("Fast"),
    VERY_FAST("Very Fast"),
    SUPER_FAST("Super Fast");

    private final String lore;

    /**
     * The lore that will be displayed on the item
     * @param lore The lore line displayed before the designated suffix in the lore setting
     */
    AttackSpeed(String lore) {
        this.lore = lore;
    }
}
