package com.iantapply.wynncraft.command.commands.cosmetic;

import com.iantapply.wynncraft.command.WynncraftCommand;
import org.bukkit.command.CommandSender;

public class RenameItemCommand extends WynncraftCommand {
    @Override
    public String name() {
        return "renameitem";
    }

    @Override
    public String syntax() {
        return "renameitem <name>";
    }

    @Override
    public String description() {
        return "Renames a crafted item. (Only the creator of a crafted item can rename it.)";
    }

    @Override
    public int minArgs() {
        return 1;
    }

    @Override
    public int maxArgs() {
        return 1;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        sender.sendMessage("This is the renameitem command!");
    }
}
