package com.iantapply.wynncraft.nbs.players;

import com.iantapply.wynncraft.Wynncraft;
import com.iantapply.wynncraft.event.wynncraft.nbs.NBSSongDestroyedEvent;
import com.iantapply.wynncraft.event.wynncraft.nbs.NBSSongEndEvent;
import com.iantapply.wynncraft.event.wynncraft.nbs.NBSSongStopEvent;
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

    /**
     * Calculates the fade of a song (the volume fade in and fade out)
     */
    protected void calculateFade() {
        if (this.fadeDone == this.fadeDuration) return;

        double targetVolume = Interpolator.interpLinear(new double[]{ 0, this.fadeStart, this.fadeDuration, this.fadeTarget }, this.fadeDone);
        setVolume((byte) targetVolume);
        this.fadeDone++;
    }

    /**
     * Creates a thread to run song ticks on
     * <p>
     * This is separate from the main thread and Thread.sleep does not affect the main thread.
     */
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
                            Bukkit.getScheduler().runTask(Wynncraft.getInstance(), () -> {
                                NBSSongEndEvent songEndEvent = new NBSSongEndEvent();
                                songEndEvent.callEvent();
                            });
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

    /**
     * Gets the player list currently listening to the NBS song
     * @return A List[String] object that contains the list of player names
     */
    public List<String> getPlayerList() {
        return Collections.unmodifiableList(this.playerList);
    }

    /**
     * Adds a player to listen to the current song
     * @param player The Player object of the player
     */
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

    /**
     * Sets if the song should loop or not
     * @param loop A boolean representing the loop status of the song
     */
    public void setLoop(boolean loop) {
        synchronized (this)
        {
            this.loop = loop;
        }
    }

    /**
     * Checks if the song is currently in loop mode
     * @return A boolean representing the loop status of the song
     */
    public boolean isLoop() {
        synchronized (this)
        {
            return this.loop;
        }
    }

    /**
     * Checks if the song will auto-destroy after playing the entirety of an NBS song
     * @return A boolean representing if the song will auto-destroy after playing
     */
    public boolean getAutoDestroy() {
        synchronized (this) {
            return this.autoDestroy;
        }
    }

    /**
     * Sets if the song should auto-destroy after playing the entirety of an NBS song
     * @param value A boolean representing if the song will auto-destroy after playing
     */
    public void setAutoDestroy(boolean value) {
        synchronized (this) {
            this.autoDestroy = value;
        }
    }

    /**
     * The code that is run when a tick is played for the NBS song
     * @param player The player to run the tick on
     * @param tick The tick in the song to play
     */
    public abstract void playTick(Player player, int tick);

    /**
     * Destroys the currently existing NBSSongPlayer instance and removes the thread
     */
    public void destroy() {
        synchronized (this) {
            NBSSongDestroyedEvent songDestroyingEvent = new NBSSongDestroyedEvent();
            songDestroyingEvent.callEvent();

            if (songDestroyingEvent.isCancelled()) {
                return;
            }
            this.setDestroyed(true);
            this.setPlaying(false);
            this.setTick((short) -1);
        }
    }

    /**
     * Sets if the song is currently playing. This must be executed upon initial creation of NBSSongPlayer object
     * @param playing A boolean representing if the song should be playing
     */
    public void setPlaying(boolean playing) {
        this.playing = playing;
        if (!playing) {
            NBSSongStopEvent songStopEvent = new NBSSongStopEvent();
            songStopEvent.callEvent();
        }
    }

    /**
     * Removes a player from the listening player list. This will not run any ticks on the player after removal
     * @param player The Player object to run it on
     */
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
                NBSSongEndEvent songEndEvent = new NBSSongEndEvent();
                songEndEvent.callEvent();
                destroy();
            }
        }
    }
}
