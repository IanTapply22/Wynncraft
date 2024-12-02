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

    public boolean isReceivingSong(Player p) {
        return ((this.playingSongs.get(p.getName()) != null) && (!this.playingSongs.get(p.getName()).isEmpty()));
    }

    public void stopPlaying(Player p) {
        if (this.playingSongs.get(p.getName()) == null) {
            return;
        }
        for (NBSSongPlayer s : this.playingSongs.get(p.getName())) {
            s.removePlayer(p);
        }
    }

    public void setPlayerVolume(Player p, byte volume) {
        this.playerVolume.put(p.getName(), volume);
    }

    public byte getPlayerVolume(Player p) {
        Byte b = this.playerVolume.get(p.getName());
        if (b == null) {
            b = 100;
            playerVolume.put(p.getName(), b);
        }
        return b;
    }
}
