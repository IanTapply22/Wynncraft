package com.iantapply.wynncraft.item.majorId.id;

import com.iantapply.wynncraft.item.majorId.MajorID;

public class Tackle extends MajorID {

    @Override
    public int id() {
        return 20;
    }

    @Override
    public String name() {
        return "Tackle";
    }

    @Override
    public String description() {
        return "<span class='font-ascii' style='color:#55FFFF'>+Tackle: <span class='font-ascii' style='color:#00AAAA'>Flying Kick deals much more knockback and Collide deals much more damage on impact.</span></span>";
    }
}
