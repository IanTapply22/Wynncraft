package com.iantapply.wynncraft.item.majorId.id;

import com.iantapply.wynncraft.item.majorId.MajorID;

public class SoulEater extends MajorID {

    @Override
    public int id() {
        return 5;
    }

    @Override
    public String name() {
        return "Soul Eater";
    }

    @Override
    public String description() {
        return "<span class='font-ascii' style='color:#55FFFF'>+Soul Eater: <span class='font-ascii' style='color:#00AAAA'>Devour and Harvester grant double mana. Decreases maximum marks by 1.</span></span>";
    }
}
