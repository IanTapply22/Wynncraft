package com.iantapply.wynncraft.command.commands.friend;

import com.iantapply.wynncraft.command.WynncraftCommand;
import org.bukkit.command.CommandSender;

public class FriendListCommand extends WynncraftCommand {
    @Override
    public String name() {
        return "list";
    }

    @Override
    public String syntax() {
        return "friend list";
    }

    @Override
    public String description() {
        return "Lists all players that are currently on your friend list.";
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
        sender.sendMessage("This is the friend list command!");
    }
}
