package com.iantapply.wynncraft.item.majorId.id;

import com.iantapply.wynncraft.item.majorId.MajorID;

public class Lockdown extends MajorID {

    @Override
    public int id() {
        return 6;
    }

    @Override
    public String name() {
        return "Lockdown";
    }

    @Override
    public String description() {
        return "<span class='font-ascii' style='color:#55FFFF'>+Lockdown: <span class='font-ascii' style='color:#00AAAA'>Meteor will snap onto a target in range, regardless of aim.</span></span>";
    }
}
