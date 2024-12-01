package com.iantapply.wynncraft.command.commands.housing;

import com.iantapply.wynncraft.command.WynncraftCommand;
import org.bukkit.command.CommandSender;

public class HousingBanCommand extends WynncraftCommand {
    @Override
    public String name() {
        return "ban";
    }

    @Override
    public String syntax() {
        return "housing ban <player>";
    }

    @Override
    public String description() {
        return "Bans the specified player from your housing.";
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
        sender.sendMessage("This is the housing ban command!");
    }
}
