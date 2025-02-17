package com.iantapply.wynncraft.item.identification;

import com.iantapply.wynncraft.item.enums.stat.StatType;
import lombok.Getter;

@Getter
public class Identification {
    private final StatType identification;
    private final int min;
    private final int max;
    private final int raw;
    private final IdentificationUnit unit;

    public Identification(StatType identification, int min, int max, int raw, IdentificationUnit unit) {
        this.identification = identification;
        this.min = min;
        this.max = max;
        this.raw = raw;
        this.unit = unit;
    }
}
