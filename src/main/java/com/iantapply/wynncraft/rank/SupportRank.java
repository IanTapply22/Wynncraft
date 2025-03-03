package com.iantapply.wynncraft.rank;

import com.iantapply.wynncraft.rank.benefits.RankBenefit;
import com.iantapply.wynncraft.rank.benefits.VIPBenefits;
import com.iantapply.wynncraft.rank.benefits.VIPPlusBenefits;
import lombok.Getter;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;

/**
 * Enum for purchasable ranks on Wynncraft through store.wynncraft.com
 */
@Getter
public enum SupportRank {
    VIP(0, "Vip", Component.text("\uE00F").color(NamedTextColor.DARK_GREEN)
            .font(Key.key("minecraft:rank")),
            "com.wynncraft.rank.vip", new VIPBenefits(), "8.99"),
    VIP_PLUS(1, "Vip Plus", Component.text("\uE010").color(NamedTextColor.BLUE)
            .font(Key.key("minecraft:rank")),
            "com.wynncraft.rank.vip_plus", new VIPPlusBenefits(), "27.99"),
    HERO(2, "Hero", Component.text("\uE007").color(NamedTextColor.DARK_PURPLE)
            .font(Key.key("minecraft:rank")),
            "com.wynncraft.rank.hero", new VIPBenefits(), "59.99"),
    CHAMPION(3, "Champion", Component.text("\uE003").color(NamedTextColor.YELLOW)
            .font(Key.key("minecraft:rank")),
            "com.wynncraft.rank.champion", new VIPBenefits(), "170.99");

    private final Integer id;
    private final String name;
    private final Component chatDisplay;
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
    SupportRank(Integer id, String name, Component chatDisplay, String permission, RankBenefit benefits, String price) {
        this.id = id;
        this.name = name;
        this.chatDisplay = chatDisplay;
        this.permission = permission;
        this.benefits = benefits;
        this.price = price;
    }

    /**
     * Retrieves a rank by its ID
     * @param id The ID of the rank
     * @return The rank if found, otherwise null
     */
    public static SupportRank getRankById(int id) {
        for (SupportRank rank : SupportRank.values()) {
            if (rank.getId() == id) {
                return rank;
            }
        }
        return null;
    }
}
