package com.iantapply.wynncraft.command.commands.guild;

import com.iantapply.wynncraft.Wynncraft;

public class GuildCommandsCore {

    /**
     * Stages all guild related commands in the current instance of the command manager
     */
    public void initialize() {
        Wynncraft.getInstance().getCommandCore().stageCommand(new GuildCommand());
    }
}
