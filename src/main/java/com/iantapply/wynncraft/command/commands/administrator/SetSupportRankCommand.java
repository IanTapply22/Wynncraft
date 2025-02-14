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
        return "setsupportrank <rank ID>";
    }

    @Override
    public String description() {
        return "Sets your support rank";
    }

    @Override
    public boolean isPlayerOnly() {
        return true;
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
    public boolean isDevelopment() {
        return true;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        WynncraftPlayer wynncraftPlayer = Wynncraft.getInstance().getPlayerCore().getPlayer(player.getUniqueId());
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
