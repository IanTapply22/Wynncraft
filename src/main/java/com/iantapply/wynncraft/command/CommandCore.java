package com.iantapply.wynncraft.command;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

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
            throw new IllegalArgumentException("The Wynncraft plugin cannot be null. Please provide a valid instance.");
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
    // TODO: Fix the executor possibly having JavaPlugin as null
    public void registerCommands() {
        for (WynncraftCommand command : this.getCommands()) {
            for (String alias : command.aliases()) {
                this.getPlugin().getCommand(alias).setExecutor(this);
            }
        }
    }

    // TODO: Implement this properly, this is auto generated
    // TODO: Check requirements of the executor
    // TODO: Use the other parameters in the creation of a command such as args, syntax, etc.
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        for (WynncraftCommand wynncraftCommand : this.getCommands()) {
            if (command.getName().equalsIgnoreCase(wynncraftCommand.name())) {
                try {
                    wynncraftCommand.execute(sender, args);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return true;
            }
        }
        return false;
    }
}
