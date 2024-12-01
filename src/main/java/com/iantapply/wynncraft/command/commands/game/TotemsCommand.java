package com.iantapply.wynncraft.command.commands.game;

import com.iantapply.wynncraft.command.WynncraftCommand;
import org.bukkit.command.CommandSender;

public class TotemsCommand extends WynncraftCommand {
    @Override
    public String name() {
        return "totems";
    }

    @Override
    public String syntax() {
        return "totems";
    }

    @Override
    public String description() {
        return "Opens the mob totem interface.";
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
        sender.sendMessage("This is the totems command!");
    }
}
