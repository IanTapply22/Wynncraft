package com.iantapply.wynncraft.command.commands.game;

import com.iantapply.wynncraft.command.WynncraftCommand;
import org.bukkit.command.CommandSender;

public class DailyCommand extends WynncraftCommand {
    @Override
    public String name() {
        return "daily";
    }

    @Override
    public String syntax() {
        return "daily";
    }

    @Override
    public String description() {
        return "Collects rewards of the daily objective completion.";
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
        sender.sendMessage("This is the daily command!");
    }
}
