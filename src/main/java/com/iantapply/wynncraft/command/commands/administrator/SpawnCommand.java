package com.iantapply.wynncraft.command.commands.administrator;

import com.iantapply.wynncraft.Wynncraft;
import com.iantapply.wynncraft.command.WynncraftCommand;
import com.iantapply.wynncraft.rank.Rank;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class SpawnCommand extends WynncraftCommand {

    @Override
    public String name() {
        return "spawn";
    }

    @Override
    public String syntax() {
        return "spawn";
    }

    @Override
    public String description() {
        return "Teleports the current player to spawn configured in the config file";
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
    public ArrayList<Rank> requiredRanks() {
        ArrayList<Rank> ranks = new ArrayList<>();
        ranks.add(Rank.ADMINISTRATOR);
        return ranks;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        Location spawnLocation = Wynncraft.getInstance().getConfigurationCore().getLocation("WYNNCRAFT_MAIN_COORDINATES");

        if (spawnLocation == null) {
            player.sendMessage("Spawn location is not configured. Please contact an administrator.");
            return;
        }

        player.teleport(spawnLocation);
        player.sendMessage("You have been teleported to spawn");
    }
}
