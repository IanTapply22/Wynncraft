package com.iantapply.wynncraft.database.model.object;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;

public abstract class JSONObject {
    public HashMap<String, Object> map;

    public String toJson() {
        StringBuilder json = new StringBuilder();
        json.append("{");
        for (String key : this.map.keySet()) {
            json.append("\"").append(key).append("\":");
            Object value = this.map.get(key);
            if (value instanceof String) {
                json.append("\"").append(value).append("\"");
            } else {
                json.append(value);
            }
            json.append(",");
        }
        json.deleteCharAt(json.length() - 1);
        json.append("}");
        return json.toString();
    }

    public void fromJson(String json) {
        this.map = new HashMap<>();
        Gson gson = new Gson();
        Type type = new TypeToken<HashMap<String, Object>>() {}.getType();
        this.map = gson.fromJson(json, type);
    }
}
