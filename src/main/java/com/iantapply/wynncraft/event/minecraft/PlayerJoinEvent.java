package com.iantapply.wynncraft.event.minecraft;
import com.iantapply.wynncraft.Wynncraft;
import com.iantapply.wynncraft.item.WynncraftItemMeta;
import com.iantapply.wynncraft.item.items.WynncraftItem;
import com.iantapply.wynncraft.item.util.VersionUtil;
import com.iantapply.wynncraft.player.PlayerCore;
import com.iantapply.wynncraft.player.WynncraftPlayer;
import net.kyori.adventure.resource.ResourcePackInfo;
import net.kyori.adventure.resource.ResourcePackRequest;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.net.URI;
import java.util.UUID;

public class PlayerJoinEvent implements Listener {

    @EventHandler
    public void onPlayerJoin(org.bukkit.event.player.PlayerJoinEvent event) {
        Player player = event.getPlayer();
        PlayerCore playerCore = Wynncraft.getInstance().getPlayerCore();
        playerCore.initializePlayer(player);
        WynncraftPlayer wynncraftPlayer = playerCore.getPlayer(player.getUniqueId());

        /* Update internal Wynncraft items with new lore and new properties while maintaining old stats */
        for (int i = 0; i < player.getInventory().getContents().length; i++) {
            ItemStack item = player.getInventory().getContents()[i];
            if (item == null) continue;

            ItemStack wynncraftNBTItem = new ItemStack(item);

            if (WynncraftItemMeta.getStringFlag("uuid", wynncraftNBTItem.getItemMeta()).isEmpty()) continue;

            WynncraftItem wynncraftItem = Wynncraft.getInstance().getItemCore().getItemByUUID(WynncraftItemMeta.getStringFlag("uuid", wynncraftNBTItem.getItemMeta()));

            if (wynncraftItem == null) continue;

            if (VersionUtil.isLowerLatestVersion(WynncraftItemMeta.getStringFlag("version", wynncraftNBTItem.getItemMeta()), wynncraftItem.version())) {
                ItemStack updatedItem = wynncraftItem.build(wynncraftNBTItem);
                player.getInventory().setItem(i, updatedItem);
            }
        }

        /* Get resource pack information */
        UUID resourcePackUUID = UUID.fromString(Wynncraft.getInstance().getConfigurationCore().getString("WYNNCRAFT_RESOURCE_PACK_UUID"));
        String resourcePackURIFile = String.format("%s_%s", Wynncraft.getInstance().getConfigurationCore().getString("WYNNCRAFT_RESOURCE_ACTIVE_PACK_TYPE"), Wynncraft.getInstance().getConfigurationCore().getString("WYNNCRAFT_RESOURCE_ACTIVE_PACK_ID"));
        String uriPrefix = Wynncraft.getInstance().getConfigurationCore().getBoolean("WYNNCRAFT_CDN_SERVER_SSL") ? "https" : "http";
        URI resourcePackURI = URI.create(String.format("%s://%s:%s%s%s", uriPrefix, Wynncraft.getInstance().getConfigurationCore().getString("WYNNCRAFT_CDN_SERVER_HOST"), Wynncraft.getInstance().getConfigurationCore().getString("WYNNCRAFT_CDN_SERVER_PORT"), Wynncraft.getInstance().getConfigurationCore().getString("WYNNCRAFT_RESOURCE_PACK_CONTEXT"), resourcePackURIFile));
        boolean forceResourcePack = Wynncraft.getInstance().getConfigurationCore().getBoolean("WYNNCRAFT_FORCE_RESOURCE_PACK");

        /* Teleport player to limbo while awaiting resource pack */
        Location limboLocation = Wynncraft.getInstance().getConfigurationCore().getLocation("WYNNCRAFT_LIMBO_COORDINATES");
        player.teleport(limboLocation);

        /* Send resource pack to player and configure it */
        player.sendResourcePacks(ResourcePackRequest.resourcePackRequest().packs(ResourcePackInfo.resourcePackInfo(resourcePackUUID, resourcePackURI, "")).required(forceResourcePack).build());
    }
}
