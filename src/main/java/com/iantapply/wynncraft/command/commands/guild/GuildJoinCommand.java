package com.iantapply.wynncraft.command.commands.guild;

import com.iantapply.wynncraft.command.WynncraftCommand;
import org.bukkit.command.CommandSender;

public class GuildJoinCommand extends WynncraftCommand {
    @Override
    public String name() {
        return "join";
    }

    @Override
    public String syntax() {
        return "guild join <tag>";
    }

    @Override
    public String description() {
        return "Accepts the invite and joins the provided guild.";
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
        sender.sendMessage("This is the guild join command!");
    }
}
