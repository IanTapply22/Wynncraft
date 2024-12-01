package com.iantapply.wynncraft.command.commands.ignore;

import com.iantapply.wynncraft.command.WynncraftCommand;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;

public class IgnoreCommand extends WynncraftCommand {
    @Override
    public String name() {
        return "ignore";
    }

    @Override
    public String syntax() {
        return "ignore <add/remove> <player>";
    }

    @Override
    public String description() {
        return "Handles the in-game ignore list.";
    }

    @Override
    public ArrayList<WynncraftCommand> subcommands() {
        ArrayList<WynncraftCommand> subcommands = new ArrayList<>();
        subcommands.add(new IgnoreAddCommand());
        subcommands.add(new IgnoreRemoveCommand());

        return subcommands;
    }

    @Override
    public int minArgs() {
        return 1;
    }

    @Override
    public int maxArgs() {
        return 2;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {}
}
