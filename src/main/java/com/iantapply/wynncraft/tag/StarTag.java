package com.iantapply.wynncraft.tag;

import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;

/**
 * This enum is used for having stars appear behind regular tags to represent
 * the importance that the player has within the content team. This also applied to
 * moderators of the network
 */
@Getter
public enum StarTag {
    STAR_1(1, "TBD", Component.text("⭐").color(NamedTextColor.GRAY)),
    STAR_2(2, "TBD", Component.text("⭐⭐").color(NamedTextColor.GRAY)),
    STAR_3(3, "TBD", Component.text("⭐⭐⭐").color(NamedTextColor.GRAY)),
    STAR_4(4, "TBD", Component.text("⭐⭐⭐⭐").color(NamedTextColor.GRAY)),
    STAR_5(5, "TBD", Component.text("⭐⭐⭐⭐⭐").color(NamedTextColor.GRAY));

    private final Integer id;
    private final String description;
    private final TextComponent chatDisplay;

    /**
     * Creates a new star tag that can be applied to the player if
     * they have the star tag enabled
     * @param id The ID of the star tag (starts at 0)
     * @param description The description of the star tag for internal purposes,
     *                    it should have reasoning for the usage
     * @param chatDisplay The final displayed tag in the chat
     */
    StarTag(Integer id, String description, TextComponent chatDisplay) {
        this.id = id;
        this.description = description;
        this.chatDisplay = chatDisplay;
    }
}
