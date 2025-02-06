package com.iantapply.wynncraft.inventory.crafting;

import com.iantapply.wynncraft.configuration.NBTTagConfigurations;
import com.iantapply.wynncraft.inventory.WynncraftItem;
import com.iantapply.wynncraft.inventory.crafting.effects.base.negative.NegativePossible;
import com.iantapply.wynncraft.inventory.crafting.effects.base.negative.NegativeRange;
import com.iantapply.wynncraft.inventory.crafting.effects.base.negative.NegativeValue;
import com.iantapply.wynncraft.inventory.crafting.effects.base.positive.PositivePossible;
import com.iantapply.wynncraft.inventory.crafting.effects.base.positive.PositiveRange;
import com.iantapply.wynncraft.inventory.crafting.effects.base.positive.PositiveValue;
import lombok.Getter;
import lombok.Setter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class CraftingIngredient {
    private String name;
    private WynncraftItem item;
    private CraftingIngredientTier tier = CraftingIngredientTier.TIER_0;
    private Integer minimumCraftingLevel = 0;

    private List<CraftingProfession> requiredProfessions = new ArrayList<>();

    private List<PositiveRange> positiveEffectRanges = new ArrayList<>();
    private List<PositivePossible> positivePossibleEffects = new ArrayList<>();
    private List<PositiveValue> positiveEffectValues = new ArrayList<>();

    private List<NegativeRange> negativeEffectRanges = new ArrayList<>();
    private List<NegativePossible> negativePossibleEffects = new ArrayList<>();
    private List<NegativeValue> negativeEffectValues = new ArrayList<>();

    public CraftingIngredient(String name, WynncraftItem item) {
        this.name = name;
        this.item = item;
    }

    public CraftingIngredient(String name, WynncraftItem item, CraftingIngredientTier tier) {
        this.name = name;
        this.item = item;
        this.tier = tier;
    }

    public CraftingIngredient(String name, WynncraftItem item, CraftingIngredientTier tier, Integer minimumCraftingLevel) {
        this.name = name;
        this.item = item;
        this.tier = tier;
        this.minimumCraftingLevel = minimumCraftingLevel;
    }

    public CraftingIngredient(String name, WynncraftItem item, CraftingIngredientTier tier, Integer minimumCraftingLevel, List<CraftingProfession> requiredProfessions) {
        this.name = name;
        this.item = item;
        this.tier = tier;
        this.minimumCraftingLevel = minimumCraftingLevel;
        this.requiredProfessions = requiredProfessions;
    }

    public CraftingIngredient(String name, WynncraftItem item, CraftingIngredientTier tier, Integer minimumCraftingLevel, List<CraftingProfession> requiredProfessions, List<PositiveRange> positiveEffectRanges) {
        this.name = name;
        this.item = item;
        this.tier = tier;
        this.minimumCraftingLevel = minimumCraftingLevel;
        this.requiredProfessions = requiredProfessions;
        this.positiveEffectRanges = positiveEffectRanges;
    }

    public CraftingIngredient(String name, WynncraftItem item, CraftingIngredientTier tier, Integer minimumCraftingLevel, List<CraftingProfession> requiredProfessions, List<PositiveRange> positiveEffectRanges, List<PositivePossible> positivePossibleEffects) {
        this.name = name;
        this.item = item;
        this.tier = tier;
        this.minimumCraftingLevel = minimumCraftingLevel;
        this.requiredProfessions = requiredProfessions;
        this.positiveEffectRanges = positiveEffectRanges;
        this.positivePossibleEffects = positivePossibleEffects;
    }

    public CraftingIngredient(String name, WynncraftItem item, CraftingIngredientTier tier, Integer minimumCraftingLevel, List<CraftingProfession> requiredProfessions, List<PositiveRange> positiveEffectRanges, List<PositivePossible> positivePossibleEffects, List<PositiveValue> positiveEffectValues) {
        this.name = name;
        this.item = item;
        this.tier = tier;
        this.minimumCraftingLevel = minimumCraftingLevel;
        this.requiredProfessions = requiredProfessions;
        this.positiveEffectRanges = positiveEffectRanges;
        this.positivePossibleEffects = positivePossibleEffects;
        this.positiveEffectValues = positiveEffectValues;
    }

    public CraftingIngredient(String name, WynncraftItem item, CraftingIngredientTier tier, Integer minimumCraftingLevel, List<CraftingProfession> requiredProfessions, List<PositiveRange> positiveEffectRanges, List<PositivePossible> positivePossibleEffects, List<PositiveValue> positiveEffectValues, List<NegativeRange> negativeEffectRanges) {
        this.name = name;
        this.item = item;
        this.tier = tier;
        this.minimumCraftingLevel = minimumCraftingLevel;
        this.requiredProfessions = requiredProfessions;
        this.positiveEffectRanges = positiveEffectRanges;
        this.positivePossibleEffects = positivePossibleEffects;
        this.positiveEffectValues = positiveEffectValues;
        this.negativeEffectRanges = negativeEffectRanges;
    }

    public CraftingIngredient(String name, WynncraftItem item, CraftingIngredientTier tier, Integer minimumCraftingLevel, List<CraftingProfession> requiredProfessions, List<PositiveRange> positiveEffectRanges, List<PositivePossible> positivePossibleEffects, List<PositiveValue> positiveEffectValues, List<NegativeRange> negativeEffectRanges, List<NegativePossible> negativePossibleEffects) {
        this.name = name;
        this.item = item;
        this.tier = tier;
        this.minimumCraftingLevel = minimumCraftingLevel;
        this.requiredProfessions = requiredProfessions;
        this.positiveEffectRanges = positiveEffectRanges;
        this.positivePossibleEffects = positivePossibleEffects;
        this.positiveEffectValues = positiveEffectValues;
        this.negativeEffectRanges = negativeEffectRanges;
        this.negativePossibleEffects = negativePossibleEffects;
    }

    public CraftingIngredient(String name, WynncraftItem item, CraftingIngredientTier tier, Integer minimumCraftingLevel, List<CraftingProfession> requiredProfessions, List<PositiveRange> positiveEffectRanges, List<PositivePossible> positivePossibleEffects, List<PositiveValue> positiveEffectValues, List<NegativeRange> negativeEffectRanges, List<NegativePossible> negativePossibleEffects, List<NegativeValue> negativeEffectValues) {
        this.name = name;
        this.item = item;
        this.tier = tier;
        this.minimumCraftingLevel = minimumCraftingLevel;
        this.requiredProfessions = requiredProfessions;
        this.positiveEffectRanges = positiveEffectRanges;
        this.positivePossibleEffects = positivePossibleEffects;
        this.positiveEffectValues = positiveEffectValues;
        this.negativeEffectRanges = negativeEffectRanges;
        this.negativePossibleEffects = negativePossibleEffects;
        this.negativeEffectValues = negativeEffectValues;
    }

    /**
     * Get the finalized item with all the lore, requirements, and NBT data
     * @return The finalized item as a WynncraftItem (ItemStack)
     */
    public WynncraftItem getFinalizedItem() {
        ItemMeta meta = this.item.getItemMeta();
        List<Component> itemLore = new ArrayList<>();

        List<Integer> requiredProfessions = new ArrayList<>();
        for (CraftingProfession profession : this.requiredProfessions) {
            requiredProfessions.add(profession.getId());
        }

        // Set NBT data for possible effects and requirements
        this.item.setIntegerFlag(NBTTagConfigurations.CraftingIngredient.CRAFTING_LVL_REQUIREMENT, this.minimumCraftingLevel);
        this.item.setStringFlag(NBTTagConfigurations.CraftingIngredient.REQUIRED_PROFESSION_IDS, String.join(",", requiredProfessions.toString()));

        Component displayName = Component.text(this.name).color(NamedTextColor.GRAY)
                                .append(this.tier.getDisplay());
        meta.displayName(displayName);

        // Hide data from the client
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);

        itemLore.add(Component.text("Crafting Ingredient").color(NamedTextColor.DARK_GRAY)); // Add crafting ingredient header
        itemLore.add(Component.text(""));

        /* TOP HALF LORE **/
        for (PositiveRange range : this.positiveEffectRanges) {
            itemLore.add(range.lore());
            // TODO: Add NBT data (value that should be applied to the item combined)
        }

        for (NegativeRange range : this.negativeEffectRanges) {
            itemLore.add(range.lore());
            // TODO: Add NBT data (value that should be applied to the item combined)
        }

        for (PositivePossible possible : this.positivePossibleEffects) {
            itemLore.add(possible.lore());
            // TODO: Add NBT data (value that should be applied to the item combined)
        }

        for (PositiveValue value : this.positiveEffectValues) {
            itemLore.add(value.lore());
            // TODO: Add NBT data (value that should be applied to the item combined)
        }

        // Break the lore into two sections
        itemLore.add(Component.text(""));

        /* BOTTOM HALF LORE **/
        for (NegativePossible possible : this.negativePossibleEffects) {
            itemLore.add(possible.lore());
        }

        for (NegativeValue value : this.negativeEffectValues) {
            itemLore.add(value.lore());
        }

        itemLore.add(Component.text(""));

        // TODO: Check if the player has the required profession levels and crafting level to craft this item
        itemLore.add(Component.text("✖").color(NamedTextColor.RED) // TODO: We should toggle the X icon to be a red X for doesn't meet requirements and a green check mark if they do
                .append(Component.text("Crafting Lv. Min: " + this.minimumCraftingLevel)).color(NamedTextColor.GRAY));

        for (CraftingProfession profession : this.requiredProfessions) {
            // TODO: This should be a gray X if they don't meet the requirement and a green check mark if they do
            itemLore.add(Component.text("   ✔").color(NamedTextColor.GREEN) // TODO: We should toggle the X icon to be a red X for doesn't meet requirements and a green check mark if they do
                    .append(Component.text(profession.getIcon() + " " + profession.getName())).color(NamedTextColor.GRAY));
        }

        meta.lore(itemLore);

        this.item.setItemMeta(meta);
        return this.item;
    }

    /**
     * Helper functions to add and remove item from arrays
     * @param range The positive effect range object that contains lore, NBT data, and other visual elements
     */
    public void addPositiveEffectRange(PositiveRange range) {
        this.positiveEffectRanges.add(range);
    }

    public void removePositiveEffectRange(PositiveRange range) {
        this.positiveEffectRanges.remove(range);
    }

    public void addPositivePossibleEffect(PositivePossible possible) {
        this.positivePossibleEffects.add(possible);
    }

    public void removePositivePossibleEffect(PositivePossible possible) {
        this.positivePossibleEffects.remove(possible);
    }

    public void addPositiveEffectValue(PositiveValue value) {
        this.positiveEffectValues.add(value);
    }

    public void removePositiveEffectValue(PositiveValue value) {
        this.positiveEffectValues.remove(value);
    }

    public void addNegativeEffectRange(NegativeRange range) {
        this.negativeEffectRanges.add(range);
    }

    public void removeNegativeEffectRange(NegativeRange range) {
        this.negativeEffectRanges.remove(range);
    }

    public void addNegativePossibleEffect(NegativePossible possible) {
        this.negativePossibleEffects.add(possible);
    }

    public void removeNegativePossibleEffect(NegativePossible possible) {
        this.negativePossibleEffects.remove(possible);
    }

    public void addNegativeEffectValue(NegativeValue value) {
        this.negativeEffectValues.add(value);
    }

    public void removeNegativeEffectValue(NegativeValue range) {
        this.negativeEffectValues.remove(range);
    }

    public void addRequiredProfession(CraftingProfession profession) {
        this.requiredProfessions.add(profession);
    }

    public void removeRequiredProfession(CraftingProfession profession) {
        this.requiredProfessions.remove(profession);
    }
}
