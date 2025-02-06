package com.iantapply.wynncraft.command.commands.game;

import com.iantapply.wynncraft.command.WynncraftCommand;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;

/**
 * A command that opens the character selector menu that cna be used to change
 * to a different character/profile
 */
public class ClassCommand  extends WynncraftCommand {
    @Override
    public String name() {
        return "class";
    }

    @Override
    public String syntax() {
        return "class";
    }

    @Override
    public ArrayList<String> aliases() {
        ArrayList<String> aliases = new ArrayList<>();
        aliases.add("classes");

        return aliases;
    }

    @Override
    public String description() {
        return "Opens the character selector.";
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
        sender.sendMessage("This is the class command!");
    }
}
