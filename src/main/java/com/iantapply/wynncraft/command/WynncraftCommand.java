package com.iantapply.wynncraft.command;

import com.iantapply.wynncraft.Wynncraft;
import com.iantapply.wynncraft.event.wynncraft.WynncraftEvent;
import com.iantapply.wynncraft.logger.Logger;
import com.iantapply.wynncraft.logger.LoggingLevel;
import com.iantapply.wynncraft.rank.Rank;
import com.iantapply.wynncraft.rank.SupportRank;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

/**
 * Represents a command that can be executed by a player or console
 */
public abstract class WynncraftCommand {

    /**
     * The instance of the Wynncraft plugin. Used for configurations or to comply with registries
     * @return The instance of the Wynncraft plugin
     */
    public final Wynncraft plugin() { return Wynncraft.getInstance(); }

    /**
     * The name that is appended after the leading '/' character
     * @return The name
     */
    public abstract String name();
    /**
     * The syntax of the command to display in the help menu and other places
     * This should be in the format of "command [args]"
     * @return The syntax
     */
    public abstract String syntax();

    /**
     * The description of the command to display in the help menu and other places
     * @return The description
     */
    public abstract String description();

    /**
     * The subcommands that are bound to the current command, this being provided will
     * override the execute method of the parent command.
     * @return An arraylist containing all commands that are subcommands of this base command
     */
    public ArrayList<WynncraftCommand> subcommands() { return new ArrayList<>(); }

    /**
     * The aliases of the command that are alternatives to the primary name
     * @return The aliases as an array of strings
     */
    public ArrayList<String> aliases() {
        return new ArrayList<>();
    }

    /**
     * The minimum amount of arguments that are required to execute the command
     * @return The minimum amount of arguments
     */
    public abstract int minArgs();

    /**
     * The maximum amount of arguments that are required to execute the command
     * @return The maximum amount of arguments
     */
    public abstract int maxArgs();

    /**
     * The permission strings that are required to execute the command
     * @return The permission strings
     */
    public ArrayList<CommandPermission> requiredCommandPermissions() {
        return new ArrayList<>();
    }

    /**
     * The non-purchasable ranks that are required to execute the command
     * @return The non-purchasable ranks
     */
    public ArrayList<Rank> requiredRanks() {
        return new ArrayList<>();
    }

    /**
     * The purchasable ranks that are required to execute the command
     * @return The purchasable ranks
     */
    public ArrayList<SupportRank> requiredSupportRanks() {
        return new ArrayList<>();
    }

    /**
     * Whether the command can only be executed by a player
     * @return A boolean to determine if the command is player executable only
     */
    public boolean isPlayerOnly() {
        return false;
    }

    /**
     * The event that triggers when the command is executed
     * @return The event that is called when the command is run
     */
    public WynncraftEvent getTriggerEvent() {
        return null;
    };

    /**
     * Whether the command is in development and should not be used in production
     * @return A boolean to determine if the command is in development
     */
    public boolean isDevelopment() { return false;}

    /**
     * Gets the player from the command sender if the command is player only
     * @param sender The sender of the command
     * @return The player if the command is player only, otherwise it is null and not cast into player object
     */
    public Player getPlayer(CommandSender sender) {
        if (this.isPlayerOnly()) return (Player) sender;

        Logger.log(LoggingLevel.ERROR, String.format("The command %s is not marked as player only and attempted to cast a non-player object into player.", this.name()));
        return null;
    }

    /**
     * The method that is called when the command is executed
     *
     * @param sender The sender of the command
     * @param args   The arguments of the command
     */
    public abstract void execute(CommandSender sender, String[] args);
}
