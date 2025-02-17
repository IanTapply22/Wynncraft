package com.iantapply.wynncraft.item.majorId.id;

import com.iantapply.wynncraft.item.majorId.MajorID;

public class ExplosiveImpact extends MajorID {

    @Override
    public int id() {
        return 14;
    }

    @Override
    public String name() {
        return "Explosive Impact";
    }

    @Override
    public String description() {
        return "<span class='font-ascii' style='color:#55FFFF'>+Explosive Impact: <span class='font-ascii' style='color:#00AAAA'>Your Exploding ID can trigger when hitting mobs with your Main Attack.</span></span>";
    }
}
