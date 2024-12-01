package com.iantapply.wynncraft.command.commands.game;

import com.iantapply.wynncraft.command.WynncraftCommand;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;

public class HubCommand extends WynncraftCommand {
    @Override
    public String name() {
        return "hub";
    }

    @Override
    public String syntax() {
        return "hub";
    }

    @Override
    public ArrayList<String> aliases() {
        ArrayList<String> aliases = new ArrayList<>();
        aliases.add("leave");
        aliases.add("lobby");
        aliases.add("port");
        aliases.add("change");
        aliases.add("wcl");
        aliases.add("servers");

        return aliases;
    }

    @Override
    public String description() {
        return "Moves you back to the lobby.";
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
        sender.sendMessage("This is the hub command!");
    }
}
