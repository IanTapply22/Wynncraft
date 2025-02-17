package com.iantapply.wynncraft.item.majorId.id;

import com.iantapply.wynncraft.item.majorId.MajorID;

public class DivineHonor extends MajorID {

    @Override
    public int id() {
        return 1;
    }

    @Override
    public String name() {
        return "Divine Honor";
    }

    @Override
    public String description() {
        return "<span class='font-ascii' style='color:#55FFFF'>+Divine Honor: <span class='font-ascii' style='color:#00AAAA'>Increases the bonus from Radiance by 10%. Bash deals <span class='font-ascii' style='color:#00aa00'><span class='font-common'>\uE001</span> </span><span class='font-ascii' style='color:#00AAAA'>-15% per hit.</span></span></span></span>";
    }
}
