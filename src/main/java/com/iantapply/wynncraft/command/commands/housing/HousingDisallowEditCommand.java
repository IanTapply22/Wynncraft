package com.iantapply.wynncraft.command.commands.housing;

import com.iantapply.wynncraft.command.WynncraftCommand;
import org.bukkit.command.CommandSender;

public class HousingDisallowEditCommand extends WynncraftCommand {
    @Override
    public String name() {
        return "disallowedit";
    }

    @Override
    public String syntax() {
        return "housing disallowedit <player>";
    }

    @Override
    public String description() {
        return "Disallows the specified player from editing your housing.";
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
        sender.sendMessage("This command is the housing disallowedit command!");
    }
}
