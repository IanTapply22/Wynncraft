package com.iantapply.wynncraft.command.commands.cosmetic;

import com.iantapply.wynncraft.command.WynncraftCommand;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;

public class ParticlesCommand extends WynncraftCommand {
    @Override
    public String name() {
        return "particles";
    }

    @Override
    public String syntax() {
        return "particles <off|low|medium|high|veryhigh|highest|(particles per tick)>";
    }

    @Override
    public String description() {
        return "Limits the time (in-game ticks) between each game particle. (20 is equivalent of 1 particle per second)";
    }

    @Override
    public ArrayList<String> aliases() {
        ArrayList<String> aliases = new ArrayList<>();
        aliases.add("pq");

        return aliases;
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
        sender.sendMessage("This is the particles command!");
    }
}
