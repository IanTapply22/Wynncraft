package com.iantapply.wynncraft.command.commands.guild;

import com.iantapply.wynncraft.command.WynncraftCommand;
import org.bukkit.command.CommandSender;

public class GuildStatsCommand extends WynncraftCommand {
    @Override
    public String name() {
        return "stats";
    }

    @Override
    public String syntax() {
        return "guild stats";
    }

    @Override
    public String description() {
        return "Show the current level, XP, tag, name, and player count of your guild.";
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
        sender.sendMessage("This is the guild stats command!");
    }
}
