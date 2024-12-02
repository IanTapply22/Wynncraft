package com.iantapply.wynncraft.command.commands.guild;

import com.iantapply.wynncraft.command.WynncraftCommand;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;

public class GuildCommand extends WynncraftCommand {
    @Override
    public String name() {
        return "guild";
    }

    @Override
    public String syntax() {
        return "guild <join|leaderboard|leave|list|stats|log|xp|rank|invite|attack|kick> <args>";
    }

    @Override
    public ArrayList<String> aliases() {
        ArrayList<String> aliases = new ArrayList<>();
        aliases.add("gu");

        return aliases;
    }

    @Override
    public ArrayList<WynncraftCommand> subcommands() {
        ArrayList<WynncraftCommand> subcommands = new ArrayList<>();
        subcommands.add(new GuildAttackCommand());
        subcommands.add(new GuildInviteCommand());
        subcommands.add(new GuildJoinCommand());
        subcommands.add(new GuildKickCommand());
        subcommands.add(new GuildLeaderboardCommand());
        subcommands.add(new GuildLeaveCommand());
        subcommands.add(new GuildListCommand());
        subcommands.add(new GuildLogCommand());
        subcommands.add(new GuildRankCommand());
        subcommands.add(new GuildStatsCommand());
        subcommands.add(new GuildXpCommand());

        return subcommands;
    }

    @Override
    public String description() {
        return "Handles the in-game guilds.";
    }

    @Override
    public int minArgs() {
        return 1;
    }

    @Override
    public int maxArgs() {
        return 2;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {}
}
