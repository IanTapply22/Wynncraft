package com.iantapply.wynncraft.nbs;

import com.iantapply.wynncraft.nbs.players.NBSSongPlayer;
import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

@Getter
public class NBSCore {
    public HashMap<String, ArrayList<NBSSongPlayer>> playingSongs = new HashMap<>();
    public HashMap<String, Byte> playerVolume = new HashMap<>();
    public NBSCore instance;

    public NBSCore() {
        this.instance = this;
    }

    /**
     * Checks if a player is currently receiving a played song
     * @param player The player to check if a song is playing for
     * @return A boolean representing if a song is currently playing for the player
     */
    public boolean isReceivingSong(Player player) {
        return ((this.playingSongs.get(player.getName()) != null) && (!this.playingSongs.get(player.getName()).isEmpty()));
    }

    /**
     * Stops playing a song for the specified player while still maintaining an NBSSongPlayer instance
     * @param player The player to stop playing the song for
     */
    public void stopPlaying(Player player) {
        if (this.playingSongs.get(player.getName()) == null) {
            return;
        }
        for (NBSSongPlayer songPlayer : this.playingSongs.get(player.getName())) {
            songPlayer.removePlayer(player);
        }
    }

    /**
     * Sets the volume of a specified player
     * @param player The player to set the volume of
     * @param volume The volume amount as a byte
     */
    public void setPlayerVolume(Player player, byte volume) {
        this.playerVolume.put(player.getName(), volume);
    }

    /**
     * Gets the volume of the currently playing song for the specified player
     * @param player The player to get the volume for
     * @return A byte representing the currently set volume
     */
    public byte getPlayerVolume(Player player) {
        return this.playerVolume.computeIfAbsent(player.getName(), k -> (byte) 100);
    }
}
