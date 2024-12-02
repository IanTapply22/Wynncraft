package com.iantapply.wynncraft.command.commands.party;

import com.iantapply.wynncraft.command.WynncraftCommand;
import org.bukkit.command.CommandSender;

public class PartyKickCommand extends WynncraftCommand {
    @Override
    public String name() {
        return "kick";
    }

    @Override
    public String syntax() {
        return "party kick <player>";
    }

    @Override
    public String description() {
        return "Kicks the specified player from the party.";
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
        sender.sendMessage("This is the party kick command!");
    }
}
