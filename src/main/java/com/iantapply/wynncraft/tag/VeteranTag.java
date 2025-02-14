package com.iantapply.wynncraft.tag;

import com.iantapply.wynncraft.rank.SupportRank;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;

/**
 * The different veteran tags that can be applied to a player based on
 * their previous rank on Wynncraft prior to August 1, 2024 (the 1.12 update)
 */
@Getter
public enum VeteranTag {
    VIP_VETERAN(0, "VIP Veteran", Component.text("[", NamedTextColor.DARK_GREEN)
            .append(Component.text("Vet", NamedTextColor.GREEN))
            .append(Component.text("]", NamedTextColor.DARK_GREEN)), SupportRank.VIP),
    VIP_PLUS_VETERAN(1, "VIP+ Veteran", Component.text("[", NamedTextColor.AQUA)
            .append(Component.text("Vet", NamedTextColor.DARK_AQUA))
            .append(Component.text("]", NamedTextColor.AQUA)), SupportRank.VIP_PLUS),
    HERO_VETERAN(2, "Hero Veteran", Component.text("[", NamedTextColor.DARK_PURPLE)
            .append(Component.text("Vet", NamedTextColor.LIGHT_PURPLE))
            .append(Component.text("]", NamedTextColor.DARK_PURPLE)), SupportRank.HERO),
    CHAMPION_VETERAN(3, "Champion Veteran", Component.text("[", NamedTextColor.YELLOW)
            .append(Component.text("|", NamedTextColor.AQUA))
            .append(Component.text("Vet", NamedTextColor.GOLD))
            .append(Component.text("|", NamedTextColor.AQUA))
            .append(Component.text("]", NamedTextColor.YELLOW)), SupportRank.CHAMPION);

    private final Integer id;
    private final String name;
    private final TextComponent chatDisplay;
    private final SupportRank connectedRank;

    /**
     * Creates a new veteran tag that can be applied to the player if
     * they have the veteran tag enabled
     * @param id The ID of the veteran tag (starts at 0)
     * @param name The name of the veteran tag for internal purposes
     * @param chatDisplay The final displayed chat tag of the veteran tag
     * @param connectedRank The rank that the veteran tag is connected to
     */
    VeteranTag(Integer id, String name, TextComponent chatDisplay, SupportRank connectedRank) {
        this.id = id;
        this.name = name;
        this.chatDisplay = chatDisplay;
        this.connectedRank = connectedRank;
    }
}
