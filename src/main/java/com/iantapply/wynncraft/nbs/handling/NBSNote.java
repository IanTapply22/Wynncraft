package com.iantapply.wynncraft.nbs.handling;

import lombok.Getter;
import lombok.Setter;

@Getter
public class NBSNote {
    @Setter
    private byte instrument;
    @Setter
    private byte key;
    private byte velocity;
    @Setter
    private int panning;
    @Setter
    private short pitch;

    public NBSNote(byte instrument, byte key) {
        this(instrument, key, (byte) 100, (byte) 100, (short) 0);
    }

    public NBSNote(byte instrument, byte key, byte velocity, int panning, short pitch) {
        this.instrument = instrument;
        this.key = key;
        this.velocity = velocity;
        this.panning = panning;
        this.pitch = pitch;
    }

    /**
     * Sets note velocity (volume)
     * @param velocity number from 0 to 100
     */
    public void setVelocity(byte velocity) {
        if (velocity < 0) velocity = 0;
        if (velocity > 100) velocity = 100;

        this.velocity = velocity;
    }
}
