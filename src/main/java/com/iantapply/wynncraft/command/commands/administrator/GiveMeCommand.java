package com.iantapply.wynncraft.command.commands.administrator;

import com.iantapply.wynncraft.command.WynncraftCommand;
import com.iantapply.wynncraft.item.WynncraftNBTItem;
import com.iantapply.wynncraft.item.items.weapon.Sacrilege;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GiveMeCommand extends WynncraftCommand {
    @Override
    public String name() {
        return "giveme";
    }

    @Override
    public String syntax() {
        return "giveme";
    }

    @Override
    public String description() {
        return "Gives you a set command";
    }

    @Override
    public boolean isPlayerOnly() {
        return true;
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
    public boolean isDevelopment() {
        return true;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        WynncraftNBTItem item = new Sacrilege().build();

        player.getInventory().addItem(item.getItem());

        player.sendMessage("You have been given the configured item");
    }
}
