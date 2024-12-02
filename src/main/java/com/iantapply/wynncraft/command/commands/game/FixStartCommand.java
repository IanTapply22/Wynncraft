package com.iantapply.wynncraft.command.commands.game;

import com.iantapply.wynncraft.command.WynncraftCommand;
import org.bukkit.command.CommandSender;

public class FixStartCommand extends WynncraftCommand {
    @Override
    public String name() {
        return "fixstart";
    }

    @Override
    public String syntax() {
        return "fixstart";
    }

    @Override
    public String description() {
        return "Fixes the tutorial quest (King's Recruit) if broken.";
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
        sender.sendMessage("This is the fixstart command!");
    }
}
