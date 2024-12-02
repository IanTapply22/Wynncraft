package com.iantapply.wynncraft.command.commands.friend;

import com.iantapply.wynncraft.command.WynncraftCommand;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;

public class FriendCommand extends WynncraftCommand {
    @Override
    public String name() {
        return "friend";
    }

    @Override
    public String syntax() {
        return "friend <add/remove/list> <args>";
    }

    @Override
    public String description() {
        return "Handles the in-game friend system.";
    }

    @Override
    public ArrayList<WynncraftCommand> subcommands() {
        ArrayList<WynncraftCommand> subcommands = new ArrayList<>();
        subcommands.add(new FriendAddCommand());
        subcommands.add(new FriendListCommand());
        subcommands.add(new FriendRemoveCommand());

        return subcommands;
    }

    @Override
    public ArrayList<String> aliases() {
        ArrayList<String> aliases = new ArrayList<>();
        aliases.add("friends");

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
