package com.iantapply.wynncraft.command.commands.guild;

import com.iantapply.wynncraft.command.WynncraftCommand;
import org.bukkit.command.CommandSender;

public class GuildAttackCommand extends WynncraftCommand {
    @Override
    public String name() {
        return "attack";
    }

    @Override
    public String syntax() {
        return "guild attack";
    }

    @Override
    public String description() {
        return "Attack the territory you are currently in.";
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
        sender.sendMessage("This is the guild attack command!");
    }
}
