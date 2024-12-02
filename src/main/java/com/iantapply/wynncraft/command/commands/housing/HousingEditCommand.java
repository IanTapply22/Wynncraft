package com.iantapply.wynncraft.command.commands.housing;

import com.iantapply.wynncraft.command.WynncraftCommand;
import org.bukkit.command.CommandSender;

public class HousingEditCommand extends WynncraftCommand {
    @Override
    public String name() {
        return "edit";
    }

    @Override
    public String syntax() {
        return "housing edit";
    }

    @Override
    public String description() {
        return "Opens the housing edit interface.";
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
        sender.sendMessage("This is the housing edit command!");
    }
}
