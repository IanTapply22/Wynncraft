package com.iantapply.wynncraft.database.model.object;

public class Profession extends JSONObject {

    public Profession(int level, int rawXp) {
        super();
        this.map.put("level", level);
        this.map.put("rawXp", rawXp);
    }
}
