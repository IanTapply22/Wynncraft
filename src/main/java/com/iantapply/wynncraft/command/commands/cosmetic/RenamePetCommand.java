package com.iantapply.wynncraft.command.commands.cosmetic;

import com.iantapply.wynncraft.command.WynncraftCommand;
import org.bukkit.command.CommandSender;

public class RenamePetCommand extends WynncraftCommand {
    @Override
    public String name() {
        return "renamepet";
    }

    @Override
    public String syntax() {
        return "renamepet <name>";
    }

    @Override
    public String description() {
        return "Renames a pet or horse.";
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
        sender.sendMessage("This is the renamepet command!");
    }
}
