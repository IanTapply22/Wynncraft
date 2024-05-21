package com.iantapply.wynncraft.inventory.crafting.effects.positive;

import com.iantapply.wynncraft.inventory.crafting.effects.base.positive.PositiveRange;
import lombok.Getter;
import lombok.Setter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

/**
 * A wrapper for a stat that can be a percentage range (i.e. 10-20%)
 */
@Getter @Setter
public class PercentageRangePositive extends PositiveRange {
    private String name;
    private String nbtKey;
    private int min;
    private int max;

    /**
     * Create a new percentage range stat
     * @param name The name of the stat
     * @param nbtKey The NBT key to store the stat as in the item
     * @param min The minimum value of the stat
     * @param max The maximum value of the stat
     */
    public PercentageRangePositive(String name, String nbtKey, int min, int max) {
        this.name = name;
        this.nbtKey = nbtKey;
        this.min = min;
        this.max = max;
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public String nbtKey() {
        return this.nbtKey;
    }

    @Override
    public Component lore() {
        return Component.text("+" + this.min).color(NamedTextColor.GREEN)
                .append(Component.text(" to ").color(NamedTextColor.DARK_GREEN))
                .append(Component.text(this.max + "% ").color(NamedTextColor.GREEN))
                .append(Component.text(this.name).color(NamedTextColor.GRAY));
    }

    /**
     * Get the range of the stat
     * @return The range
     */
    public int getRange() {
        return this.max - this.min;
    }

    /**
     * Gets the mid-percentage of the stat
     * @return The mid-percentage
     */
    public double getMidpoint() {
        return (double) getRange() / 2;
    }

    /**
     * Gets a random value within the range of the stat
     * @return The random value
     */
    public int getRandomValue() {
        return (int) (Math.random() * this.getRange()) + this.min;
    }
}
