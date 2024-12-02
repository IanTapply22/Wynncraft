package com.iantapply.wynncraft.command.commands.game;

import com.iantapply.wynncraft.command.WynncraftCommand;
import org.bukkit.command.CommandSender;

public class ForumCommand extends WynncraftCommand {
    @Override
    public String name() {
        return "forum";
    }

    @Override
    public String syntax() {
        return "forum";
    }

    @Override
    public String description() {
        return "Starts the process of linking your forums account with your game account.";
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
        sender.sendMessage("This is the forum command!");
    }
}
