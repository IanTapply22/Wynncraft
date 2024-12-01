package com.iantapply.wynncraft.command.commands.game;

import com.iantapply.wynncraft.command.WynncraftCommand;
import org.bukkit.command.CommandSender;

public class ReportCommand extends WynncraftCommand {
    @Override
    public String name() {
        return "report";
    }

    @Override
    public String syntax() {
        return "report <player> <reason>";
    }

    @Override
    public String description() {
        return "Report the provided player to the Wynncraft Moderators.";
    }

    @Override
    public int minArgs() {
        return 2;
    }

    @Override
    public int maxArgs() {
        return 2;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        sender.sendMessage("This is the report command!");
    }
}
