package com.iantapply.wynncraft.command.commands.ignore;

import com.iantapply.wynncraft.command.WynncraftCommand;
import org.bukkit.command.CommandSender;

public class IgnoreRemoveCommand extends WynncraftCommand {
    @Override
    public String name() {
        return "remove";
    }

    @Override
    public String syntax() {
        return "ignore remove <player>";
    }

    @Override
    public String description() {
        return "Removes the provided player from your ignore list.";
    }

    @Override
    public int minArgs() {
        return 1;
    }

    @Override
    public int maxArgs() {
        return 1;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        sender.sendMessage("This is the ignore remove command!");
    }
}
