package com.iantapply.wynncraft.command.commands.game;

import com.iantapply.wynncraft.command.WynncraftCommand;
import org.bukkit.command.CommandSender;

public class ClaimIngredientBombCommand extends WynncraftCommand {
    @Override
    public String name() {
        return "claimingredientbomb";
    }

    @Override
    public String syntax() {
        return "claimingredientbomb";
    }

    @Override
    public String description() {
        return "Claims a thrown ingredient bomb.";
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
        sender.sendMessage("This is the claimingredientbomb command!");
    }
}
