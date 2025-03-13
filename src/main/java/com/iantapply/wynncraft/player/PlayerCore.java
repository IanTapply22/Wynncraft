package com.iantapply.wynncraft.player;

import com.iantapply.wynncraft.Wynncraft;
import com.iantapply.wynncraft.configuration.ConfigurationCore;
import com.iantapply.wynncraft.database.model.PlayerModel;
import com.iantapply.wynncraft.database.pgsql.PGSQLDatabaseHelpers;
import com.iantapply.wynncraft.logger.Logger;
import com.iantapply.wynncraft.logger.LoggingLevel;
import com.iantapply.wynncraft.rank.Rank;
import com.iantapply.wynncraft.rank.SupportRank;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

/**
 * A core class to manage all Wynncraft players that are active on the server
 * <p>
 * Used to reduce the amount of calls to the database to speed up time, to cache, and
 * to increase response times of player data.
 */
@Getter @Setter
public class PlayerCore {
    private final Wynncraft plugin;
    private final ArrayList<WynncraftPlayer> players;
    private final Collection<? extends Player> onlinePlayers;

    public PlayerCore(Wynncraft plugin, Collection<? extends Player> onlinePlayers) {
        this.plugin = plugin;
        this.players = new ArrayList<>();
        this.onlinePlayers = onlinePlayers;
    }

    /**
     * Loops through all online players and initializes them
     */
    public void initialize() {
        for (Player player : this.onlinePlayers) {
            this.initializePlayer(player);
        }
    }

    /**
     * Initializes the player specified and registers them within the player core
     * @param player The player to initialize and instantiate
     */
    public void initializePlayer(Player player) {
        PlayerModel playerModel = new PlayerModel(player.getUniqueId());
        ConfigurationCore configurationCore = Wynncraft.getInstance().getConfigurationCore();
        try {
            // Sets the player up if they aren't in the database
            if (PGSQLDatabaseHelpers.selectRow(playerModel.database().connect(true), playerModel.table(), "uuid = CAST(? AS UUID)", player.getUniqueId()) == null) {
                playerModel.setRank(Rank.PLAYER);

                playerModel.setVeteran(false); // ignore: set, but means they had VIP before update
                playerModel.setFirstJoin(new Timestamp(System.currentTimeMillis()));
                playerModel.setPublicProfile(true);
            }

            playerModel.setUsername(player.getName());
            playerModel.setOnline(true);
            playerModel.setServer(configurationCore.getString("WYNNCRAFT_SERVER_INSTANCE_ID"));
            playerModel.setRank(Rank.getRankById((int) playerModel.getModelValue("rank")));

            Object supportRank = playerModel.getModelValue("supportRank");
            if (supportRank != null) playerModel.setSupportRank(SupportRank.getRankById((int) supportRank));
            playerModel.setLastJoin(new Timestamp(System.currentTimeMillis()));

            playerModel.populate();
            playerModel.database().disconnect();

            WynncraftPlayer wynncraftPlayer = new WynncraftPlayer(player, playerModel);
            wynncraftPlayer.initialize();
            Wynncraft.getInstance().getPlayerCore().addPlayer(wynncraftPlayer);
        } catch (Exception e) {
            player.sendMessage("Could not execute query to database! Please see the console logs.");
            Logger.log(LoggingLevel.ERROR, String.format("Could not execute SQL query with error: %s", e));
        }
    }

    /**
     * Gets a WynncraftPlayer object instance of the specified players UUID. Must be
     * registered within the player core.
     * @param uuid The UUID of the player to search for
     * @return An instance of WynncraftPlayer if found, otherwise null
     */
    public WynncraftPlayer getPlayer(UUID uuid) {
        for (WynncraftPlayer player : this.players) {
            if (player.getPlayer().getUniqueId().toString().equals(uuid.toString())) {
                return player;
            }
        }

        Logger.log(LoggingLevel.WARNING, String.format("Player with UUID %s is not in the player core list", uuid));
        return null;
    }

    /**
     * Adds a WynncraftPlayer instance to the player core registry
     * @param player The WynncraftPlayer instance to add
     */
    public void addPlayer(WynncraftPlayer player) {
        if (this.players.contains(player)) {
            Logger.log(LoggingLevel.WARNING, String.format("Player %s is already added to the player core list", player.getPlayer().getUniqueId()));
        }

        this.players.add(player);
    }

    /**
     * Removes a player from the player core registry
     * @param uuid The UUID of the player to remove
     */
    public void removePlayer(UUID uuid) {
        WynncraftPlayer player = this.getPlayer(uuid);
        if (player == null) {
            Logger.log(LoggingLevel.WARNING, String.format("Player with UUID %s is not in the player core list", uuid));
            return;
        }

        this.players.remove(player);
    }

    public void updatePlayer(UUID uuid, PlayerModel playerModel) {
        WynncraftPlayer player = this.getPlayer(uuid);
        if (player == null) {
            Logger.log(LoggingLevel.WARNING, String.format("Player with UUID %s is not in the player core list", uuid));
            return;
        }

        player.setPlayerModel(playerModel);
    }
}
