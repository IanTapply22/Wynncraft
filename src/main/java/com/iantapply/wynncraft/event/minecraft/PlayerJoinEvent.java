package com.iantapply.wynncraft.event.minecraft;
import com.iantapply.wynncraft.Wynncraft;
import com.iantapply.wynncraft.item.WynncraftItemMeta;
import com.iantapply.wynncraft.item.items.WynncraftItem;
import com.iantapply.wynncraft.item.util.VersionUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class PlayerJoinEvent implements Listener {

    @EventHandler
    public void onPlayerJoin(org.bukkit.event.player.PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Wynncraft.getInstance().getPlayerCore().initializePlayer(player);

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
    }
}
