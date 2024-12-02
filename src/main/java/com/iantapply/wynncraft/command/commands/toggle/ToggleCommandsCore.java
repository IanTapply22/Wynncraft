package com.iantapply.wynncraft.command.commands.toggle;

import com.iantapply.wynncraft.Wynncraft;

public class ToggleCommandsCore {

    /**
     * Stages all toggle related commands in the current instance of the command manager
     */
    public void initialize() {
        Wynncraft.getInstance().getCommandCore().stageCommand(new ToggleCommand());
    }
}
