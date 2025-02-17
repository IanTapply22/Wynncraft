package com.iantapply.wynncraft.item.majorId.id;

import com.iantapply.wynncraft.item.majorId.MajorID;

public class Rally extends MajorID {

    @Override
    public int id() {
        return 17;
    }

    @Override
    public String name() {
        return "Rally";
    }

    @Override
    public String description() {
        return "<span class='font-ascii' style='color:#55FFFF'>+Rally: <span class='font-ascii' style='color:#00AAAA'>Charge heals you by 10% and nearby allies by 15% on impact, but becomes harmless.</span></span>";
    }
}
