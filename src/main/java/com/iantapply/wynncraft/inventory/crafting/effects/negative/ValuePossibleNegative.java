package com.iantapply.wynncraft.inventory.crafting.effects.negative;

import com.iantapply.wynncraft.inventory.crafting.effects.base.negative.NegativePossible;
import lombok.Getter;
import lombok.Setter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

@Getter @Setter
public class ValuePossibleNegative extends NegativePossible {

    private ValueNegative firstNegativeValue;
    private ValueNegative secondNegativeValue;

    private DurationNegative negativeDuration;

    public ValuePossibleNegative(ValueNegative firstNegativeValue, ValueNegative secondNegativeValue) {
        this.firstNegativeValue = firstNegativeValue;
        this.secondNegativeValue = secondNegativeValue;
    }

    public ValuePossibleNegative(ValueNegative firstNegativeValue, DurationNegative negativeDuration) {
        this.firstNegativeValue = firstNegativeValue;
        this.negativeDuration = negativeDuration;
    }

    @Override
    public Component lore() {
        if (this.secondNegativeValue == null) {
            return Component.text(this.firstNegativeValue.getValue()).color(NamedTextColor.RED)
                    .append(Component.text(" or ").color(NamedTextColor.GRAY))
                    .append(Component.text(this.negativeDuration.getValue() + "s Duration").color(NamedTextColor.RED));
        } else {
            return Component.text(this.firstNegativeValue.getValue()).color(NamedTextColor.RED)
                    .append(Component.text(" " + this.firstNegativeValue.getName()).color(NamedTextColor.RED))
                    .append(Component.text(" or ").color(NamedTextColor.GRAY))
                    .append(Component.text(this.secondNegativeValue.getValue()).color(NamedTextColor.RED))
                    .append(Component.text(" " + this.secondNegativeValue.getName()).color(NamedTextColor.RED));
        }
    }
}
