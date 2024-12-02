package com.iantapply.wynncraft.command.commands.friend;

import com.iantapply.wynncraft.command.WynncraftCommand;
import org.bukkit.command.CommandSender;

public class FriendRemoveCommand extends WynncraftCommand {
    @Override
    public String name() {
        return "remove";
    }

    @Override
    public String syntax() {
        return "friend remove <player>";
    }

    @Override
    public String description() {
        return "Removes the provided player from your friend list.";
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
        sender.sendMessage("This is the friend remove command!");
    }
}
