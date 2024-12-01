package com.iantapply.wynncraft.command.commands.guild;

import com.iantapply.wynncraft.command.WynncraftCommand;
import org.bukkit.command.CommandSender;

public class GuildKickCommand extends WynncraftCommand {
    @Override
    public String name() {
        return "kick";
    }

    @Override
    public String syntax() {
        return "guild kick <player>";
    }

    @Override
    public String description() {
        return "Kicks the provided player from your guild.";
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
        sender.sendMessage("This is the guild kick command!");
    }
}
