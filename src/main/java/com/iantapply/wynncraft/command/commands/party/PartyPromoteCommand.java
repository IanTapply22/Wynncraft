package com.iantapply.wynncraft.command.commands.party;

import com.iantapply.wynncraft.command.WynncraftCommand;
import org.bukkit.command.CommandSender;

public class PartyPromoteCommand extends WynncraftCommand {
    @Override
    public String name() {
        return "promote";
    }

    @Override
    public String syntax() {
        return "party promote <player>";
    }

    @Override
    public String description() {
        return "Promotes the specified player to party leader.";
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
        sender.sendMessage("This is the party promote command!");
    }
}
