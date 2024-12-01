package com.iantapply.wynncraft.command.commands.friend;

import com.iantapply.wynncraft.Wynncraft;

public class FriendCommandsCore {

    /**
     * Stages all friend related commands in the current instance of the command manager
     */
    public void initialize() {
        Wynncraft.getInstance().getCommandCore().stageCommand(new FriendCommand());
    }
}
