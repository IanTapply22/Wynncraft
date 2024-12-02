package com.iantapply.wynncraft.command.commands.game;

import com.iantapply.wynncraft.command.WynncraftCommand;
import org.bukkit.command.CommandSender;

public class FindCommand extends WynncraftCommand {
    @Override
    public String name() {
        return "find";
    }

    @Override
    public String syntax() {
        return "find <player>";
    }

    @Override
    public String description() {
        return "Find out what world the provided player is currently on.";
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
        sender.sendMessage("This is the find command!");
    }
}
