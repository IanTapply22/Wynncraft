package com.iantapply.wynncraft.command.commands.ignore;

import com.iantapply.wynncraft.Wynncraft;

public class IgnoreCommandsCore {

    /**
     * Stages all ignore related commands in the current instance of the command manager
     */
    public void initialize() {
        Wynncraft.getInstance().getCommandCore().stageCommand(new IgnoreCommand());
    }
}
