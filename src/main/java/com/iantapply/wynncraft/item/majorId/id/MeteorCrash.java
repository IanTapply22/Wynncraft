package com.iantapply.wynncraft.item.majorId.id;

import com.iantapply.wynncraft.item.majorId.MajorID;

public class MeteorCrash extends MajorID {

    @Override
    public int id() {
        return 19;
    }

    @Override
    public String name() {
        return "Meteor Crash";
    }

    @Override
    public String description() {
        return "<span class='font-ascii' style='color:#55FFFF'>+Meteor Crash: <span class='font-ascii' style='color:#00AAAA'>Burning Sigil deals more damage in a massively increased radius, but lasts half as long.</span></span>";
    }
}
