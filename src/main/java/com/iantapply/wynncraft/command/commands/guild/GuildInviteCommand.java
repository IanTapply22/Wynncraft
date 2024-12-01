package com.iantapply.wynncraft.command.commands.guild;

import com.iantapply.wynncraft.command.WynncraftCommand;
import org.bukkit.command.CommandSender;

public class GuildInviteCommand extends WynncraftCommand {
    @Override
    public String name() {
        return "invite";
    }

    @Override
    public String syntax() {
        return "guild invite";
    }

    @Override
    public String description() {
        return "Invite a player to your guild.";
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
        sender.sendMessage("This is the guild invite command!");
    }
}
