package com.iantapply.wynncraft.command.commands.nbs;

import com.iantapply.wynncraft.command.WynncraftCommand;
import org.bukkit.command.CommandSender;

public class NbsStopCommand extends WynncraftCommand {
    @Override
    public String name() {
        return "stop";
    }

    @Override
    public String syntax() {
        return "stop";
    }

    @Override
    public String description() {
        return "Stops playing the currently playing NBS song.";
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
        try {
            NbsCore.player.destroy();
            sender.sendMessage("Stopped playing NBS song.");
        } catch(Exception e) {
            sender.sendMessage("Could not stop playing NBS song.");
        }
    }
}
