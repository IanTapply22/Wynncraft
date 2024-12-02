package com.iantapply.wynncraft.command.commands.game;

import com.iantapply.wynncraft.command.WynncraftCommand;
import org.bukkit.command.CommandSender;

public class StreamCommand extends WynncraftCommand {
    @Override
    public String name() {
        return "stream";
    }

    @Override
    public String syntax() {
        return "stream";
    }

    @Override
    public String description() {
        return "Hides the server you are on and shows your name as 'Player' to everyone else. You are invisible to everyone and they are invisible to you, unless they are in your Friend list, Guild, or Party.";
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
        sender.sendMessage("This is the stream command!");
    }
}
