package com.iantapply.wynncraft.item.identification;

import com.iantapply.wynncraft.item.enums.stat.SkillStat;
import lombok.Getter;

@Getter
public class RawIdentification {
    private final SkillStat identification;
    private final int value;

    public RawIdentification(SkillStat identification, int value) {
        this.identification = identification;
        this.value = value;
    }
}
