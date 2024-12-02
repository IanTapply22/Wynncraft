package com.iantapply.wynncraft.command.commands.guild;

import com.iantapply.wynncraft.command.WynncraftCommand;
import org.bukkit.command.CommandSender;

public class GuildLogCommand extends WynncraftCommand {
    @Override
    public String name() {
        return "log";
    }

    @Override
    public String syntax() {
        return "guild log";
    }

    @Override
    public String description() {
        return "View the log of your guild's activities.";
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
        sender.sendMessage("This is the guild log command!");
    }
}
