package com.iantapply.wynncraft.command.commands.guild;

import com.iantapply.wynncraft.command.WynncraftCommand;
import org.bukkit.command.CommandSender;

public class GuildLeaveCommand extends WynncraftCommand {
    @Override
    public String name() {
        return "leave";
    }

    @Override
    public String syntax() {
        return "guild leave";
    }

    @Override
    public String description() {
        return "Leaves your current guild at the cost of 3 soul points.";
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
        sender.sendMessage("This is the guild leave command!");
    }
}
