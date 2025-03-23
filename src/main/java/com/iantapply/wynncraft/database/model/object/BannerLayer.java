package com.iantapply.wynncraft.database.model.object;

import net.kyori.adventure.text.format.NamedTextColor;

public class BannerLayer extends JSONObject {

    // TODO: Create banner pattern enum
    public BannerLayer(NamedTextColor color, String layer) {
        super();
        this.map.put("color", color.examinableName());
        this.map.put("layer", layer);
    }
}
