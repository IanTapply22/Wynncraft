package com.iantapply.wynncraft.item.items;

import com.iantapply.wynncraft.item.WynncraftItemMeta;
import com.iantapply.wynncraft.item.base.BaseStat;
import com.iantapply.wynncraft.item.enums.DropRestriction;
import com.iantapply.wynncraft.item.icon.ItemIconFormat;
import com.iantapply.wynncraft.item.enums.stat.WeaponDamageBase;
import com.iantapply.wynncraft.item.enums.tier.ItemRarity;
import com.iantapply.wynncraft.item.enums.advanced.AttackSpeed;
import com.iantapply.wynncraft.item.enums.advanced.WeaponType;
import com.iantapply.wynncraft.item.identification.Identification;
import com.iantapply.wynncraft.item.identification.RawIdentification;
import com.iantapply.wynncraft.item.requirements.ItemRequirements;
import com.iantapply.wynncraft.logger.Logger;
import com.iantapply.wynncraft.logger.LoggingLevel;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.datacomponent.item.CustomModelData;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Random;
import java.util.UUID;

public abstract class WynncraftWeapon extends WynncraftItem {

    public abstract WeaponType weaponType();

    public abstract AttackSpeed attackSpeed();

    public abstract int averageDPS();

    public abstract ItemRarity rarity();

    public int powderSlots() {
        return 1;
    }

    public String lore() {
        return null;
    }

    public DropRestriction dropRestriction() {
        return DropRestriction.NEVER;
    }

    public boolean raidReward() {
        return false;
    }

    public ArrayList<BaseStat> bases() {
        return new ArrayList<>();
    }

    public abstract ItemRequirements requirements();

    public ArrayList<Identification> identifications() {
        return new ArrayList<>();
    }

    public ArrayList<RawIdentification> rawIdentifications() {
        return new ArrayList<>();
    }

