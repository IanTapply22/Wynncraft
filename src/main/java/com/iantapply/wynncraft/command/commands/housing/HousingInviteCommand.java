package com.iantapply.wynncraft.command.commands.housing;

import com.iantapply.wynncraft.command.WynncraftCommand;
import org.bukkit.command.CommandSender;

public class HousingInviteCommand extends WynncraftCommand {
    @Override
    public String name() {
        return "invite";
    }

    @Override
    public String syntax() {
        return "housing invite <player>";
    }

    @Override
    public String description() {
        return "Invites the specified player to your housing.";
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
        sender.sendMessage("This is the housing invite command!");
    }
}
