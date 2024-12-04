package com.iantapply.wynncraft.rank;

import com.iantapply.wynncraft.rank.benefits.ChampionBenefits;
import com.iantapply.wynncraft.rank.benefits.PlayerBenefits;
import com.iantapply.wynncraft.rank.benefits.RankBenefit;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;

/**
 * Enum for non-purchasable ranks such as staff and media players
 * <p>
 * All ranks here are awarded the benefits of a Champion rank level unless
 * otherwise specified
 */
@Getter
public enum NonPurchasableRank {
    PLAYER(0, "Player", null,
            "Players are the default players who join Wynncraft",
            Component.text(""),
            "com.wynncraft.rank.player", new PlayerBenefits()),
    MEDIA(1, "Media", null,
            "Granted to those who record and post Wynncraft videos to YouTube or stream Wynncraft",
            Component.text("[", NamedTextColor.LIGHT_PURPLE)
            .append(Component.text("Media", NamedTextColor.DARK_PURPLE))
            .append(Component.text("]", NamedTextColor.LIGHT_PURPLE)),
            "com.wynncraft.rank.media", new ChampionBenefits()),
    MODERATOR(2, "Moderator", "Mod",
            "If you need help or if you have a problem, you can ask them either in game or on the forums",
            Component.text("[", NamedTextColor.GOLD)
            .append(Component.text("Mod", NamedTextColor.YELLOW))
            .append(Component.text("]", NamedTextColor.GOLD)),
            "com.wynncraft.rank.mod", new ChampionBenefits()),
    ADMINISTRATOR(3, "Administrator", "Admin",
            "Admins are either the owners or developer of Wynncraft",
            Component.text("[", NamedTextColor.DARK_RED)
            .append(Component.text("Admin", NamedTextColor.RED))
            .append(Component.text("]", NamedTextColor.DARK_RED)),
            "com.wynncraft.rank.admin", new ChampionBenefits());

    private final Integer id;
    private final String name;
    private final String shortenedName;
    private final String description;
    private final TextComponent chatDisplay;
    private final String permission;
    private final RankBenefit benefits;

    /**
     * Creates a new purchasable rank that can be purchased through store.wynncraft.com
     * @param id The ID of the rank (starts at 0)
     * @param name The name of the rank for internal purposes (e.g. "VIP", "VIP_PLUS", "HERO", "CHAMPION")
     * @param description The description of the rank
     * @param chatDisplay The final displayed chat tag of the rank
     * @param permission The permission node of the rank
     * @param benefits The benefits of the rank
     */
    NonPurchasableRank(Integer id, String name, String shortenedName, String description, TextComponent chatDisplay, String permission, RankBenefit benefits) {
        this.id = id;
        this.name = name;
        this.shortenedName = shortenedName;
        this.description = description;
        this.chatDisplay = chatDisplay;
        this.permission = permission;
        this.benefits = benefits;
    }
}
