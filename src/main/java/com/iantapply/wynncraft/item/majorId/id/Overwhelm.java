package com.iantapply.wynncraft.item.majorId.id;

import com.iantapply.wynncraft.item.majorId.MajorID;

public class Overwhelm extends MajorID {

    @Override
    public int id() {
        return 8;
    }

    @Override
    public String name() {
        return "Overwhelm";
    }

    @Override
    public String description() {
        return "<span class='font-ascii' style='color:#55FFFF'>+Overwhelm: <span class='font-ascii' style='color:#00AAAA'>Bash will hit +2 times.</span></span>";
    }
}
