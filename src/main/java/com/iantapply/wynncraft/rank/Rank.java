package com.iantapply.wynncraft.rank;

import com.iantapply.wynncraft.rank.benefits.ChampionBenefits;
import com.iantapply.wynncraft.rank.benefits.PlayerBenefits;
import com.iantapply.wynncraft.rank.benefits.RankBenefit;
import lombok.Getter;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

/**
 * Enum for non-purchasable ranks such as staff and media players
 * <p>
 * All ranks here are awarded the benefits of a Champion rank level unless
 * otherwise specified
 */
@Getter
public enum Rank {
    PLAYER(0, "Player", null,
            "Players are the default players who join Wynncraft",
            Component.text("").color(NamedTextColor.GRAY),
            "com.wynncraft.rank.player", new PlayerBenefits()),
    MEDIA(1, "Media", null,
            "Granted to those who record and post Wynncraft videos to YouTube or stream Wynncraft",
            Component.text("\uE00A").color(NamedTextColor.LIGHT_PURPLE).font(Key.key("minecraft:rank")),
            "com.wynncraft.rank.media", new ChampionBenefits()),
    MODERATOR(2, "Moderator", "Mod",
            "If you need help or if you have a problem, you can ask them either in game or on the forums",
            Component.text("\uE00B").color(NamedTextColor.GOLD).font(Key.key("minecraft:rank")),
            "com.wynncraft.rank.mod", new ChampionBenefits()),
    ADMINISTRATOR(3, "Administrator", "Admin",
            "Admins are either the owners or developer of Wynncraft",
            Component.text("\uE000").color(NamedTextColor.DARK_RED).font(Key.key("minecraft:rank")),
            "com.wynncraft.rank.admin", new ChampionBenefits()),
    WEB(4, "Web Developer", "Web",
            "Web developers are responsible for maintaining all web infrastructure",
            Component.text("\uE011").color(NamedTextColor.DARK_RED).font(Key.key("minecraft:rank")),
            "com.wynncraft.rank.web", new ChampionBenefits()),
    OWNER(5, "Owner", null,
            "Owner and founder of Wynncraft",
            Component.text("\uE00D").color(NamedTextColor.DARK_RED).font(Key.key("minecraft:rank")),
            "com.wynncraft.rank.owner", new ChampionBenefits()),
    QUALITY_ASSURANCE(6, "Quality Assurance", "QA",
            "Quality assurance ensure that Wynncraft meets the quality standards and performs as expected",
            Component.text("\uE00E").color(NamedTextColor.DARK_AQUA).font(Key.key("minecraft:rank")),
            "com.wynncraft.rank.qa", new ChampionBenefits()),
    MUSIC(7, "Music", null,
            "Creators of the music featured on Wynncraft",
            Component.text("\uE00C").color(NamedTextColor.DARK_AQUA).font(Key.key("minecraft:rank")),
            "com.wynncraft.rank.music", new ChampionBenefits()),
    ITEM_MAKER(8, "Item Maker", "Item",
            "Creators of the items used within the Wynncraft world",
            Component.text("\uE009").color(NamedTextColor.DARK_AQUA).font(Key.key("minecraft:rank")),
            "com.wynncraft.rank.item", new ChampionBenefits()),
    HYBRID(8, "Hybrid", null,
            "????",
            Component.text("\uE008").color(NamedTextColor.DARK_AQUA).font(Key.key("minecraft:rank")),
            "com.wynncraft.rank.hybrid", new ChampionBenefits()),
    GM(9, "GM", null,
                   "The Game Masters are the creators of the game quests, mobs, NPCs and items",
           Component.text("\uE006").color(NamedTextColor.DARK_AQUA).font(Key.key("minecraft:rank")),
            "com.wynncraft.rank.hybrid", new ChampionBenefits()),
    DEVELOPER(10, "Developer", "Dev",
            "One of the game's developers. Developers work on the server network and the game itself",
            Component.text("\uE005").color(NamedTextColor.DARK_RED).font(Key.key("minecraft:rank")),
            "com.wynncraft.rank.developer", new ChampionBenefits()),
    BUILDER(11, "Builder", null,
                      "The game builders create the world of Wynn, building houses, cities, mountains, caves, forests, ruins and everything you see",
              Component.text("\uE002").color(NamedTextColor.DARK_AQUA).font(Key.key("minecraft:rank")),
            "com.wynncraft.rank.builder", new ChampionBenefits()),
    ARTIST(12, "Artist", null,
            "Given to official Wynncraft artists",
            Component.text("\uE001").color(NamedTextColor.DARK_AQUA).font(Key.key("minecraft:rank")),
            "com.wynncraft.rank.artist", new ChampionBenefits());

    private final Integer id;
    private final String name;
    private final String shortenedName;
    private final String description;
    private final Component chatDisplay;
    @Deprecated
    private final String permission; // Currently serves no purpose, but is good to have
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
    Rank(Integer id, String name, String shortenedName, @Deprecated String description, Component chatDisplay, String permission, RankBenefit benefits) {
        this.id = id;
        this.name = name;
        this.shortenedName = shortenedName;
        this.description = description;
        this.chatDisplay = chatDisplay;
        this.permission = permission;
        this.benefits = benefits;
    }

    /**
     * Retrieves a rank by its ID
     * @param id The ID of the rank
     * @return The rank if found, otherwise null
     */
    public static Rank getRankById(int id) {
        for (Rank rank : Rank.values()) {
            if (rank.getId() == id) {
                return rank;
            }
        }
        return null;
    }
}
