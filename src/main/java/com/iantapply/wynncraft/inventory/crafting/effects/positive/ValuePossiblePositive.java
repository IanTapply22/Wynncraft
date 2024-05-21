package com.iantapply.wynncraft.inventory.crafting.effects.positive;


import com.iantapply.wynncraft.inventory.crafting.effects.base.positive.PositivePossible;
import lombok.Getter;
import lombok.Setter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

@Getter @Setter
public class ValuePossiblePositive extends PositivePossible {

    private ValuePositive firstPositiveValue;
    private ValuePositive secondPositiveValue;

    private DurationPositive duration;

    public ValuePossiblePositive(ValuePositive firstPositiveValue, ValuePositive secondPositiveValue) {
        this.firstPositiveValue = firstPositiveValue;
        this.secondPositiveValue = secondPositiveValue;
    }

    public ValuePossiblePositive(ValuePositive firstPositiveValue, DurationPositive duration) {
        this.firstPositiveValue = firstPositiveValue;
        this.duration = duration;
    }

    @Override
    public Component lore() {
        if (this.secondPositiveValue == null) {
            return Component.text(this.firstPositiveValue.getValue()).color(NamedTextColor.GREEN)
                    .append(Component.text(" or ").color(NamedTextColor.GRAY))
                    .append(Component.text(this.duration.getValue() + "s Duration").color(NamedTextColor.GREEN));
        } else {
            return Component.text(this.firstPositiveValue.getValue()).color(NamedTextColor.GREEN)
                    .append(Component.text(" " + this.firstPositiveValue.getName()).color(NamedTextColor.GREEN))
                    .append(Component.text(" or ").color(NamedTextColor.GRAY))
                    .append(Component.text(this.secondPositiveValue.getValue()).color(NamedTextColor.GREEN))
                    .append(Component.text(" " + this.secondPositiveValue.getName()).color(NamedTextColor.GREEN));
        }
    }
}
