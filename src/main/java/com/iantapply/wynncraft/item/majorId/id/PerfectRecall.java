package com.iantapply.wynncraft.item.majorId.id;

import com.iantapply.wynncraft.item.majorId.MajorID;

public class PerfectRecall extends MajorID {

    @Override
    public int id() {
        return 4;
    }

    @Override
    public String name() {
        return "Perfect Recall";
    }

    @Override
    public String description() {
        return "<span class='font-ascii' style='color:#55FFFF'>+Perfect Recall: <span class='font-ascii' style='color:#00AAAA'>Memory Recollection adds 1 extra spell. Increases Chaos Explosion's mana bank requirement to 150.</span></span>";
    }
}
