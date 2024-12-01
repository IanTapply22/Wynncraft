package com.iantapply.wynncraft.command.commands.housing;

import com.iantapply.wynncraft.command.WynncraftCommand;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;

public class HousingCommand extends WynncraftCommand {
    @Override
    public String name() {
        return "housing";
    }

    @Override
    public String syntax() {
        return "housing <command> <args>";
    }

    @Override
    public String description() {
        return "Handles all housing related actions.";
    }

    @Override
    public ArrayList<WynncraftCommand> subcommands() {
        ArrayList<WynncraftCommand> subcommands = new ArrayList<>();
        subcommands.add(new HousingEditCommand());
        subcommands.add(new HousingInviteCommand());
        subcommands.add(new HousingVisitCommand());
        subcommands.add(new HousingLeaveCommand());
        subcommands.add(new HousingPublicCommand());
        subcommands.add(new HousingKickCommand());
        subcommands.add(new HousingKickallCommand());
        subcommands.add(new HousingBanCommand());
        subcommands.add(new HousingUnbanCommand());
        subcommands.add(new HousingAllowEditCommand());
        subcommands.add(new HousingDisallowEditCommand());

        return subcommands;
    }

    @Override
    public ArrayList<String> aliases() {
        ArrayList<String> aliases = new ArrayList<>();
        aliases.add("is");

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
