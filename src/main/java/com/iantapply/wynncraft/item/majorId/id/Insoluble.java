package com.iantapply.wynncraft.item.majorId.id;

import com.iantapply.wynncraft.item.majorId.MajorID;

public class Insoluble extends MajorID {

    @Override
    public int id() {
        return 18;
    }

    @Override
    public String name() {
        return "Insoluble";
    }

    @Override
    public String description() {
        return "<span class='font-ascii' style='color:#55FFFF'>+Insoluble: <span class='font-ascii' style='color:#00AAAA'>Dissolution now lasts 2.5 seconds, but grants only 35% resistance.</span></span>";
    }
}
