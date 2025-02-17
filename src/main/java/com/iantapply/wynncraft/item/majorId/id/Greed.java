package com.iantapply.wynncraft.item.majorId.id;

import com.iantapply.wynncraft.item.majorId.MajorID;

public class Greed extends MajorID {

    @Override
    public int id() {
        return 10;
    }

    @Override
    public String name() {
        return "Greed";
    }

    @Override
    public String description() {
        return "<span class='font-ascii' style='color:#55FFFF'>+Greed: <span class='font-ascii' style='color:#00AAAA'>Picking up emeralds heals you and nearby players for 8% max health.</span></span>";
    }
}
