package com.iantapply.wynncraft.command.commands.cosmetic;

import com.iantapply.wynncraft.command.WynncraftCommand;
import org.bukkit.command.CommandSender;

public class CratesCommand extends WynncraftCommand {
    @Override
    public String name() {
        return "crates";
    }

    @Override
    public String syntax() {
        return "crates";
    }

    @Override
    public String description() {
        return "Opens teh crate interface.";
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
        sender.sendMessage("This is the crates command!");
    }
}
