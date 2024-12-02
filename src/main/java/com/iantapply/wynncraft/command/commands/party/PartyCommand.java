package com.iantapply.wynncraft.command.commands.party;

import com.iantapply.wynncraft.command.WynncraftCommand;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;

public class PartyCommand extends WynncraftCommand {
    @Override
    public String name() {
        return "party";
    }

    @Override
    public String syntax() {
        return "party <command> <args>";
    }

    @Override
    public String description() {
        return "Handles all party related actions.";
    }

    @Override
    public ArrayList<WynncraftCommand> subcommands() {
        ArrayList<WynncraftCommand> subcommands = new ArrayList<>();
        subcommands.add(new PartyCreateCommand());
        subcommands.add(new PartyInviteCommand());
        subcommands.add(new PartyLeaveCommand());
        subcommands.add(new PartyListCommand());
        subcommands.add(new PartyKickCommand());
        subcommands.add(new PartyPromoteCommand());
        subcommands.add(new PartyBanCommand());
        subcommands.add(new PartyUnbanCommand());

        return subcommands;
    }

    @Override
    public ArrayList<String> aliases() {
        ArrayList<String> aliases = new ArrayList<>();
        aliases.add("p");

        return aliases;
    }

    @Override
    public int minArgs() {
        return 1;
    }

    @Override
    public int maxArgs() {
        return 2;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {}
}
