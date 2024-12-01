package com.iantapply.wynncraft.command.commands.housing;

import com.iantapply.wynncraft.command.WynncraftCommand;
import org.bukkit.command.CommandSender;

public class HousingVisitCommand extends WynncraftCommand {
    @Override
    public String name() {
        return "visit";
    }

    @Override
    public String syntax() {
        return "housing visit";
    }

    @Override
    public String description() {
        return "Opens the housing visiting interface.";
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
        sender.sendMessage("This is the housing visit command!");
    }
}
