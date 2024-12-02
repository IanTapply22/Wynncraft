package com.iantapply.wynncraft.command.commands.nbs;

import com.iantapply.wynncraft.command.WynncraftCommand;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;

public class NbsCommand extends WynncraftCommand {
    @Override
    public String name() {
        return "nbs";
    }

    @Override
    public String syntax() {
        return "nbs <play|stop> <args>";
    }

    @Override
    public String description() {
        return "Handles the testing and playing of NBS files.";
    }

    @Override
    public ArrayList<WynncraftCommand> subcommands() {
        ArrayList<WynncraftCommand> subcommands = new ArrayList<>();
        subcommands.add(new NbsPlayCommand());
        subcommands.add(new NbsStopCommand());

        return subcommands;
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
