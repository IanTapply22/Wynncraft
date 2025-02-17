package com.iantapply.wynncraft.item.majorId.id;

import com.iantapply.wynncraft.item.majorId.MajorID;

public class ForestsBlessing extends MajorID {

    @Override
    public int id() {
        return 16;
    }

    @Override
    public String name() {
        return "Forest's Blessing";
    }

    @Override
    public String description() {
        return "<span class='font-ascii' style='color:#55FFFF'>+Forest's Blessing: <span class='font-ascii' style='color:#00AAAA'>Your archer summons have increased movement speed, attack speed, and vision. Arrow Bomb's damage is reduced by <span class='font-ascii' style='color:elements.neutral'><span class='font-common'>\uE005</span> </span><span class='font-ascii' style='color:#00AAAA'>-30%</span><span class='font-ascii' style='color:#00AAAA'>. </span></span></span></span>";
    }
}
