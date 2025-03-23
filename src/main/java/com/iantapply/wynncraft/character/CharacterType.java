package com.iantapply.wynncraft.character;

import lombok.Getter;

@Getter
public enum CharacterType {
    ARCHER(0),
    WARRIOR(1),
    MAGE(2),
    ASSASSIN(3),
    SHAMAN(4);

    public final int id;

    CharacterType(int id) {
        this.id = id;
    }
}
