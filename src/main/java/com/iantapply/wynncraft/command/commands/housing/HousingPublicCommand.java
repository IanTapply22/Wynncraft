package com.iantapply.wynncraft.command.commands.housing;

import com.iantapply.wynncraft.command.WynncraftCommand;
import org.bukkit.command.CommandSender;

public class HousingPublicCommand extends WynncraftCommand {
    @Override
    public String name() {
        return "public";
    }

    @Override
    public String syntax() {
        return "housing public";
    }

    @Override
    public String description() {
        return "Makes your housing publicly accessible.";
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
        sender.sendMessage("This is the housing public command!");
    }
}
