package com.iantapply.wynncraft.command.commands.game;

import com.iantapply.wynncraft.command.WynncraftCommand;
import org.bukkit.command.CommandSender;

public class SkipTutorialCommand extends WynncraftCommand {
    @Override
    public String name() {
        return "skiptutorial";
    }

    @Override
    public String syntax() {
        return "skiptutorial";
    }

    @Override
    public String description() {
        return "Skips the tutorial if you have completed it in another class.";
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
        sender.sendMessage("This is the skiptutorial command!");
    }
}
