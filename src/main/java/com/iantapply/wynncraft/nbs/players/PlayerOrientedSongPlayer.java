package com.iantapply.wynncraft.nbs.players;

import com.iantapply.wynncraft.nbs.*;
import com.iantapply.wynncraft.nbs.enums.NotePitch;
import com.iantapply.wynncraft.nbs.handling.NBSLayer;
import com.iantapply.wynncraft.nbs.handling.NBSNote;
import com.iantapply.wynncraft.nbs.handling.NBSSong;
import com.iantapply.wynncraft.nbs.instruments.NBSInstrument;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;

@Getter @Setter
public class PlayerOrientedSongPlayer extends NBSSongPlayer {
    private Player player;
    private SoundCategory soundCategory = SoundCategory.RECORDS;

    public PlayerOrientedSongPlayer(NBSCore core, NBSSong song) {
        super(core, song);
    }

    @Override
    public void playTick(Player player, int tick) {
        byte playerVolume = this.core.getPlayerVolume(player);

        for (NBSLayer layer : this.song.getLayerHashMap().values()) {
            NBSNote note = layer.getNote(tick);
            if (note == null) continue;
            player.playSound(this.player,
                    NBSInstrument.getInstrument(note.getInstrument()),
                    this.soundCategory,
                    (layer.getVolume() * (int) this.volume * (int) playerVolume) / 1000000f,
                    NotePitch.getPitch(note.getKey() - 33));
        }
    }
}
