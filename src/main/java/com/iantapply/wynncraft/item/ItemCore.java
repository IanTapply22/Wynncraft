package com.iantapply.wynncraft.item;

import com.iantapply.wynncraft.Wynncraft;
import com.iantapply.wynncraft.item.items.WynncraftItem;
import com.iantapply.wynncraft.item.items.weapon.Sacrilege;
import lombok.Getter;

import java.util.ArrayList;

/**
 * Handles all registration and enabling of items, this is the core of the item system, the registry.
 */
@Getter
public class ItemCore {
    private final ArrayList<WynncraftItem> items;
    private final Wynncraft plugin;

    public ItemCore(Wynncraft plugin) {
        items = new ArrayList<>();
        this.plugin = plugin;
    }

    /**
     * Initializes and stages all items
     */
    public void initialize() {
        this.items.add(new Sacrilege());
    }

    /**
     * Gets an item in the sytsem by its UUID, the UUID never changes
     * @param uuid The UUID of the item as a String
     * @return The item, or null if not found
     */
    public WynncraftItem getItemByUUID(String uuid) {
        for (WynncraftItem item : items) {
            if (item.uuid().toString().equals(uuid)) {
                return item;
            }
        }
        return null;
    }
}
