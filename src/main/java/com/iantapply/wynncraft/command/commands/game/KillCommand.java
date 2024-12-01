package com.iantapply.wynncraft.command.commands.game;

import com.iantapply.wynncraft.command.WynncraftCommand;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;

public class KillCommand extends WynncraftCommand {
    @Override
    public String name() {
        return "kill";
    }

    @Override
    public String syntax() {
        return "kill";
    }

    @Override
    public ArrayList<String> aliases() {
        ArrayList<String> aliases = new ArrayList<>();
        aliases.add("suicide");
        aliases.add("die");

        return aliases;
    }

    @Override
    public String description() {
        return "Kill your character (useful if you're stuck).";
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
        sender.sendMessage("This is the kill command!");
    }
}
