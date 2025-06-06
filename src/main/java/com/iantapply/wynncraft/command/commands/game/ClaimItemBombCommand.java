package com.iantapply.wynncraft.command.commands.game;

import com.iantapply.wynncraft.command.WynncraftCommand;
import org.bukkit.command.CommandSender;

/**
 * A command used to claim a thrown item bomb
 */
public class ClaimItemBombCommand extends WynncraftCommand {
    @Override
    public String name() {
        return "claimitembomb";
    }

    @Override
    public String syntax() {
        return "claimitembomb";
    }

    @Override
    public String description() {
        return "Claims a thrown item bomb.";
    }

    @Override
    public int minArgs() {
        return 0;
    }

    @Override
    public int maxArgs() {
        return 0;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        sender.sendMessage("This is the claimitembomb command!");
    }
}
