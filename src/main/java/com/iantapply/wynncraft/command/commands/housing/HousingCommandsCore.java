package com.iantapply.wynncraft.command.commands.housing;

import com.iantapply.wynncraft.Wynncraft;

public class HousingCommandsCore {

    /**
     * Stages all housing related commands in the current instance of the command manager
     */
    public void initialize() {
        Wynncraft.getInstance().getCommandCore().stageCommand(new HousingCommand());
    }
}
