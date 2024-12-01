package com.iantapply.wynncraft.command.commands.game;

import com.iantapply.wynncraft.command.WynncraftCommand;
import org.bukkit.command.CommandSender;

public class DuelCommand extends WynncraftCommand {
    @Override
    public String name() {
        return "duel";
    }

    @Override
    public String syntax() {
        return "duel <player>";
    }

    @Override
    public String description() {
        return "Challenges another player to a duel.";
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
        sender.sendMessage("This is the duel command!");
    }
}
