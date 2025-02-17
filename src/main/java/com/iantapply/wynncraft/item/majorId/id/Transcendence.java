package com.iantapply.wynncraft.item.majorId.id;

import com.iantapply.wynncraft.item.majorId.MajorID;

public class Transcendence extends MajorID {

    @Override
    public int id() {
        return 9;
    }

    @Override
    public String name() {
        return "Transcendence";
    }

    @Override
    public String description() {
        return "<span class='font-ascii' style='color:#55FFFF'>+Transcendence: <span class='font-ascii' style='color:#00AAAA'>30% chance for spells to cost no mana when casted.</span></span>";
    }
}
