package com.iantapply.wynncraft.command.commands.administrator;

import com.iantapply.wynncraft.Wynncraft;
import com.iantapply.wynncraft.command.WynncraftCommand;
import com.iantapply.wynncraft.database.model.PlayerModel;
import com.iantapply.wynncraft.logger.Logger;
import com.iantapply.wynncraft.logger.LoggingLevel;
import com.iantapply.wynncraft.player.WynncraftPlayer;
import com.iantapply.wynncraft.rank.Rank;
import com.iantapply.wynncraft.rank.SupportRank;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;
import java.util.ArrayList;

public class SetSupportRankCommand extends WynncraftCommand {
    @Override
    public String name() {
        return "setsupportrank";
    }

    @Override
    public String syntax() {
        return "setsupportrank <rank ID> [player]";
    }

    @Override
    public String description() {
        return "Sets the current players support rank or the specified players support rank";
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
        Player player = (Player) sender;
        WynncraftPlayer wynncraftPlayer;

        if (args.length == 1) {
            if (sender instanceof ConsoleCommandSender) {
                Logger.log(LoggingLevel.ERROR, "Console cannot set rank for itself");
                return;
            }

            wynncraftPlayer = Wynncraft.getInstance().getPlayerCore().getPlayer(player.getUniqueId());
        } else {
            Player targetPlayer = Wynncraft.getInstance().getServer().getPlayer(args[1]);
            if (targetPlayer == null) {
                player.sendMessage("Player not found");
                return;
            }
            wynncraftPlayer = Wynncraft.getInstance().getPlayerCore().getPlayer(targetPlayer.getUniqueId());
        }

        PlayerModel playerModel = wynncraftPlayer.getPlayerModel();

        try {
            if (SupportRank.getRankById(Integer.parseInt(args[0])) == null) {
                playerModel.setSupportRank(null);
                playerModel.populate();
                Wynncraft.getInstance().getPlayerCore().updatePlayer(player.getUniqueId(), playerModel);
                return;
            }

            playerModel.setSupportRank(SupportRank.getRankById(Integer.parseInt(args[0])));
            playerModel.populate();
            Wynncraft.getInstance().getPlayerCore().updatePlayer(player.getUniqueId(), playerModel);
        } catch (SQLException e) {
            Logger.log(LoggingLevel.ERROR, "Failed to populate player model with rank update for " + player.getName());
            Logger.log(LoggingLevel.ERROR, e.getMessage());
        }
    }
}
