package com.iantapply.wynncraft.character;

import lombok.Getter;

@Getter
public enum CharacterReskin {
    HUNTER(0, "hunter"),
    KNIGHT(1, "knight"),
    NINJA(2, "ninja"),
    DARK_WIZARD(3, "dark_wizard"),
    SKYSEER(4, "skyseer");

    public final int id;
    public final String name;

    CharacterReskin(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
