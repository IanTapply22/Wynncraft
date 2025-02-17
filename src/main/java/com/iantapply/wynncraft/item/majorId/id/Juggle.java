package com.iantapply.wynncraft.item.majorId.id;

import com.iantapply.wynncraft.item.majorId.MajorID;

public class Juggle extends MajorID {

    @Override
    public int id() {
        return 15;
    }

    @Override
    public String name() {
        return "Juggle";
    }

    @Override
    public String description() {
        return "<span class='font-ascii' style='color:#55FFFF'>+Juggle: <span class='font-ascii' style='color:#00AAAA'>Mutilate adds an additional 12 hits. All hits are reduced by <span class='font-ascii' style='color:elements.neutral'><span class='font-common'>\uE005</span> </span><span class='font-ascii' style='color:#00AAAA'>-10%.</span></span></span></span>";
    }
}
