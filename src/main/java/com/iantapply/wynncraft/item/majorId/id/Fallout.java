package com.iantapply.wynncraft.item.majorId.id;

import com.iantapply.wynncraft.item.majorId.MajorID;

public class Fallout extends MajorID {

    @Override
    public int id() {
        return 21;
    }

    @Override
    public String name() {
        return "Fallout";
    }

    @Override
    public String description() {
        return "<span class='font-ascii' style='color:#55FFFF'>+Fallout: <span class='font-ascii' style='color:#00AAAA'>Bomb Arrow's blast becomes giant. Damage, self-damage, and recoil are severely increased.</span></span>";
    }
}
