package com.iantapply.wynncraft.command.commands.administrator;

import com.iantapply.wynncraft.Wynncraft;

/**
 * Handles staging all administrator related commands to the command core registry
 */
public class AdministratorCommandsCore {

    /**
     * Stages all administrator related commands in the current instance of the command manager
     */
    public void initialize() {
        Wynncraft.getInstance().getCommandCore().stageCommand(new SetRankCommand());
        Wynncraft.getInstance().getCommandCore().stageCommand(new SetSupportRankCommand());
        Wynncraft.getInstance().getCommandCore().stageCommand(new GiveMeCommand());
        Wynncraft.getInstance().getCommandCore().stageCommand(new OpenGUICommand());
    }
}
