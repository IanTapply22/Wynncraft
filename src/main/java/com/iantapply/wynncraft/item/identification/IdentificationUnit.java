package com.iantapply.wynncraft.item.identification;

import lombok.Getter;

@Getter
public enum IdentificationUnit {
    FLAT(""),
    PERCENTAGE("%"),
    PER_THREE_SECONDS("/3s"),
    TIER(" tier");

    private final String suffix;

    IdentificationUnit(String suffix) {
        this.suffix = suffix;
    }
}
