package com.iantapply.wynncraft.event;
import com.iantapply.wynncraft.database.DatabaseHelpers;
import com.iantapply.wynncraft.database.model.PlayerModel;
import com.iantapply.wynncraft.logger.Logger;
import com.iantapply.wynncraft.logger.LoggingLevel;
import com.iantapply.wynncraft.rank.NonPurchasableRank;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.sql.Timestamp;

public class PlayerJoinEvent implements Listener {

    @EventHandler
    public void onPlayerJoin(org.bukkit.event.player.PlayerJoinEvent event) {
        Player player = event.getPlayer();
        PlayerModel playerModel = new PlayerModel(player.getUniqueId());
        try {
            if (DatabaseHelpers.selectRow(playerModel.database().connect(true), playerModel.table(), "uuid = CAST(? AS UUID)", player.getUniqueId()) == null) {
                playerModel.setUsername(player.getName());
                playerModel.setOnline(true);
                playerModel.setServer("WA3");
                playerModel.setRank(NonPurchasableRank.PLAYER);
                playerModel.setVeteran(false);
                playerModel.setFirstJoin(new Timestamp(System.currentTimeMillis()));
                playerModel.setLastJoin(new Timestamp(System.currentTimeMillis()));
                playerModel.setPublicProfile(true);

                playerModel.populate();
                playerModel.database().disconnect();
            } else {
                playerModel.setUsername(player.getName());
                playerModel.setOnline(true);
                playerModel.setServer("WA3");
                playerModel.setLastJoin(new Timestamp(System.currentTimeMillis()));

                playerModel.populate();
                playerModel.database().disconnect();
            }
        } catch (Exception e) {
            player.sendMessage("Could not execute query to database! Please see the console logs.");
            Logger.log(LoggingLevel.ERROR, String.format("Could not execute SQL query with error: %s", e.getMessage()));
        }
    }
}
