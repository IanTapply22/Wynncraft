package com.iantapply.wynncraft.command.commands.guild;

import com.iantapply.wynncraft.command.WynncraftCommand;
import org.bukkit.command.CommandSender;

public class GuildRankCommand extends WynncraftCommand {
    @Override
    public String name() {
        return "rank";
    }

    @Override
    public String syntax() {
        return "guild rank";
    }

    @Override
    public String description() {
        return "Sets the rank of another member of your guild.";
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
        sender.sendMessage("This is the guild rank command!");
    }
}
