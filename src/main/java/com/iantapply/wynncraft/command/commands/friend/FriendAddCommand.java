package com.iantapply.wynncraft.command.commands.friend;

import com.iantapply.wynncraft.command.WynncraftCommand;
import org.bukkit.command.CommandSender;

public class FriendAddCommand extends WynncraftCommand {
    @Override
    public String name() {
        return "add";
    }

    @Override
    public String syntax() {
        return "friend add <player>";
    }

    @Override
    public String description() {
        return "Adds a new player to your friend list.";
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
        sender.sendMessage("This is the friend add command!");
    }
}
