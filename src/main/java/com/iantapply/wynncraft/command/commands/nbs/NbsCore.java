package com.iantapply.wynncraft.command.commands.nbs;

import com.iantapply.wynncraft.Wynncraft;
import com.iantapply.wynncraft.nbs.players.PlayerOrientedSongPlayer;

public class NbsCore {
    public static PlayerOrientedSongPlayer player;

    /**
     * Stages all NBS related commands in the current instance of the command manager
     */
    public void initialize() {
        Wynncraft.getInstance().getCommandCore().stageCommand(new NbsCommand());
    }
}
