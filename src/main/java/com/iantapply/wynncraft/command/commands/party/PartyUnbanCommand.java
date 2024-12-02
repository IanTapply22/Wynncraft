package com.iantapply.wynncraft.command.commands.party;

import com.iantapply.wynncraft.command.WynncraftCommand;
import org.bukkit.command.CommandSender;

public class PartyUnbanCommand extends WynncraftCommand {
    @Override
    public String name() {
        return "unban";
    }

    @Override
    public String syntax() {
        return "party unban <player>";
    }

    @Override
    public String description() {
        return "Unbans the specified player from the party.";
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
        sender.sendMessage("This is the party unban command!");
    }
}
