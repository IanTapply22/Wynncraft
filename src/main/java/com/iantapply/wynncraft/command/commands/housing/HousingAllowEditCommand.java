package com.iantapply.wynncraft.command.commands.housing;

import com.iantapply.wynncraft.command.WynncraftCommand;
import org.bukkit.command.CommandSender;

public class HousingAllowEditCommand extends WynncraftCommand {
    @Override
    public String name() {
        return "allowedit";
    }

    @Override
    public String syntax() {
        return "housing allowedit <player>";
    }

    @Override
    public String description() {
        return "Allows the specified player to edit your housing.";
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
        sender.sendMessage("This is the housing allowedit command!");
    }
}
