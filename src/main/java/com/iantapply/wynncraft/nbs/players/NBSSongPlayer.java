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
        createThread();
    }

    protected void calculateFade() {
        if (fadeDone == fadeDuration) return;

        double targetVolume = Interpolator.interpLinear(new double[]{0, fadeStart, fadeDuration, fadeTarget}, fadeDone);
        setVolume((byte) targetVolume);
        fadeDone++;
    }

    protected void createThread() {
        playerThread = new Thread(() -> {
            while (!destroyed) {
                long startTime = System.currentTimeMillis();
                synchronized (NBSSongPlayer.this) {
                    if (playing) {
                        calculateFade();
                        tick++;
                        if (tick > song.getLength()) {
                            if(loop){
                                tick = 0;
                                continue;
                            }
                            playing = false;
                            tick = -1;
                            // TODO: Send custom emit song end event
                            if (autoDestroy) {
                                destroy();
                                return;
                            }
                        }
                        for (String s : playerList) {
                            Player p = Bukkit.getPlayerExact(s);
                            if (p == null) {
                                // offline...
                                continue;
                            }
                            playTick(p, tick);
                        }
                    }
                }
                long duration = System.currentTimeMillis() - startTime;
                float delayMillis = song.getDelay() * 50;
                if (duration < delayMillis) {
                    try {
                        Thread.sleep((long) (delayMillis - duration));
                    } catch (InterruptedException e) {
                        // do nothing
                    }
                }
            }
        });
        playerThread.setPriority(Thread.MAX_PRIORITY);
        playerThread.start();
    }

    public List<String> getPlayerList() {
        return Collections.unmodifiableList(playerList);
    }

    public void addPlayer(Player p) {
        synchronized (this) {
            if (!playerList.contains(p.getName())) {
                playerList.add(p.getName());
                ArrayList<NBSSongPlayer> songs = this.core.getPlayingSongs()
                        .get(p.getName());
                if (songs == null) {
                    songs = new ArrayList<>();
                }
                songs.add(this);
                this.core.getPlayingSongs().put(p.getName(), songs);
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
            return autoDestroy;
        }
    }

    public void setAutoDestroy(boolean value) {
        synchronized (this) {
            autoDestroy = value;
        }
    }

    public abstract void playTick(Player p, int tick);

    public void destroy() {
        synchronized (this) {

            // TODO: Call song destroy custom emit event and cancel thread via thread ID
            destroyed = true;
            playing = false;
            setTick((short) -1);
        }
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
        if (!playing) {
            // TODO: Call song stopped event
        }
    }

    public void removePlayer(Player p) {
        synchronized (this) {
            playerList.remove(p.getName());
            if (this.core.getPlayingSongs().get(p.getName()) == null) {
                return;
            }
            ArrayList<NBSSongPlayer> songs = new ArrayList<>(
                    this.core.getPlayingSongs().get(p.getName()));
            songs.remove(this);
            this.core.getPlayingSongs().put(p.getName(), songs);
            if (playerList.isEmpty() && autoDestroy) {
                // TODO: Call song end event emit
                destroy();
            }
        }
    }
}
