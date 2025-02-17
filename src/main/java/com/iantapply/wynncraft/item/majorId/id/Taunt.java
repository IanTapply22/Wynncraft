package com.iantapply.wynncraft.item.majorId.id;

import com.iantapply.wynncraft.item.majorId.MajorID;

public class Taunt extends MajorID {

    @Override
    public int id() {
        return 13;
    }

    @Override
    public String name() {
        return "Taunt";
    }

    @Override
    public String description() {
        return "<span class='font-ascii' style='color:#55FFFF'>+Taunt: <span class='font-ascii' style='color:#00AAAA'>Provoke's Cooldown is lowered by -3s.</span></span>";
    }
}
