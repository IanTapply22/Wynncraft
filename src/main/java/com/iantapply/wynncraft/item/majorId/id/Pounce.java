package com.iantapply.wynncraft.item.majorId.id;

import com.iantapply.wynncraft.item.majorId.MajorID;

public class Pounce extends MajorID {

    @Override
    public int id() {
        return 2;
    }

    @Override
    public String name() {
        return "Pounce";
    }

    @Override
    public String description() {
        return "<span class='font-ascii' style='color:#55FFFF'>+Pounce: <span class='font-ascii' style='color:#00AAAA'>Escape becomes a forward lunge.</span></span>";
    }
}
