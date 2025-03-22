package com.iantapply.wynncraft.event.minecraft;

import com.iantapply.wynncraft.Wynncraft;
import com.iantapply.wynncraft.configuration.ConfigurationCore;
import com.iantapply.wynncraft.logger.Logger;
import com.iantapply.wynncraft.logger.LoggingLevel;
import com.iantapply.wynncraft.player.PlayerCore;
import com.iantapply.wynncraft.player.WynncraftPlayer;
import net.kyori.adventure.resource.ResourcePackInfo;
import net.kyori.adventure.resource.ResourcePackRequest;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;

import java.net.URI;
import java.util.Arrays;
import java.util.UUID;

public class PayerResourcePackStatusEvent implements Listener {

    @EventHandler
    public void onPlayerResourcePackStatus(PlayerResourcePackStatusEvent event) {
        Player player = event.getPlayer();
        PlayerCore playerCore = Wynncraft.getInstance().getPlayerCore();
        ConfigurationCore configurationCore = Wynncraft.getInstance().getConfigurationCore();
        WynncraftPlayer wynncraftPlayer = playerCore.getPlayer(player.getUniqueId());

        String rawCoordinates = configurationCore.getString("WYNNCRAFT_MAIN_WORLD_SPAWN");
        double[] parsedCoordinates = Arrays.stream(rawCoordinates.split(",")).mapToDouble(Double::parseDouble).toArray(); // X, Y, Z

        switch (event.getStatus()) {
            case SUCCESSFULLY_LOADED -> {
                if (isFirstJoin(wynncraftPlayer)) {
                    // TODO: Handle tutorial initiation
                    player.teleport(new Location(Bukkit.getWorld(configurationCore.getString("WYNNCRAFT_MAIN_WORLD")), parsedCoordinates[0], parsedCoordinates[1], parsedCoordinates[2], 270, 0));
                } else {
                    player.teleport(new Location(Bukkit.getWorld(configurationCore.getString("WYNNCRAFT_MAIN_WORLD")), wynncraftPlayer.getPlayerModel().getLastX(), wynncraftPlayer.getPlayerModel().getLastY(), wynncraftPlayer.getPlayerModel().getLastZ()));
                }
            }
            case FAILED_DOWNLOAD -> {
                boolean forceResourcePack = Wynncraft.getInstance().getConfigurationCore().getBoolean("WYNNCRAFT_FORCE_RESOURCE_PACK");
                UUID resourcePackUUID = UUID.fromString(Wynncraft.getInstance().getConfigurationCore().getString("WYNNCRAFT_RESOURCE_PACK_UUID"));
                String fallbackResourcePackURI = Wynncraft.getInstance().getConfig().getString("WYNNCRAFT_RESOURCE_PACK_FALLBACK");

                if (fallbackResourcePackURI == null) {
                    Logger.log(LoggingLevel.ERROR, "No fallback resource pack URI was found. Please configure one in the configuration file. No pack will be applied.");
                    return;
                }
                player.sendResourcePacks(ResourcePackRequest.resourcePackRequest().packs(ResourcePackInfo.resourcePackInfo(resourcePackUUID, URI.create(fallbackResourcePackURI), "")).required(forceResourcePack).build());
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
