package com.iantapply.wynncraft.item.enums.stat;

import lombok.Getter;

@Getter
public enum SkillStat {
    RAW_INTELLIGENCE("Intelligence"),
    RAW_DEFENCE("Defence"),
    RAW_AGILITY("Agility"),
    RAW_STRENGTH("Strength"),
    RAW_DEXTERITY("Dexterity");

    private final String name;

    SkillStat(String name) {
        this.name = name;
    }
}
