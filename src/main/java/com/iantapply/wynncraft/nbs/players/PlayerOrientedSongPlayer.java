package com.iantapply.wynncraft.nbs.players;


import com.iantapply.wynncraft.nbs.*;
import com.iantapply.wynncraft.nbs.enums.NotePitch;
import com.iantapply.wynncraft.nbs.handling.NBSLayer;
import com.iantapply.wynncraft.nbs.handling.NBSNote;
import com.iantapply.wynncraft.nbs.handling.NBSSong;
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
    public void playTick(Player p, int tick) {

        byte playerVolume = this.core.getPlayerVolume(p);

        for (NBSLayer l : song.getLayerHashMap().values()) {
            NBSNote note = l.getNote(tick);
            if (note == null) {
                continue;
            }
            p.playSound(player,
                    Instrument.getInstrument(note.getInstrument()),
                    soundCategory,
                    (l.getVolume() * (int) volume * (int) playerVolume) / 1000000f,
                    NotePitch.getPitch(note.getKey() - 33));
        }
    }
}
