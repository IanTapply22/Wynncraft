package com.iantapply.wynncraft.item.requirements;

import lombok.Getter;

@Getter
public class LevelRange {
    private final int min;
    private final int max;

    public LevelRange(int min, int max) {
        this.min = min;
        this.max = max;
    }
}
