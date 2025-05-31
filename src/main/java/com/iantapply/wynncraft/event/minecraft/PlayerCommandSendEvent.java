package com.iantapply.wynncraft.event.minecraft;

import com.iantapply.wynncraft.Wynncraft;
import com.iantapply.wynncraft.player.WynncraftPlayer;
import com.iantapply.wynncraft.rank.Rank;
import com.iantapply.wynncraft.rank.SupportRank;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerCommandSendEvent implements Listener {

    @EventHandler
    public void onPlayerCommandSend(org.bukkit.event.player.PlayerCommandSendEvent event) {
        Player player = event.getPlayer();
        WynncraftPlayer wynncraftPlayer = Wynncraft.getInstance().getPlayerCore().getPlayer(player.getUniqueId());
        Rank rank = wynncraftPlayer.getPlayerModel().getRank();
        SupportRank supportRank = wynncraftPlayer.getPlayerModel().getSupportRank();

        // Remove all minecraft commands if the player is not an administrator or owner
        if (rank != Rank.ADMINISTRATOR && rank != Rank.OWNER) {
            event.getCommands().removeIf(command -> command.startsWith("minecraft:") || command.startsWith("bukkit:"));
        }

        // Remove commands that are not allowed for the player's rank
        event.getCommands().removeIf(command -> !Wynncraft.getInstance().getCommandCore().isCommandAllowed(command, rank, supportRank));
    }
}
