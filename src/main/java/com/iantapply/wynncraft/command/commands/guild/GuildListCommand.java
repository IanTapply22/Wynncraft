package com.iantapply.wynncraft.command.commands.guild;

import com.iantapply.wynncraft.command.WynncraftCommand;
import org.bukkit.command.CommandSender;

public class GuildListCommand extends WynncraftCommand {
    @Override
    public String name() {
        return "list";
    }

    @Override
    public String syntax() {
        return "guild list";
    }

    @Override
    public String description() {
        return "Lists all members of your guild.";
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
        sender.sendMessage("This is the guild list command!");
    }
}
