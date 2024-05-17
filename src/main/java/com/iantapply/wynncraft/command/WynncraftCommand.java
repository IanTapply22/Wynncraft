package com.iantapply.wynncraft.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public abstract class WynncraftCommand implements CommandExecutor {

    /**
     * The name that is appended after the leading '/' character
     * @return The name
     */
    public String name() {
        return "help";
    }

    public String description() {
        return "A help command";
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        return false;
    }

}
