package com.iantapply.wynncraft.command;

import com.iantapply.wynncraft.event.wynncraft.WynncraftEvent;
import com.iantapply.wynncraft.rank.NonPurchasableRank;
import com.iantapply.wynncraft.rank.PurchasableRank;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;

/**
 * Represents a command that can be executed by a player or console
 */
public abstract class WynncraftCommand {

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
    public ArrayList<NonPurchasableRank> requiredNonPurchasableRanks() {
        return new ArrayList<>();
    }

    /**
     * The purchasable ranks that are required to execute the command
     * @return The purchasable ranks
     */
    public ArrayList<PurchasableRank> requiredPurchasableRanks() {
        return new ArrayList<>();
    }

    /**
     * Whether or not the command can only be executed by a player
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
     * The method that is called when the command is executed
     * @param sender The sender of the command
     * @param args The arguments of the command
     * @return
     */
    public abstract boolean execute(CommandSender sender, String[] args) throws Exception;
}
