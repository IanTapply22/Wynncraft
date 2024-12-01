package com.iantapply.wynncraft.command.commands.housing;

import com.iantapply.wynncraft.command.WynncraftCommand;
import org.bukkit.command.CommandSender;

public class HousingLeaveCommand extends WynncraftCommand {
    @Override
    public String name() {
        return "leave";
    }

    @Override
    public String syntax() {
        return "house leave";
    }

    @Override
    public String description() {
        return "Leaves the housing world and routes you back to Wynncraft.";
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
        sender.sendMessage("This is the housing leave command!");
    }
}
