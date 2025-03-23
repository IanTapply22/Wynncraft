package com.iantapply.wynncraft.character;

import lombok.Getter;

@Getter
public enum Skills {
    STRENGTH(0, "strength"),
    DEXTERITY(1, "dexterity"),
    INTELLIGENCE(2, "intelligence"),
    DEFENSE(3, "defense"),
    AGILITY(4, "agility");

    public final int id;
    public final String name;

    Skills(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
