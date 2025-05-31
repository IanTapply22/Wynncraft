package com.iantapply.wynncraft.command.commands.administrator;

import com.iantapply.wynncraft.Wynncraft;
import com.iantapply.wynncraft.command.WynncraftCommand;
import com.iantapply.wynncraft.database.model.PlayerModel;
import com.iantapply.wynncraft.logger.Logger;
import com.iantapply.wynncraft.logger.LoggingLevel;
import com.iantapply.wynncraft.player.WynncraftPlayer;
import com.iantapply.wynncraft.rank.Rank;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class SetRankCommand extends WynncraftCommand {
    @Override
    public String name() {
        return "setrank";
    }

    @Override
    public String syntax() {
        return "setrank <rank ID> [player]";
    }

    @Override
    public String description() {
        return "Sets the current players rank or the specified players rank";
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
    public HashMap<String, Integer> tabCompleteOptions() {
        HashMap<String, Integer> options = new HashMap<>();
        for (Rank rank : Rank.values()) {
            String option = rank.getShortenedName() != null ? rank.getShortenedName() : rank.getName();
            options.put(option, 0);
        }

        for (Player player : Bukkit.getOnlinePlayers()) {
            options.put(player.getName(), 1);
        }
        return options;
    }

    @Override
    public boolean isDevelopment() {
        return true;
    }

    @Override
    public ArrayList<Rank> requiredRanks() {
        ArrayList<Rank> ranks = new ArrayList<>();
        ranks.add(Rank.ADMINISTRATOR);
        ranks.add(Rank.OWNER);
        return ranks;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        WynncraftPlayer wynncraftPlayer;
        Player player;

        if (args.length == 1) {
            if (!(sender instanceof Player)) {
                Logger.log(LoggingLevel.ERROR, "Console cannot set rank for itself");
                return;
            }
            player = (Player) sender;

        } else {
            player = Wynncraft.getInstance().getServer().getPlayer(args[1]);
            if (player == null) {
                sender.sendMessage("Player not found");
                return;
            }
        }
        wynncraftPlayer = Wynncraft.getInstance().getPlayerCore().getPlayer(player.getUniqueId());

        PlayerModel playerModel = wynncraftPlayer.getPlayerModel();

        try {
            Rank rank = Rank.getRankByName(args[0]);
            if (rank == null) {
                rank = Rank.getRankByShortenedName(args[0]);
            }
            if (rank == null) {
                playerModel.setRank(Rank.PLAYER);
                playerModel.populate();
                Wynncraft.getInstance().getPlayerCore().updatePlayer(player.getUniqueId(), playerModel);
                return;
            }

            playerModel.setRank(rank);

            playerModel.populate();
            Wynncraft.getInstance().getPlayerCore().updatePlayer(player.getUniqueId(), playerModel);

            // Update commands to trigger new available commands for the player
            player.updateCommands();

            sender.sendMessage("Rank set to " + playerModel.getRank().getName() + " for " + player.getName());
        } catch (SQLException e) {
            Logger.log(LoggingLevel.ERROR, "Failed to populate player model with rank update for " + player.getName());
            Logger.log(LoggingLevel.ERROR, e.getMessage());
        }
    }
}
