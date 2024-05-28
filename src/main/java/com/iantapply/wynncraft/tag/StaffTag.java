package com.iantapply.wynncraft.tag;

import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;

/**
 * Staff related tags that can be used to identify the position of staff members on
 * the Wynncraft content team
 */
@Getter
public enum StaffTag {
    WEBDEV(0, "WEBDEV", "Given to the people who helped develop the website", Component.text("[", NamedTextColor.DARK_RED)
            .append(Component.text("WebDev", NamedTextColor.RED))
            .append(Component.text("]", NamedTextColor.DARK_RED))),
    GM(1, "GM", "Given to Game Masters, the creators of item, quests, mobs, etc", Component.text("[", NamedTextColor.DARK_AQUA)
            .append(Component.text("GM", NamedTextColor.AQUA))
            .append(Component.text("]", NamedTextColor.DARK_AQUA))),
    BUILD(2, "BUILD", "Given to the builders who built the physical map", Component.text("[", NamedTextColor.DARK_AQUA)
            .append(Component.text("Build", NamedTextColor.AQUA))
            .append(Component.text("]", NamedTextColor.DARK_AQUA))),
    CMD(3, "CMD", "Given to the talented command blockers who create the cutscenes and puzzles you see in-game", Component.text("[", NamedTextColor.DARK_AQUA)
            .append(Component.text("CMD", NamedTextColor.AQUA))
            .append(Component.text("]", NamedTextColor.DARK_AQUA))),
    HYBRID(4, "HYBRID", "Given to Wynncraft Hybrids; Content Team members who are both builders, GMs, or CMDs", Component.text("[", NamedTextColor.DARK_AQUA)
            .append(Component.text("Hybrid", NamedTextColor.AQUA))
            .append(Component.text("]", NamedTextColor.DARK_AQUA))),
    Item(5, "Item", "Given to the makers and balancers of the various weapons and armor in Wynncraft, the Item Team", Component.text("[", NamedTextColor.DARK_AQUA)
            .append(Component.text("Item", NamedTextColor.AQUA))
            .append(Component.text("]", NamedTextColor.DARK_AQUA))),
    QA(6, "QA", "Given to the members of the Quality Assurance team, who rigorously test several upcoming things in the works", Component.text("[", NamedTextColor.DARK_AQUA)
            .append(Component.text("QA", NamedTextColor.AQUA))
            .append(Component.text("]", NamedTextColor.DARK_AQUA))),
    ART(7, "Art", "Given to the artists responsible for making the several pieces of art seen across Wynncraft, as well as making the Resource Pack and skins", Component.text("[", NamedTextColor.DARK_AQUA)
            .append(Component.text("Art", NamedTextColor.AQUA))
            .append(Component.text("]", NamedTextColor.DARK_AQUA)));

    private final Integer id;
    private final String name;
    private final String description;
    private final TextComponent chatDisplay;

    /**
     * Creates a new staff tag that can be used to identify staff members.
     * If a player has tags disabled, this will not be displayed and their primary
     * rank will be displayed instead.
     * <p>
     * Tags have starts displayed behind them to indicate the importance of a player
     * within the content team. If a moderator is also part of the content team they may
     * also have those stars displayed after their tag.
     * @param id The ID of the staff tag (starts at 0)
     * @param name The name of the staff tag for internal purposes
     * @param description The description of the staff tag
     * @param chatDisplay The final displayed chat tag of the staff tag
     */
    StaffTag(Integer id, String name, String description, TextComponent chatDisplay) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.chatDisplay = chatDisplay;
    }
}
