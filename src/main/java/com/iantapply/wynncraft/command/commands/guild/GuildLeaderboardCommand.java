package com.iantapply.wynncraft.command.commands.guild;

import com.iantapply.wynncraft.command.WynncraftCommand;
import org.bukkit.command.CommandSender;

public class GuildLeaderboardCommand extends WynncraftCommand {
    @Override
    public String name() {
        return "leaderboard";
    }

    @Override
    public String syntax() {
        return "guild leaderboard";
    }

    @Override
    public String description() {
        return "Shows a leaderboard (XP-wise) with your guild, 5 guilds above yours, and 4 guilds below yours.";
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
        sender.sendMessage("This is the guild leaderboard command!");
    }
}
