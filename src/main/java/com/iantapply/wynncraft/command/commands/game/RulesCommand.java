package com.iantapply.wynncraft.command.commands.game;

import com.iantapply.wynncraft.command.WynncraftCommand;
import org.bukkit.command.CommandSender;

public class RulesCommand extends WynncraftCommand {
    @Override
    public String name() {
        return "rules";
    }

    @Override
    public String syntax() {
        return "rules";
    }

    @Override
    public String description() {
        return "Displays the server rules.";
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
        sender.sendMessage("This is the rules command!");
    }
}
