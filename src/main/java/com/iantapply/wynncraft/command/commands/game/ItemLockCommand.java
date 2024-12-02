package com.iantapply.wynncraft.command.commands.game;

import com.iantapply.wynncraft.command.WynncraftCommand;
import org.bukkit.command.CommandSender;

public class ItemLockCommand extends WynncraftCommand {
    @Override
    public String name() {
        return "itemlock";
    }

    @Override
    public String syntax() {
        return "itemlock";
    }

    @Override
    public String description() {
        return "Locks item that is being held, so it cannot be dropped.";
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
        sender.sendMessage("This is the itemlock command!");
    }
}
