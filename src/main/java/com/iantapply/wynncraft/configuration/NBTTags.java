package com.iantapply.wynncraft.configuration;

/**
 * A class that holds the configuration for commonly used NBT tags on items
 */
public class NBTTags {

    public static class CraftingIngredient {
        public static final String CRAFTING_LVL_REQUIREMENT = "craftingLvlRequirement"; // This can be stored as an int for simplicity
        public static final String REQUIRED_PROFESSION_IDS = "requiredProfessions"; // This content should look like a list of Strings in a String (i.e. "A,B,C")
    }
}
