package com.iantapply.wynncraft.item.majorId.id;

import com.iantapply.wynncraft.item.majorId.MajorID;

public class PerilousFlare extends MajorID {

    @Override
    public int id() {
        return 11;
    }

    @Override
    public String name() {
        return "Perilous Flare";
    }

    @Override
    public String description() {
        return "<span class='font-ascii' style='color:#55FFFF'>+Perilous Flare: <span class='font-ascii' style='color:#00AAAA'>Pyrokinesis moves much slower, but has an increased Area of Effect and <span class='font-ascii' style='color:elements.neutral'><span class='font-common'>\uE005</span> +50%</span><span class='font-ascii' style='color:#00AAAA'>.</span></span></span></span>";
    }
}
