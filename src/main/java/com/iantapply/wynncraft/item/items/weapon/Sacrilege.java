package com.iantapply.wynncraft.item.items.weapon;

import com.iantapply.wynncraft.item.base.BaseStat;
import com.iantapply.wynncraft.item.enums.*;
import com.iantapply.wynncraft.item.enums.advanced.AttackSpeed;
import com.iantapply.wynncraft.item.enums.advanced.WeaponType;
import com.iantapply.wynncraft.item.enums.stat.SkillStat;
import com.iantapply.wynncraft.item.enums.stat.StatType;
import com.iantapply.wynncraft.item.enums.stat.WeaponDamageBase;
import com.iantapply.wynncraft.item.enums.tier.ItemRarity;
import com.iantapply.wynncraft.item.icon.ItemIcon;
import com.iantapply.wynncraft.item.icon.ItemIconAttributeValue;
import com.iantapply.wynncraft.item.icon.ItemIconFormat;
import com.iantapply.wynncraft.item.identification.Identification;
import com.iantapply.wynncraft.item.identification.IdentificationUnit;
import com.iantapply.wynncraft.item.identification.RawIdentification;
import com.iantapply.wynncraft.item.items.WynncraftWeapon;
import com.iantapply.wynncraft.item.requirements.ClassRequirement;
import com.iantapply.wynncraft.item.requirements.ItemRequirements;

import java.util.ArrayList;

public class Sacrilege extends WynncraftWeapon {
    @Override
    public String internalName() {
        return "Sacrilege";
    }

    @Override
    public ItemType type() {
        return ItemType.WEAPON;
    }

    @Override
    public ItemIcon icon() {
        ItemIconAttributeValue attributeValue = new ItemIconAttributeValue("minecraft:iron_horse_armor",
                                                                337, "wand.thunder3");
        return new ItemIcon(ItemIconFormat.ATTRIBUTE, attributeValue);
    }

    @Override
    public WeaponType weaponType() {
        return WeaponType.WAND;
    }

    @Override
    public AttackSpeed attackSpeed() {
        return AttackSpeed.NORMAL;
    }

    @Override
    public int averageDPS() {
        return 330;
    }

    @Override
    public int powderSlots() {
        return 3;
    }

    @Override
    public DropRestriction dropRestriction() {
        return DropRestriction.NORMAL;
    }

    @Override
    public ItemRarity rarity() {
        return ItemRarity.LEGENDARY;
    }

    @Override
    public ItemRequirements requirements() {
        return new ItemRequirements(103, null,
                                  45, 60, -1, -1, -1,
                                    null, ClassRequirement.MAGE, null);
    }

    @Override
    public ArrayList<Identification> identifications() {
        ArrayList<Identification> identifications = new ArrayList<>();
        identifications.add(new Identification(StatType.RAW_MAIN_ATTACK_DAMAGE, 42, 182, 140, IdentificationUnit.FLAT));
        identifications.add(new Identification(StatType.RAW_SPELL_DAMAGE, -260, -140, -200, IdentificationUnit.FLAT));
        identifications.add(new Identification(StatType.LIFE_STEAL, 131, 566, 435, IdentificationUnit.PER_THREE_SECONDS));
        identifications.add(new Identification(StatType.MANA_STEAL, -26, -14, -20, IdentificationUnit.PER_THREE_SECONDS));
        identifications.add(new Identification(StatType.RAW_ATTACK_SPEED, 1, 5, 4, IdentificationUnit.TIER));
        identifications.add(new Identification(StatType.WATER_DAMAGE, -65, -35, -50, IdentificationUnit.PERCENTAGE));
        return identifications;
    }

    @Override
    public ArrayList<RawIdentification> rawIdentifications() {
        ArrayList<RawIdentification> identifications = new ArrayList<>();
        identifications.add(new RawIdentification(SkillStat.RAW_INTELLIGENCE, -40));
        return identifications;
    }

    @Override
    public ArrayList<BaseStat> bases() {
        ArrayList<BaseStat> bases = new ArrayList<>();
        bases.add(new BaseStat(WeaponDamageBase.BASE_DAMAGE, 5, 55, 5));
        bases.add(new BaseStat(WeaponDamageBase.BASE_EARTH_DAMAGE, 25, 75, 25));
        bases.add(new BaseStat(WeaponDamageBase.BASE_THUNDER_DAMAGE, 25, 135, 25));
        return bases;
    }
}
