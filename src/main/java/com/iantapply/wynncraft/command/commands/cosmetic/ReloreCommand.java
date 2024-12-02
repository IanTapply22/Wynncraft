package com.iantapply.wynncraft.command.commands.cosmetic;

import com.iantapply.wynncraft.command.WynncraftCommand;
import org.bukkit.command.CommandSender;

public class ReloreCommand extends WynncraftCommand {
    @Override
    public String name() {
        return "relore";
    }

    @Override
    public String syntax() {
        return "relore <lore>";
    }

    @Override
    public String description() {
        return "Adds lore to a crafted item. (Only the creator of a crafted item can relore it.)";
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
        sender.sendMessage("This is the relore command!");
    }
}
