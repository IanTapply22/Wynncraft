package com.iantapply.wynncraft.item.majorId.id;

import com.iantapply.wynncraft.item.majorId.MajorID;

public class SeekingModule extends MajorID {

    @Override
    public int id() {
        return 0;
    }

    @Override
    public String name() {
        return "Seeking Module";
    }

    @Override
    public String description() {
        return "<span class='font-ascii' style='color:#55FFFF'>+Seeking Module: <span class='font-ascii' style='color:#00AAAA'>Homing Shots track towards nearby enemies much more aggressively.</span></span>";
    }
}
