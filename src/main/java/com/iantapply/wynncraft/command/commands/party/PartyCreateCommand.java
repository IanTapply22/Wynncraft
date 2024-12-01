package com.iantapply.wynncraft.command.commands.party;

import com.iantapply.wynncraft.command.WynncraftCommand;
import org.bukkit.command.CommandSender;

public class PartyCreateCommand extends WynncraftCommand {
    @Override
    public String name() {
        return "create";
    }

    @Override
    public String syntax() {
        return "party create";
    }

    @Override
    public String description() {
        return "Creates a party.";
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
        sender.sendMessage("This is the party create command!");
    }
}
