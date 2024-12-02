package com.iantapply.wynncraft.command.commands.cosmetic;

import com.iantapply.wynncraft.Wynncraft;
import org.bukkit.craftbukkit.block.impl.CraftCommand;

public class CosmeticCommandsCore {

    /**
     * Stages all cosmetic related commands in the current instance of the command manager
     */
    public void initialize() {
        Wynncraft.getInstance().getCommandCore().stageCommand(new ChangeTagCommand());
        Wynncraft.getInstance().getCommandCore().stageCommand(new CraftCommand());
        Wynncraft.getInstance().getCommandCore().stageCommand(new ParticlesCommand());
        Wynncraft.getInstance().getCommandCore().stageCommand(new PetCommand());
        Wynncraft.getInstance().getCommandCore().stageCommand(new ReloreCommand());
        Wynncraft.getInstance().getCommandCore().stageCommand(new RenameItemCommand());
        Wynncraft.getInstance().getCommandCore().stageCommand(new RenamePetCommand());
        Wynncraft.getInstance().getCommandCore().stageCommand(new UseCommand());
    }
}
