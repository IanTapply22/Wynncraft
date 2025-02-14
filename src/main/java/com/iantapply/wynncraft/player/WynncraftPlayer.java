package com.iantapply.wynncraft.player;

import com.iantapply.wynncraft.database.model.PlayerModel;
import com.iantapply.wynncraft.logger.Logger;
import com.iantapply.wynncraft.logger.LoggingLevel;
import lombok.Getter;
import lombok.Setter;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

/**
 * A class to interact with a player based on their stored Wynncraft player data model
 */
@Getter @Setter
public class WynncraftPlayer {
    private PlayerModel playerModel;
    private final Player player;

    public WynncraftPlayer(Player player, PlayerModel playerModel) {
        this.player = player;
        this.playerModel = playerModel;
    }

    public void initialize() {
        if (this.player == null) {
            Logger.log(LoggingLevel.ERROR, "Player is null, cannot initialize Wynncraft player instance");
            return;
        }

        String name = (this.playerModel.getNickname() != null) ? this.playerModel.getNickname() : this.playerModel.getUsername();

        this.player.displayName(Component.text("[" + this.playerModel.getServer() + "] " +  name)); // Changes chat + tab list name
    }
}
