package com.iantapply.wynncraft.item.icon;

import com.iantapply.wynncraft.logger.Logger;
import com.iantapply.wynncraft.logger.LoggingLevel;
import lombok.Getter;
import org.bukkit.Material;

@Getter
public class ItemIconAttributeValue {
    private final String id;
    private final int customModelData;
    private final String name;

    /**
     * Constructor for ItemIconAttributeValue
     * @param id ID of the item
     * @param customModelData Custom model data of the item
     * @param name Name of icon -- Can be ignored for now
     */
    public ItemIconAttributeValue(String id, int customModelData, String name) {
        if (id == null || name == null) {
            Logger.log(LoggingLevel.ERROR, "ItemIconAttributeValue constructor: id, customModelData, and name cannot be null");
        }

        this.id = id;
        this.customModelData = customModelData;
        this.name = name;
    }

    public Material getMaterial() {
        String materialName = this.id.replace("minecraft:", "").toUpperCase();

        Material material = Material.matchMaterial(materialName);
        if (material == null) {
            material = Material.AIR;
            Logger.log(LoggingLevel.ERROR, "ItemIconAttributeValue getMaterial: Material not found for " + materialName);
        }

        return material;
    }
}
