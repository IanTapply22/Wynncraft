package com.iantapply.wynncraft.gui.menu;

import com.iantapply.wynncraft.gui.GUIClickableItem;
import com.iantapply.wynncraft.gui.WynncraftGUI;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class CharacterSelector extends WynncraftGUI {
    private Inventory inventory;

    @Override
    public Inventory inventory() {
        if (this.inventory == null) {
            this.inventory = Bukkit.createInventory(null, slots(), name());
        }
        return this.inventory;
    }

    @Override
    public Component name() {
        return Component.text("\uDAFF\uDFD5\uE01F").font(Key.key("minecraft:interface")).color(NamedTextColor.WHITE);
    }

    @Override
    public int slots() {
        return 54;
    }

    @Override
    public void open(Player player) {
        this.addItem(new GUIClickableItem(new ItemStack(Material.POTION), Component.text("Cancel Deletion").color(NamedTextColor.RED).decoration(TextDecoration.BOLD, true).decoration(TextDecoration.ITALIC, false), 7, 181,
                Arrays.asList(
                        Component.text("Restore characters currently in the waiting period prior to deletion").color(NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false),
                        Component.empty(),
                        Component.text("\uE000")
                                .font(Key.key("minecraft:keybind"))
                                .color(NamedTextColor.WHITE)
                                .decoration(TextDecoration.ITALIC, false)
                                .append(Component.text(" Click to Open")
                                        .font(Key.key("minecraft:default"))
                                        .color(NamedTextColor.GREEN)
                                        .decoration(TextDecoration.ITALIC, false)
                                ))));
        player.openInventory(inventory());
    }
}