package com.iantapply.wynncraft.character;

import lombok.Getter;

@Getter
public enum Gamemode {
    IRONMAN(0, "ironman");

    private final int id;
    private final String name;

    private Gamemode(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
