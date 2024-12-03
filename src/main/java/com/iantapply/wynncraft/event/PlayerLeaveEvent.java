package com.iantapply.wynncraft.event;

import com.iantapply.wynncraft.database.model.PlayerModel;
import com.iantapply.wynncraft.logger.Logger;
import com.iantapply.wynncraft.logger.LoggingLevel;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.sql.SQLException;

public class PlayerLeaveEvent implements Listener {

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        PlayerModel playerModel = new PlayerModel();

        playerModel.setUuid(player.getUniqueId());
        playerModel.setOnline(false);

        try {
            playerModel.populate();
        } catch (SQLException e) {
            Logger.log(LoggingLevel.ERROR, String.format("Could not save data for player with UUID %s, with error: %s", player.getUniqueId(), e.getMessage()));
        }
    }
}
