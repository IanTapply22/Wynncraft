package com.iantapply.wynncraft.command.commands.cosmetic;

import com.iantapply.wynncraft.command.WynncraftCommand;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;

public class PetCommand extends WynncraftCommand {
    @Override
    public String name() {
        return "pet";
    }

    @Override
    public String syntax() {
        return "pet";
    }

    @Override
    public String description() {
        return "Opens the list of pets interface.";
    }

    @Override
    public ArrayList<String> aliases() {
        ArrayList<String> aliases = new ArrayList<>();
        aliases.add("pets");

        return aliases;
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
        sender.sendMessage("This is the pet command!");
    }
}
