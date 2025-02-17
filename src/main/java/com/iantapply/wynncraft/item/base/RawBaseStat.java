package com.iantapply.wynncraft.item.base;

import com.iantapply.wynncraft.item.enums.stat.WeaponDamageBase;
import lombok.Getter;

// TODO: Support multiple types of enums for stats
@Getter
public class RawBaseStat extends BaseStatBase {
    private final WeaponDamageBase base;
    private final double value;

    public RawBaseStat(WeaponDamageBase base, int value) {
        this.base = base;
        this.value = value;
    }
}
