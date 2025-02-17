package com.iantapply.wynncraft.item.base;

import com.iantapply.wynncraft.item.enums.stat.WeaponDamageBase;
import lombok.Getter;

// TODO: Support multiple types of enums for stats
@Getter
public class BaseStat extends BaseStatBase {
    private final WeaponDamageBase base;
    private final int min;
    private final int max;
    private final int raw;

    public BaseStat(WeaponDamageBase base, int min, int max, int raw) {
        this.base = base;
        this.min = min;
        this.max = max;
        this.raw = raw;
    }
}