package com.iantapply.wynncraft.command.commands.party;

import com.iantapply.wynncraft.command.WynncraftCommand;
import org.bukkit.command.CommandSender;

public class PartyBanCommand extends WynncraftCommand {
    @Override
    public String name() {
        return "ban";
    }

    @Override
    public String syntax() {
        return "party ban <player>";
    }

    @Override
    public String description() {
        return "Bans the specified player from the party.";
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
        sender.sendMessage("This is the party ban command!");
    }
}
