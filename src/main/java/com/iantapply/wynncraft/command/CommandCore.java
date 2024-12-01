package com.iantapply.wynncraft.command;

import com.iantapply.wynncraft.command.commands.friend.FriendCommandsCore;
import com.iantapply.wynncraft.command.commands.game.GameCommandsCore;
import com.iantapply.wynncraft.command.commands.housing.HousingCommandsCore;
import com.iantapply.wynncraft.command.commands.ignore.IgnoreCommandsCore;
import com.iantapply.wynncraft.command.commands.party.PartyCommandsCore;
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
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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
     * Initializes the command core by staging all commands.
     * This is done here to reduce the size of Wynncraft.
     */
    public void initialize() {
        GameCommandsCore gameCommandsCore = new GameCommandsCore();
        PartyCommandsCore partyCommandsCore = new PartyCommandsCore();
        IgnoreCommandsCore ignoreCommandsCore = new IgnoreCommandsCore();
        FriendCommandsCore friendCommandsCore = new FriendCommandsCore();
        HousingCommandsCore housingCommandsCore = new HousingCommandsCore();

        gameCommandsCore.initialize();
        partyCommandsCore.initialize();
        ignoreCommandsCore.initialize();
        friendCommandsCore.initialize();
        housingCommandsCore.initialize();
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
            List<String> aliases = new ArrayList<>();
            if (command.aliases() != null) {
                aliases.addAll(command.aliases());
            }
            if (!aliases.contains(command.name())) {
                aliases.add(command.name());
            }

            for (String alias : aliases) {
                if (this.getPlugin().getCommand(alias) == null) {
                    Logger.log(LoggingLevel.ERROR, "The command alias '" + alias + "' is not registered in the plugin.yml file. Please add it to the file.");
                } else {
                    // Cast and set usage to be nothing so it can appear in help commands and in the proper plugin.yml
                    PluginCommand registeredCommand = (PluginCommand) Objects.requireNonNull(this.getPlugin().getCommand(alias)).setUsage("");
                    Objects.requireNonNull(registeredCommand).setExecutor(this);
                }
            }
        }
    }

    /**
     * Executes the command, returning whether the command was successful
     * @param sender Source of the command
     * @param command Command which was executed
     * @param label Alias of the command which was used
     * @param args Passed command arguments
     * @return Whether the command was handled successfully
     */
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        for (WynncraftCommand wynncraftCommand : this.getCommands()) {
            List<String> aliases = wynncraftCommand.aliases() == null ? List.of() : wynncraftCommand.aliases();

            // Check if the command matches either the name or one of its aliases
            if (command.getName().equalsIgnoreCase(wynncraftCommand.name()) || aliases.contains(command.getName())) {
                if (!this.hasPermission(sender, wynncraftCommand)) return false;
                if (!this.isPlayer(sender) && wynncraftCommand.isPlayerOnly()) return false;

                if (args.length < wynncraftCommand.minArgs() || args.length > wynncraftCommand.maxArgs()) {
                    Component errorMessage = Component.text("Incorrect usage. The correct syntax is '").color(NamedTextColor.RED)
                            .append(Component.text("/" + wynncraftCommand.syntax()).color(NamedTextColor.RED))
                            .append(Component.text("'. Please try again."));

                    sender.sendMessage(errorMessage);
                    return false;
                }

                // If there are sub command available, skip treating as regular command
                if (!wynncraftCommand.subcommands().isEmpty()) {
                    boolean subcommandHandled = false;

                    for (WynncraftCommand subcommand : wynncraftCommand.subcommands()) {
                        if (subcommand.name().equalsIgnoreCase(args[0]) || subcommand.aliases().contains(args[0])) {
                            subcommandHandled = true;

                            if (!this.hasPermission(sender, subcommand)) return false;
                            if (!this.isPlayer(sender) && subcommand.isPlayerOnly()) return false;

                            if ((args.length - 1) < subcommand.minArgs() || (args.length - 1) > subcommand.maxArgs()) {
                                sender.sendMessage(Component.text("Incorrect usage. The correct syntax is '/" + subcommand.syntax() + "'. Please try again.")
                                        .color(NamedTextColor.RED));
                                return false;
                            }

                            try {
                                String[] strippedArguments = Arrays.copyOfRange(args, 1, args.length);
                                subcommand.execute(sender, strippedArguments);

                                if (subcommand.getTriggerEvent() != null) {
                                    subcommand.getTriggerEvent().callEvent();
                                }
                            } catch (Exception e) {
                                sender.sendMessage(Component.text("An error occurred while executing the subcommand '/" + subcommand.name() + "'. Please check the syntax and try again.")
                                        .color(NamedTextColor.RED));
                            }

                            break;
                        }
                    }

                    // No subcommand matched, handle the main command
                    if (!subcommandHandled) {
                        try {
                            wynncraftCommand.execute(sender, args);

                            if (wynncraftCommand.getTriggerEvent() != null) {
                                wynncraftCommand.getTriggerEvent().callEvent();
                            }
                        } catch (Exception e) {
                            sender.sendMessage(Component.text("An error occurred while executing the command '/" + wynncraftCommand.name() + "'. Please check the syntax and try again.")
                                    .color(NamedTextColor.RED));
                        }
                    }
                    return true;
                }

                // Execute the command and call the event that should be triggered when the command is run
                try {
                    wynncraftCommand.execute(sender, args);

                    if (wynncraftCommand.getTriggerEvent() != null) {
                        wynncraftCommand.getTriggerEvent().callEvent();
                    }
                } catch (Exception e) {
                    // In the event of incorrect usage, send an error message to the sender and log the error
                    Component errorMessage = Component.text("An error occurred while executing the command '")
                            .append(Component.text(wynncraftCommand.name()).color(NamedTextColor.RED))
                            .append(Component.text("'. Incorrect usage, the correct syntax is '").color(NamedTextColor.RED))
                            .append(Component.text("/" + wynncraftCommand.syntax())).color(NamedTextColor.RED)
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
     * @return Whether the sender has the required permissions to execute the command
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
     * @return Whether the sender is a player
     */
    public boolean isPlayer(CommandSender sender) {
        return sender instanceof Player;
    }
}
