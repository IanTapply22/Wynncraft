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

    public boolean isReceivingSong(Player player) {
        return ((this.playingSongs.get(player.getName()) != null) && (!this.playingSongs.get(player.getName()).isEmpty()));
    }

    public void stopPlaying(Player player) {
        if (this.playingSongs.get(player.getName()) == null) {
            return;
        }
        for (NBSSongPlayer songPlayer : this.playingSongs.get(player.getName())) {
            songPlayer.removePlayer(player);
        }
    }

    public void setPlayerVolume(Player player, byte volume) {
        this.playerVolume.put(player.getName(), volume);
    }

    public byte getPlayerVolume(Player player) {
        Byte volumeByte = this.playerVolume.get(player.getName());
        if (volumeByte == null) {
            volumeByte = 100;
            this.playerVolume.put(player.getName(), volumeByte);
        }
        return volumeByte;
    }
}
