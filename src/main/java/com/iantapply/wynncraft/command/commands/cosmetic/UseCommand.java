package com.iantapply.wynncraft.command.commands.cosmetic;

import com.iantapply.wynncraft.command.WynncraftCommand;
import org.bukkit.command.CommandSender;

public class UseCommand extends WynncraftCommand {
    @Override
    public String name() {
        return "use";
    }

    @Override
    public String syntax() {
        return "use";
    }

    @Override
    public String description() {
        return "Activate items that you have purchased from the store or claimed from Loot Crates.";
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
        sender.sendMessage("This is the use command!");
    }
}
