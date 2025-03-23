package com.iantapply.wynncraft.database.model.object;

public class SeasonRank extends JSONObject {

    public SeasonRank(int rating, int finalTerritories) {
        super();
        this.map.put("rating", rating);
        this.map.put("finalTerritories", finalTerritories);
    }
}
