package com.iantapply.wynncraft.command.commands.housing;

import com.iantapply.wynncraft.command.WynncraftCommand;
import org.bukkit.command.CommandSender;

public class HousingKickallCommand extends WynncraftCommand {
    @Override
    public String name() {
        return "kickall";
    }

    @Override
    public String syntax() {
        return "housing kickall";
    }

    @Override
    public String description() {
        return "Kicks all players currently visiting your housing.";
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
        sender.sendMessage("This is the housing kickall command!");
    }
}
