package com.iantapply.wynncraft.item.enums.tier;

import lombok.Getter;

@Getter
public enum IngredientTier {
    ZERO(0),
    ONE(1),
    TWO(2),
    THREE(2);

    private final int tier;

    IngredientTier(int tier) {
        this.tier = tier;
    }
}
