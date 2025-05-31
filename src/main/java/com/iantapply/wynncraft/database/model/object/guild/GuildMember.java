package com.iantapply.wynncraft.database.model.object.guild;

import com.iantapply.wynncraft.database.model.object.JSONObject;

import java.sql.Timestamp;
import java.util.UUID;

public class GuildMember extends JSONObject {

    public GuildMember(UUID UUID, int contributed, int contributionRank, Timestamp joined) {
        super();
        this.map.put("uuid", UUID.toString());
        this.map.put("contributed", contributed);
        this.map.put("contributionRank", contributionRank);
        this.map.put("joined", joined.toString());
    }
}
