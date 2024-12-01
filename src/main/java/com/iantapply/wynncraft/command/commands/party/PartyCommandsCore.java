package com.iantapply.wynncraft.command.commands.party;

import com.iantapply.wynncraft.Wynncraft;

public class PartyCommandsCore {

    /**
     * Stages all party related commands in the current instance of the command manager
     */
    public void initialize() {
        Wynncraft.getInstance().getCommandCore().stageCommand(new PartyCommand());
    }
}
