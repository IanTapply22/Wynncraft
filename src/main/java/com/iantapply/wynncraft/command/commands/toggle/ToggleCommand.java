package com.iantapply.wynncraft.command.commands.toggle;

import com.iantapply.wynncraft.command.WynncraftCommand;
import org.bukkit.command.CommandSender;

public class ToggleCommand extends WynncraftCommand {
    @Override
    public String name() {
        return "toggle";
    }

    @Override
    public String syntax() {
        return "toggle <toggle>";
    }

    @Override
    public String description() {
        return "Toggles in-game settings.";
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
        sender.sendMessage("This is the toggle command!");
    }
}
