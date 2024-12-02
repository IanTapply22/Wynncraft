package com.iantapply.wynncraft.command.commands.nbs;

import com.iantapply.wynncraft.Wynncraft;
import com.iantapply.wynncraft.nbs.players.PlayerOrientedSongPlayer;

public class NbsCore {
    public static PlayerOrientedSongPlayer player;

    public void initialize() {
        Wynncraft.getInstance().getCommandCore().stageCommand(new NbsCommand());
    }
}
