package com.iantapply.wynncraft.event.minecraft;

import com.iantapply.wynncraft.Wynncraft;
import com.iantapply.wynncraft.configuration.ConfigurationCore;
import com.iantapply.wynncraft.player.PlayerCore;
import com.iantapply.wynncraft.player.WynncraftPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;

import java.util.Arrays;

public class PayerResourcePackStatusEvent implements Listener {

    @EventHandler
    public void onPlayerResourcePackStatus(PlayerResourcePackStatusEvent event) {
        Player player = event.getPlayer();
        PlayerCore playerCore = Wynncraft.getInstance().getPlayerCore();
        ConfigurationCore configurationCore = Wynncraft.getInstance().getConfigurationCore();
        WynncraftPlayer wynncraftPlayer = playerCore.getPlayer(player.getUniqueId());

        String rawCoordinates = configurationCore.getString("WYNNCRAFT_MAIN_WORLD_SPAWN");
        double[] parsedCoordinates = Arrays.stream(rawCoordinates.split(",")).mapToDouble(Double::parseDouble).toArray(); // X, Y, Z

        if (event.getStatus() == PlayerResourcePackStatusEvent.Status.SUCCESSFULLY_LOADED) {
            if (isFirstJoin(wynncraftPlayer)) {
                // TODO: Handle tutorial initiation
                player.teleport(new Location(Bukkit.getWorld(configurationCore.getString("WYNNCRAFT_MAIN_WORLD")), parsedCoordinates[0], parsedCoordinates[1], parsedCoordinates[2], 270, 0));
            } else {
                player.teleport(new Location(Bukkit.getWorld(configurationCore.getString("WYNNCRAFT_MAIN_WORLD")), wynncraftPlayer.getPlayerModel().getLastX(), wynncraftPlayer.getPlayerModel().getLastY(), wynncraftPlayer.getPlayerModel().getLastZ()));
            }
        }
    }

    /**
     * Checks if the player is joining for the first time
     * @param player The player to check
     * @return True if the player is joining for the first time, otherwise false
     */
    private boolean isFirstJoin(WynncraftPlayer player) {
        return player.getPlayerModel().getLastX() == 0 && player.getPlayerModel().getLastY() == 0 && player.getPlayerModel().getLastZ() == 0;
    }
}
