package com.iantapply.wynncraft.command.commands.game;

import com.iantapply.wynncraft.command.WynncraftCommand;
import org.bukkit.command.CommandSender;

public class SwitchCommand extends WynncraftCommand {
    @Override
    public String name() {
        return "switch";
    }

    @Override
    public String syntax() {
        return "switch <world>";
    }

    @Override
    public String description() {
        return "Switches you to the specified world.";
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
        sender.sendMessage("This is the switch command!");
    }
}
