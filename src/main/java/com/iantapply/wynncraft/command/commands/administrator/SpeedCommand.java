package com.iantapply.wynncraft.command.commands.administrator;

import com.iantapply.wynncraft.command.WynncraftCommand;
import com.iantapply.wynncraft.rank.Rank;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class SpeedCommand extends WynncraftCommand {

    @Override
    public String name() {
        return "speed";
    }

    @Override
    public String syntax() {
        return "speed <value>";
    }

    @Override
    public String description() {
        return "Sets the speed of the current player";
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
        int speed = Integer.parseInt(args[0]);

        if (speed < 0 || speed > 100) {
            player.sendMessage("Speed must be between 0 and 100");
            return;
        }

        player.setWalkSpeed(speed / 100f);
    }
}
