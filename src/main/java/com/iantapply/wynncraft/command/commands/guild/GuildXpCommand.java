package com.iantapply.wynncraft.command.commands.guild;

import com.iantapply.wynncraft.command.WynncraftCommand;
import org.bukkit.command.CommandSender;

public class GuildXpCommand extends WynncraftCommand {
    @Override
    public String name() {
        return "xp";
    }

    @Override
    public String syntax() {
        return "guild xp <percentage>";
    }

    @Override
    public String description() {
        return "Set the percentage of your gained XP that will go towards your guild.";
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
        sender.sendMessage("This is the guild xp command!");
    }
}
