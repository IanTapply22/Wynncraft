package com.iantapply.wynncraft.command.commands.game;

import com.iantapply.wynncraft.command.WynncraftCommand;
import org.bukkit.command.CommandSender;

public class FixQuestsCommand extends WynncraftCommand {
    @Override
    public String name() {
        return "fixquests";
    }

    @Override
    public String syntax() {
        return "fixquests";
    }

    @Override
    public String description() {
        return "Returns lost but needed quest items to the player.";
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
        sender.sendMessage("This is the fixquests command!");
    }
}
