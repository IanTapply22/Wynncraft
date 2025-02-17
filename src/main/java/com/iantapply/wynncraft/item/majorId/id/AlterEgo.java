package com.iantapply.wynncraft.item.majorId.id;

import com.iantapply.wynncraft.item.majorId.MajorID;

public class AlterEgo extends MajorID {

    @Override
    public int id() {
        return 3;
    }

    @Override
    public String name() {
        return "Alter Ego";
    }

    @Override
    public String description() {
        return "<span class='font-ascii' style='color:#55FFFF'>+Alter Ego: <span class='font-ascii' style='color:#00AAAA'>Awakened can be activated after saving 40% less mana, but its duration is reduced by 25%.</span></span>";
    }
}
