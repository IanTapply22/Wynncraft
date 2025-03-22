package com.iantapply.wynncraft.world;

import com.iantapply.wynncraft.Wynncraft;
import com.iantapply.wynncraft.world.limbo.LimboWorldHelpers;
import com.iantapply.wynncraft.world.main.MainWorldHelpers;
import lombok.Getter;

@Getter
public class WorldCore {
    private final Wynncraft plugin = Wynncraft.getInstance();

    public WorldCore() {}

    /**
     * Initializes the limbo and wynncraft main worlds if they don't exist
     */
    public void initialize() {
        LimboWorldHelpers.initialize();
        MainWorldHelpers.initialize();
    }
}
