package com.iantapply.wynncraft.player;

import org.postgresql.util.PGobject;

import java.sql.SQLException;

public record LegacyRankColour(String main, String sub) {

    public PGobject getContent() throws SQLException {
        PGobject jsonObject = new PGobject();
        jsonObject.setType("json");
        jsonObject.setValue("{}");

        return jsonObject;
    }
}
