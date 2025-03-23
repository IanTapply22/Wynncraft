package com.iantapply.wynncraft.database.model.object;

import com.iantapply.wynncraft.character.Skills;

import java.util.HashMap;

public class SkillPoints extends JSONObject {

    public SkillPoints(HashMap<Skills, Integer> skills) {
        super();
        for (Skills skill : skills.keySet()) {
            this.map.put(skill.getName(), skills.get(skill));
        }
    }
}