    @Override
    // TODO: Randomize lore and stored values rather than putting range
    // TODO: Store data on PDC of item when it is identified and alike
    // TODO: Store UUID of internal item
    // TODO: Store version of item to prevent outdated items from being used and to update
    public ItemStack build(ItemStack currentItem) {
        /* Colour of item envelope text */
        NamedTextColor itemColor = this.rarity().getColor();

        /* Build initial item stack with icon usage */
        ItemStack itemIcon = new ItemStack(Material.AIR);
        switch (icon().getFormat()) {
            case ItemIconFormat.ATTRIBUTE -> {
                itemIcon = new ItemStack(this.icon().getAttributeValue().getMaterial());
                itemIcon.setData(DataComponentTypes.CUSTOM_MODEL_DATA, CustomModelData.customModelData().addFloat(this.icon().getAttributeValue().getCustomModelData()).build());
            }
            case ItemIconFormat.SKIN -> {
                itemIcon = new ItemStack(Material.PLAYER_HEAD);
                SkullMeta skullMeta = (SkullMeta) itemIcon.getItemMeta();
                String texture = this.icon().getValue();
                String base64 = Base64.getEncoder().encodeToString(("{\"textures\":{\"SKIN\":{\"url\":\"http://textures.minecraft.net/texture/" + texture + "\"}}}").getBytes());
                GameProfile profile = new GameProfile(UUID.randomUUID(), null);
                profile.getProperties().put("textures", new Property("textures", base64));
                try {
                    Field profileField = skullMeta.getClass().getDeclaredField("profile");
                    profileField.setAccessible(true);
                } catch (NoSuchFieldException e) {
                    Logger.log(LoggingLevel.ERROR, "Failed to set profile for item icon: " + e.getMessage());
                }
                itemIcon.setItemMeta(skullMeta);
            }
        }

        /* Marked as experimental, but removes item attributes */
        itemIcon.unsetData(DataComponentTypes.ATTRIBUTE_MODIFIERS);

        ItemStack weapon = new ItemStack(itemIcon);
        ItemMeta meta = weapon.getItemMeta();

        /* Set name of weapon */
        meta.displayName(Component.text(this.internalName()).color(itemColor).decoration(TextDecoration.ITALIC, false));

        /* Start setting lore of item. Lore is added from top to bottom */
        ArrayList<Component> lore = new ArrayList<>();

        /* Set attack speed identifier */
        String attackSpeedSuffix = "Attack Speed";
        lore.add(Component.text(this.attackSpeed().getLore() + " ")
                .append(Component.text(attackSpeedSuffix))
                .color(NamedTextColor.GRAY)
                .decoration(TextDecoration.ITALIC, false));

        lore.add(Component.empty().decoration(TextDecoration.ITALIC, false)); // Starts the damage section in envelope

        /* Set weapon damage section */
        // Note: These don't need to be stored to persist as they are the same for every item
        for (BaseStat base : this.bases()) {
            WeaponDamageBase baseType = base.getBase();

            // Specialized to include "X Damage" before the component parsing. This makes it the entire colour of the damage type with the exception of the damage range
            if (base.getBase() == WeaponDamageBase.BASE_DAMAGE) {
                lore.add(Component.text(baseType.getIcon())
                        .color(baseType.getColor())
                        .decoration(TextDecoration.ITALIC, false)
                        .append(Component.text(String.format(" %s", baseType.getDamageIdentifier()))
                                .color(baseType.getColor())
                                .decoration(TextDecoration.ITALIC, false)
                                .append(Component.text(String.format(": %d-%d", base.getMin(), base.getMax()))
                                        .color(NamedTextColor.GRAY)
                                        .decoration(TextDecoration.ITALIC, false))));
            } else {
                lore.add(Component.text(baseType.getIcon())
                        .color(baseType.getColor())
                        .decoration(TextDecoration.ITALIC, false)
                        .append(Component.text(String.format(" %s ", baseType.getDamageIdentifier()))
                                .color(baseType.getColor())
                                .decoration(TextDecoration.ITALIC, false)
                                .append(Component.text(String.format("Damage: %d-%d", base.getMin(), base.getMax()))
                                        .color(NamedTextColor.GRAY)
                                        .decoration(TextDecoration.ITALIC, false))));
            }
        }

        /* Set average DPS */
        // TODO: Do proper handling of average DPS and calculate it properly
        if (currentItem == null || WynncraftItemMeta.getIntegerFlag("averageDps", currentItem.getItemMeta()) == -1) {
            lore.add(Component.text("   ")
                    .append(Component.text("Average DPS: "))
                    .color(NamedTextColor.DARK_GRAY)
                    .decoration(TextDecoration.ITALIC, false)
                    .append(Component.text(this.averageDPS())
                            .color(NamedTextColor.GRAY)
                            .decoration(TextDecoration.ITALIC, false)));
            WynncraftItemMeta.setIntegerFlag("averageDps", this.averageDPS(), meta);
        } else {
            lore.add(Component.text("   ")
                    .append(Component.text("Average DPS: "))
                    .color(NamedTextColor.DARK_GRAY)
                    .decoration(TextDecoration.ITALIC, false)
                    .append(Component.text(WynncraftItemMeta.getIntegerFlag("averageDps", currentItem.getItemMeta()))
                            .color(NamedTextColor.GRAY)
                            .decoration(TextDecoration.ITALIC, false)));
        }

        lore.add(Component.empty().decoration(TextDecoration.ITALIC, false)); // Starts the requirements section in envelope

        /* All requirements are initialized as not met and will update upon being held by a player. */
        lore.add(Component.text(String.format("Class Req: %s", this.requirements().getClassRequirement().getLore()))
                .color(NamedTextColor.GRAY)
                .decoration(TextDecoration.ITALIC, false));

        String[] requirementNames = {"Combat Lv. Min", "Strength Min", "Dexterity Min", "Intelligence Min", "Defence Min", "Agility Min"};
        int[] requirementValues = {
                this.requirements().getLevel(),
                this.requirements().getStrength(),
                this.requirements().getDexterity(),
                this.requirements().getIntelligence(),
                this.requirements().getDefence(),
                this.requirements().getAgility()
        };

        for (int i = 0; i < requirementValues.length; i++) {
            if (requirementValues[i] != -1) {
                lore.add(Component.text(String.format("%s: %d", requirementNames[i], requirementValues[i]))
                        .color(NamedTextColor.GRAY)
                        .decoration(TextDecoration.ITALIC, false));
            }
        }

        // Note: These don't persist in a PDC as they are the same for every item (raw values)
        if (!this.rawIdentifications().isEmpty()) {
            lore.add(Component.empty().decoration(TextDecoration.ITALIC, false)); // Start of raw identifications

            /* Set raw identifications and pin above ranged identifications */
            for (RawIdentification raw : this.rawIdentifications()) {
                if (raw.getValue() < 0) {
                    lore.add(Component.text(raw.getValue()).decoration(TextDecoration.ITALIC, false)
                            .color(NamedTextColor.RED).append(Component.text(String.format(" %s", raw.getIdentification().getName()))
                                    .color(NamedTextColor.GRAY)
                                    .decoration(TextDecoration.ITALIC, false)));
                } else {
                    lore.add(Component.text(raw.getValue()).decoration(TextDecoration.ITALIC, false)
                            .color(NamedTextColor.GREEN).append(Component.text(String.format(" %s", raw.getIdentification().getName()))
                                    .color(NamedTextColor.GRAY)
                                    .decoration(TextDecoration.ITALIC, false)));
                }
            }
        }

        /* Set identifications */
        // TODO: Use suffix for stats; or force suffix in stat enum
        if (!this.identifications().isEmpty()) {
            lore.add(Component.empty().decoration(TextDecoration.ITALIC, false));

            for (Identification identification : this.identifications()) {

                // Roll random value if it doesn't exist, or use the existing value
                int value;
                if (currentItem != null && WynncraftItemMeta.getIntegerFlag(identification.getIdentification().name(), currentItem.getItemMeta()) != -1) {
                    value = WynncraftItemMeta.getIntegerFlag(identification.getIdentification().name(), currentItem.getItemMeta());
                } else {
                    Random random = new Random();
                    value = random.nextInt(identification.getMax() - identification.getMin()) + identification.getMin();
                }

                WynncraftItemMeta.setIntegerFlag(identification.getIdentification().name(), value, meta);

                // Set lore as appropriate
                if (identification.getMin() < 0) {
                    lore.add(Component.text(String.format("%s%s ", value, identification.getUnit().getSuffix()))
                                    .color(NamedTextColor.RED).append(Component.text(identification.getIdentification().formattedStatName()).color(NamedTextColor.GRAY))
                                    .decoration(TextDecoration.ITALIC, false));
                } else {
                    lore.add(Component.text(String.format("+%s%s ", value, identification.getUnit().getSuffix()))
                                    .color(NamedTextColor.GREEN).append(Component.text(identification.getIdentification().formattedStatName()).color(NamedTextColor.GRAY))
                                    .decoration(TextDecoration.ITALIC, false));
                }
            }
        }

        /* Set powder slots */
        if (this.powderSlots() > 0) {
            lore.add(Component.empty().decoration(TextDecoration.ITALIC, false));

            int powderSlotsAvailable;
            if (currentItem == null || WynncraftItemMeta.getIntegerFlag("powderSlotsAvailable", currentItem.getItemMeta()) == -1) {
                powderSlotsAvailable = this.powderSlots();
                WynncraftItemMeta.setIntegerFlag("powderSlotsAvailable", powderSlotsAvailable, meta);
            } else {
                WynncraftItemMeta.setIntegerFlag("powderSlotsAvailable", WynncraftItemMeta.getIntegerFlag("powderSlotsAvailable", currentItem.getItemMeta()), meta);
                powderSlotsAvailable = WynncraftItemMeta.getIntegerFlag("powderSlotsAvailable", currentItem.getItemMeta());
            }

            lore.add(Component.text(String.format("[%s/%s] Powder Slots", this.powderSlots() - powderSlotsAvailable, this.powderSlots()))
                    .color(NamedTextColor.GRAY)
                    .decoration(TextDecoration.ITALIC, false));
        }

        /* Set item rarity */
        String rarityIdentifier = this.rarity().name().substring(0, 1).toUpperCase() +
                this.rarity().name().substring(1).toLowerCase();
        lore.add(Component.text(String.format("%s Item", rarityIdentifier))
                .color(itemColor)
                .decoration(TextDecoration.ITALIC, false));

        /* Add extra lore if necessary */
        if (this.lore() != null) {
            lore.add(Component.text(this.lore())
                    .color(NamedTextColor.DARK_GRAY)
                    .decoration(TextDecoration.ITALIC, false));
        }

        /* Finalize the lore */
        meta.lore(lore);
        WynncraftItemMeta.setStringFlag("uuid", this.uuid().toString(), meta);
        WynncraftItemMeta.setStringFlag("version", this.version(), meta);
        weapon.setItemMeta(meta);

        return weapon;
    }
}
