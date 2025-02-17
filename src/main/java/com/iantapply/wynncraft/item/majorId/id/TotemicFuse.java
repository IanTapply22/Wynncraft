package com.iantapply.wynncraft.item.majorId.id;

import com.iantapply.wynncraft.item.majorId.MajorID;

public class TotemicFuse extends MajorID {

    @Override
    public int id() {
        return 7;
    }

    @Override
    public String name() {
        return "Totemic Fuse";
    }

    @Override
    public String description() {
        return "<span class='font-ascii' style='color:#55FFFF'>+Totemic Fuse: <span class='font-ascii' style='color:#00AAAA'>Totemic Smash deals far greater damage but Totem lasts half as long.</span></span>";
    }
}
