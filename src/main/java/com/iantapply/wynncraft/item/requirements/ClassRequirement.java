package com.iantapply.wynncraft.item.requirements;

import lombok.Getter;

@Getter
public enum ClassRequirement {
    ARCHER("Archer/Hunter"),
    WARRIOR("Warrior/Knight"),
    ASSASSIN("Assassin/Ninja"),
    MAGE("Mage/Dark Wizard"),
    SHAMAN("Shaman/Skyseer");

    private final String lore;

    ClassRequirement(String lore) {
        this.lore = lore;
    }
}
