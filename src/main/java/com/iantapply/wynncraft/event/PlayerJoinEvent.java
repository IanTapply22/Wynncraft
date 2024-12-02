package com.iantapply.wynncraft.event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerJoinEvent implements Listener {

    @EventHandler
    public void onPlayerJoin(org.bukkit.event.player.PlayerJoinEvent event) {
        // TODO: Create a model that will store the players data upon joining, that including the stats from DB and NBS player instance
    }
}
