package com.iantapply.wynncraft.command;

import com.iantapply.wynncraft.logger.Logger;
import com.iantapply.wynncraft.logger.LoggingLevel;
import com.iantapply.wynncraft.rank.NonPurchasableRank;
import com.iantapply.wynncraft.rank.PurchasableRank;
import lombok.Getter;
import lombok.Setter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * A class with utilities to manage the core functionality regarding
 * commands registered and used on the Wynncraft server.
 */
@Getter @Setter
public class CommandCore implements CommandExecutor {
    private ArrayList<WynncraftCommand> commands;
    private JavaPlugin plugin;

    /**
     * Created a new instance of the command core to access
     * the command utilities
     * @param plugin The plugin instance to register the commands to
     */
    public CommandCore(JavaPlugin plugin) {
        if (plugin == null) {
            Logger.log(LoggingLevel.ERROR, "The Wynncraft plugin cannot be null. Please provide a valid instance.");
        }

        this.commands = new ArrayList<>();
        this.plugin = plugin;
    }

    /**
     * Stages a command to be registered to the plugin
     * @param command The command to stage
     */
    public void stageCommand(WynncraftCommand command) {
        this.commands.add(command);
    }

    /**
     * Registers all staged commands to the plugin
     */
    public void registerCommands() {
        for (WynncraftCommand command : this.getCommands()) {
            for (String alias : command.aliases()) {
                if (this.getPlugin().getCommand(alias) == null) {
                    Logger.log(LoggingLevel.ERROR, "The command alias '" + alias + "' is not registered in the plugin.yml file. Please add it to the file.");
                }

                this.getPlugin().getCommand(alias).setExecutor(this);
            }
        }
    }

    /**
     * Executes the command, returning whether the command was successful
     * @param sender Source of the command
     * @param command Command which was executed
     * @param label Alias of the command which was used
     * @param args Passed command arguments
     * @return Whether the command was successful
     */
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        for (WynncraftCommand wynncraftCommand : this.getCommands()) {
            // Check if the command run has the same name or alias as the command
            if (command.getName().equalsIgnoreCase(wynncraftCommand.name()) || wynncraftCommand.aliases().contains(command.getName())) {
                // Checks if the sender has the required permissions to execute the command
                if (!this.hasPermission(sender, wynncraftCommand)) {
                    return false;
                }

                // Checks if the sender is a player and if the command is player only
                if (!this.isPlayer(sender) && wynncraftCommand.isPlayerOnly()) {
                    return false;
                }

                // Check if the command has the correct amount of arguments
                if (args.length < wynncraftCommand.minArgs() || args.length > wynncraftCommand.maxArgs()) {
                    Component errorMessage = Component.text("Incorrect usage. The correct syntax is '")
                            .append(Component.text(wynncraftCommand.syntax()).color(NamedTextColor.RED))
                            .append(Component.text("'. Please try again."));

                    sender.sendMessage(errorMessage);
                    return false;
                }

                // Execute the command and call the event that should be triggered when the command is run
                try {
                    wynncraftCommand.execute(sender, args);
                    wynncraftCommand.getTriggerEvent().callEvent();
                } catch (Exception e) {
                    // In the event of incorrect usage, send an error message to the sender and console to notify of the error
                    Component errorMessage = Component.text("An error occurred while executing the command '")
                            .append(Component.text(wynncraftCommand.name()).color(NamedTextColor.RED))
                            .append(Component.text("'. Incorrect usage, the correct syntax is '").color(NamedTextColor.RED))
                            .append(Component.text(wynncraftCommand.syntax())).color(NamedTextColor.RED)
                            .append(Component.text("'. Please try again."));

                    sender.sendMessage(errorMessage);
                    Logger.log(LoggingLevel.ERROR, "An error occurred while executing the command '" + wynncraftCommand.name() + "'. Incorrect usage, the correct syntax is '" + wynncraftCommand.syntax() + "'. Please try again.");
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the sender has the required permissions to execute the command
     * @param sender The sender of the command
     * @param command The command to check the permissions for
     * @return Whether or not the sender has the required permissions to execute the command
     */
    public boolean hasPermission(CommandSender sender, WynncraftCommand command) {
        // Check if the sender has the required permissions to execute the command
        for (CommandPermission permission : command.requiredCommandPermissions()) {
            if (!sender.hasPermission(permission.getPermission())) {
                return false;
            }
        }

        // Check if the sender has the required non-purchasable ranks to execute the command
        for (NonPurchasableRank rank : command.requiredNonPurchasableRanks()) {
            if (!sender.hasPermission(rank.getPermission())) {
                return false;
            }
        }

        // Check if the sender has the required purchasable ranks to execute the command
        for (PurchasableRank rank : command.requiredPurchasableRanks()) {
            if (!sender.hasPermission(rank.getPermission())) {
                return false;
            }
        }

        return true;
    }

    /**
     * Checks if the sender is a player
     * @param sender The sender of the command
     * @return Whether or not the sender is a player
     */
    public boolean isPlayer(CommandSender sender) {
        return sender instanceof Player;
    }
}
