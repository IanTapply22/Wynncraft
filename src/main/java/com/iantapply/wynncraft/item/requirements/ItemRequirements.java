package com.iantapply.wynncraft.item.requirements;

import lombok.Getter;

import java.util.ArrayList;

@Getter
public class ItemRequirements {
    private final int level;
    private final LevelRange levelRange;
    private final int strength;
    private final int dexterity;
    private final int intelligence;
    private final int defence;
    private final int agility;
    private final String quest;
    private final ClassRequirement classRequirement;
    private final ArrayList<String> skills;

    public ItemRequirements(int level, LevelRange levelRange, int strength, int dexterity, int intelligence, int defence, int agility, String quest, ClassRequirement classRequirement, ArrayList<String> skills) {
        this.level = level;
        this.levelRange = levelRange;
        this.strength = strength;
        this.dexterity = dexterity;
        this.intelligence = intelligence;
        this.defence = defence;
        this.agility = agility;
        this.quest = quest;
        this.classRequirement = classRequirement;
        this.skills = skills;
    }
}
