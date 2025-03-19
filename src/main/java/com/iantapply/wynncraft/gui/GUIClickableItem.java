package com.iantapply.wynncraft.gui;

import com.iantapply.wynncraft.item.WynncraftItemMeta;
import com.iantapply.wynncraft.logger.Logger;
import com.iantapply.wynncraft.logger.LoggingLevel;
import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.datacomponent.item.CustomModelData;
import lombok.Getter;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

/**
 * A class that represents a clickable item in a GUI, both interactable and
 * non-interactable.
 */
@Getter
public class GUIClickableItem {
    public static HashMap<String, GUIClickableItem> itemData = new HashMap<>();
    private final String uuid;
    private final ItemStack item;
    private final int slot;
    private int customModelData = 0;
    private List<Component> lore;
    private Component name = null;

    /**
     * Creates a new clickable item
     */
    public GUIClickableItem(ItemStack item, int slot) {
        this.item = item;
        this.slot = slot;
        this.uuid = UUID.randomUUID().toString();
        itemData.put(uuid, this);
    }

    public GUIClickableItem(ItemStack item, Component name, int slot, int customModelData) {
        this.item = item;
        this.slot = slot;
        this.customModelData = customModelData;
        this.name = name;
        this.uuid = UUID.randomUUID().toString();
        itemData.put(uuid, this);
    }

    public GUIClickableItem(ItemStack item, Component name, int slot, int customModelData, List<Component> lore) {
        this.item = item;
        this.slot = slot;
        this.customModelData = customModelData;
        this.lore = lore;
        this.name = name;
        this.uuid = UUID.randomUUID().toString();
        itemData.put(uuid, this);
    }

    /**
     * Gets the finalized item with the GUI NBT data attached
     * @return The finalized item
     */
    public ItemStack getFinalizedItem() {
        ItemStack item = getItem();
        item.setData(DataComponentTypes.CUSTOM_MODEL_DATA, CustomModelData.customModelData().addFloat(this.customModelData).build());
        ItemMeta meta = item.getItemMeta();
        if (this.name != null) meta.displayName(this.name);
        if (this.lore != null && !this.lore.isEmpty()) {
            List<Component> wrappedLore = new ArrayList<>();
            for (Component loreLine : this.lore) {
                if (loreLine.equals(Component.empty())) {
                    wrappedLore.add(Component.empty());
                } else {
                    wrappedLore.addAll(this.wrapLoreLine(loreLine, 35));
                }
            }
            meta.lore(wrappedLore);
        }
        WynncraftItemMeta.setStringFlag(GUIHelpers.getClickableItemFlag(), uuid, meta);
        item.setItemMeta(meta);
        return item;
    }

    /**
     * Wraps the lore line to fit within the maximum length
     * @param loreLine The lore line to wrap
     * @param maxLength The maximum length of the line
     * @return The wrapped lore line
     */
    private List<Component> wrapLoreLine(Component loreLine, int maxLength) {
        List<Component> wrappedLore = new ArrayList<>();
        if (loreLine instanceof TextComponent) {
            String text = ((TextComponent) loreLine).content();
            TextColor color = loreLine.color();
            Map<TextDecoration, TextDecoration.State> decorations = loreLine.decorations();
            Key font = loreLine.font();
            List<Component> children = loreLine.children();

            while (text.length() > maxLength) {
                int wrapIndex = text.lastIndexOf(' ', maxLength);
                if (wrapIndex == -1) wrapIndex = maxLength;

                String line = text.substring(0, wrapIndex);
                wrappedLore.add(Component.text(line).color(color).decorations(decorations).font(font));

                text = text.substring(wrapIndex).trim();
            }

            if (!text.isEmpty()) {
                Component wrappedComponent = Component.text(text).color(color).decorations(decorations).font(font);
                for (Component child : children) {
                    wrappedComponent = wrappedComponent.append(child);
                }
                wrappedLore.add(wrappedComponent);
            }
        } else {
            wrappedLore.add(loreLine);
        }

        return wrappedLore;
    }

    /**
     * What runs when the item is clicked
     * @param event The event that is fired when the item is clicked
     */
    public void run(InventoryClickEvent event) {
        event.setCancelled(true);
    }
    /**
     * Whether the item can be picked up
     * @return Whether the item can be picked up
     */
    public boolean canPickup() {
        return false;
    }
}
