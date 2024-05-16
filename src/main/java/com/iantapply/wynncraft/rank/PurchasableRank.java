package com.iantapply.wynncraft.rank;

import com.iantapply.wynncraft.rank.benefits.RankBenefit;
import com.iantapply.wynncraft.rank.benefits.VIPBenefits;
import com.iantapply.wynncraft.rank.benefits.VIPPlusBenefits;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;

/**
 * Enum for purchasable ranks on Wynncraft through store.wynncraft.com
 */
@Getter
public enum PurchasableRank {
    VIP(0, "VIP", Component.text("[", NamedTextColor.DARK_GREEN)
            .append(Component.text("VIP", NamedTextColor.GREEN))
            .append(Component.text("]", NamedTextColor.DARK_GREEN)),
            "com.wynncraft.rank.vip", new VIPBenefits(), "8.99"),
    VIP_PLUS(1, "VIP_PLUS", Component.text("[", NamedTextColor.AQUA)
            .append(Component.text("VIP+", NamedTextColor.DARK_AQUA))
            .append(Component.text("]", NamedTextColor.AQUA)),
            "com.wynncraft.rank.vip_plus", new VIPPlusBenefits(), "27.99"),
    HERO(2, "HERO", Component.text("[", NamedTextColor.DARK_PURPLE)
            .append(Component.text("HERO", NamedTextColor.LIGHT_PURPLE))
            .append(Component.text("]", NamedTextColor.DARK_PURPLE)),
            "com.wynncraft.rank.hero", new VIPBenefits(), "59.99"),
    CHAMPION(3, "CHAMPION", Component.text("[", NamedTextColor.YELLOW)
            .append(Component.text("|", NamedTextColor.AQUA))
            .append(Component.text("CHAMPION", NamedTextColor.GOLD))
            .append(Component.text("|", NamedTextColor.AQUA))
            .append(Component.text("]", NamedTextColor.YELLOW)),
            "com.wynncraft.rank.champion", new VIPBenefits(), "170.99");

    private final Integer id;
    private final String name;
    private final TextComponent chatDisplay;
    private final String permission;
    private final RankBenefit benefits;
    private final String price;

    /**
     * Creates a new purchasable rank that can be purchased through store.wynncraft.com
     * @param id The ID of the rank (starts at 0)
     * @param name The name of the rank for internal purposes
     * @param chatDisplay The final displayed chat tag of the rank
     * @param permission The permission node of the rank
     * @param benefits The benefits of the rank
     * @param price The price of the rank in USD (can be converted using the helper)
     */
    PurchasableRank(Integer id, String name, TextComponent chatDisplay, String permission, RankBenefit benefits, String price) {
        this.id = id;
        this.name = name;
        this.chatDisplay = chatDisplay;
        this.permission = permission;
        this.benefits = benefits;
        this.price = price;
    }
}
