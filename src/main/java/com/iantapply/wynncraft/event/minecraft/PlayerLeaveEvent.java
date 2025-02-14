package com.iantapply.wynncraft.event.minecraft;

import com.iantapply.wynncraft.Wynncraft;
import com.iantapply.wynncraft.database.model.PlayerModel;
import com.iantapply.wynncraft.logger.Logger;
import com.iantapply.wynncraft.logger.LoggingLevel;
import com.iantapply.wynncraft.player.PlayerCore;
import com.iantapply.wynncraft.player.WynncraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.sql.SQLException;

public class PlayerLeaveEvent implements Listener {

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        PlayerCore playerCore = Wynncraft.getInstance().getPlayerCore();
        WynncraftPlayer wynncraftPlayer = playerCore.getPlayer(player.getUniqueId());

        if (wynncraftPlayer == null) {
            Logger.log(LoggingLevel.ERROR, "Could not find Wynncraft player instance for player with UUID " + player.getUniqueId() + ". This could be because you used the reload command.");
            return;
        }

        wynncraftPlayer.getPlayerModel().setOnline(false);
        playerCore.removePlayer(player.getUniqueId());

        try {
            wynncraftPlayer.getPlayerModel().populate();
        } catch (SQLException e) {
            Logger.log(LoggingLevel.ERROR, String.format("Could not save data for player with UUID %s, with error: %s", player.getUniqueId(), e.getMessage()));
        }
    }
}
