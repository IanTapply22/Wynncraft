package com.iantapply.wynncraft.command.commands.game;

import com.iantapply.wynncraft.command.WynncraftCommand;
import org.bukkit.command.CommandSender;

public class HelpCommand extends WynncraftCommand {
    @Override
    public String name() {
        return "help";
    }

    @Override
    public String syntax() {
        return "help";
    }

    @Override
    public String description() {
        return "Show help for commands.";
    }

    @Override
    public int minArgs() {
        return 0;
    }

    @Override
    public int maxArgs() {
        return 0;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        sender.sendMessage("This is the help command!");
    }
}
