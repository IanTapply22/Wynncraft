package com.iantapply.wynncraft.command.commands.party;

import com.iantapply.wynncraft.command.WynncraftCommand;
import org.bukkit.command.CommandSender;

public class PartyListCommand extends WynncraftCommand {
    @Override
    public String name() {
        return "list";
    }

    @Override
    public String syntax() {
        return "party list";
    }

    @Override
    public String description() {
        return "Lists the users in the party.";
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
        sender.sendMessage("This is the party list command!");
    }
}
