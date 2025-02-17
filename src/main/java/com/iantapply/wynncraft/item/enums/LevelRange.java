package com.iantapply.wynncraft.item.enums;

import lombok.Getter;

@Getter
public enum LevelRange {
    ITEMS(110),
    INGREDIENTS(105);

    private final int level;

    LevelRange(int level) {
        this.level = level;
    }
}
