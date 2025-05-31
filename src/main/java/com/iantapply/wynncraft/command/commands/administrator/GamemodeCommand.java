package com.iantapply.wynncraft.command.commands.administrator;

import com.iantapply.wynncraft.command.WynncraftCommand;
import com.iantapply.wynncraft.rank.Rank;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class GamemodeCommand extends WynncraftCommand {

    @Override
    public String name() {
        return "gm";
    }

    @Override
    public String syntax() {
        return "gm <1|2|3>";
    }

    @Override
    public String description() {
        return "Sets the gamemode of the current player";
    }

    @Override
    public boolean isPlayerOnly() {
        return true;
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
        int gamemode = Integer.parseInt(args[0]);

        switch (gamemode) {
            case 1:
                player.setGameMode(org.bukkit.GameMode.CREATIVE);
                player.sendMessage("Gamemode set to creative");
                break;
            case 2:
                player.setGameMode(org.bukkit.GameMode.SURVIVAL);
                player.sendMessage("Gamemode set to survival");
                break;
            case 3:
                player.setGameMode(org.bukkit.GameMode.ADVENTURE);
                player.sendMessage("Gamemode set to adventure");
                break;
            case 4:
                player.setGameMode(org.bukkit.GameMode.SPECTATOR);
                player.sendMessage("Gamemode set to spectator");
                break;
            default:
                player.sendMessage("Invalid gamemode provided");
        }
    }
}
