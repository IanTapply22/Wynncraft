package com.iantapply.wynncraft.nbs.players;

import com.iantapply.wynncraft.nbs.NBSCore;
import com.iantapply.wynncraft.nbs.handling.NBSSong;
import com.iantapply.wynncraft.nbs.utils.Interpolator;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// TODO: Make custom events for song playing events
@Getter @Setter
public abstract class NBSSongPlayer {
    protected NBSSong song;
    protected NBSCore core;
    protected boolean playing = false;
    protected short tick = -1;
    protected ArrayList<String> playerList = new ArrayList<>();
    protected boolean loop;
    protected boolean autoDestroy = false;
    protected boolean destroyed = false;
    protected Thread playerThread;
    protected byte fadeTarget = 100;
    protected byte volume = 100;
    protected byte fadeStart = volume;
    protected int fadeDuration = 60;
    protected int fadeDone = 0;

    public NBSSongPlayer(NBSCore core, NBSSong song) {
        this.song = song;
        this.core = core;
        this.createThread();
    }

    protected void calculateFade() {
        if (this.fadeDone == this.fadeDuration) return;

        double targetVolume = Interpolator.interpLinear(new double[]{ 0, this.fadeStart, this.fadeDuration, this.fadeTarget }, this.fadeDone);
        setVolume((byte) targetVolume);
        this.fadeDone++;
    }

    protected void createThread() {
        this.playerThread = new Thread(() -> {
            while (!this.destroyed) {
                long startTime = System.currentTimeMillis();
                synchronized (NBSSongPlayer.this) {
                    if (this.playing) {
                        calculateFade();
                        this.tick++;
                        if (this.tick > this.song.getLength()) {
                            if(this.loop){
                                this.tick = 0;
                                continue;
                            }
                            this.playing = false;
                            this.tick = -1;
                            // TODO: Send custom emit song end event
                            if (this.autoDestroy) {
                                destroy();
                                return;
                            }
                        }
                        for (String playerGamertag : this.playerList) {
                            Player player = Bukkit.getPlayerExact(playerGamertag);
                            if (player == null) continue;
                            playTick(player, this.tick);
                        }
                    }
                }
                long duration = System.currentTimeMillis() - startTime;
                float delayMillis = this.song.getDelay() * 50;
                if (duration < delayMillis) {
                    try {
                        Thread.sleep((long) (delayMillis - duration));
                    } catch (InterruptedException ignored) {}
                }
            }
        });
        this.playerThread.setPriority(Thread.MAX_PRIORITY);
        this.playerThread.start();
    }

    public List<String> getPlayerList() {
        return Collections.unmodifiableList(this.playerList);
    }

    public void addPlayer(Player player) {
        synchronized (this) {
            if (!this.getPlayerList().contains(player.getName())) {
                this.playerList.add(player.getName());
                ArrayList<NBSSongPlayer> songs = this.core.getPlayingSongs()
                        .get(player.getName());
                if (songs == null) {
                    songs = new ArrayList<>();
                }
                songs.add(this);
                this.core.getPlayingSongs().put(player.getName(), songs);
            }
        }
    }
    public void setLoop(boolean loop) {
        synchronized (this)
        {
            this.loop = loop;
        }
    }

    public boolean isLoop() {
        synchronized (this)
        {
            return this.loop;
        }
    }
    public boolean getAutoDestroy() {
        synchronized (this) {
            return this.autoDestroy;
        }
    }

    public void setAutoDestroy(boolean value) {
        synchronized (this) {
            this.autoDestroy = value;
        }
    }

    public abstract void playTick(Player p, int tick);

    public void destroy() {
        synchronized (this) {

            // TODO: Call song destroy custom emit event and cancel thread via thread ID
            this.setDestroyed(true);
            this.setPlaying(false);
            this.setTick((short) -1);
        }
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
        if (!playing) {
            // TODO: Call song stopped event
        }
    }

    public void removePlayer(Player player) {
        synchronized (this) {
            this.getPlayerList().remove(player.getName());
            if (this.core.getPlayingSongs().get(player.getName()) == null) {
                return;
            }
            ArrayList<NBSSongPlayer> songs = new ArrayList<>(
                    this.core.getPlayingSongs().get(player.getName()));
            songs.remove(this);
            this.core.getPlayingSongs().put(player.getName(), songs);
            if (this.getPlayerList().isEmpty() && this.getAutoDestroy()) {
                // TODO: Call song end event emit
                destroy();
            }
        }
    }
}
