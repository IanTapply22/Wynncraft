package com.iantapply.wynncraft.event.minecraft;
import com.iantapply.wynncraft.Wynncraft;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerJoinEvent implements Listener {

    @EventHandler
    public void onPlayerJoin(org.bukkit.event.player.PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Wynncraft.getInstance().getPlayerCore().initializePlayer(player);
    }
}
