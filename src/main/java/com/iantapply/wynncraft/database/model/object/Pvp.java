package com.iantapply.wynncraft.database.model.object;

public class Pvp extends JSONObject {

    public Pvp(int kills, int deaths) {
        super();
        this.map.put("kills", kills);
        this.map.put("deaths", deaths);
    }
}
