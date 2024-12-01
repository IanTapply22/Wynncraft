package com.iantapply.wynncraft.command.commands.game;

import com.iantapply.wynncraft.command.WynncraftCommand;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;

public class TradeCommand extends WynncraftCommand {
    @Override
    public String name() {
        return "trade";
    }

    @Override
    public String syntax() {
        return "trade <player>";
    }

    @Override
    public ArrayList<String> aliases() {
        ArrayList<String> aliases = new ArrayList<>();
        aliases.add("share");

        return aliases;
    }

    @Override
    public String description() {
        return "Sends a trade request to the provided player.";
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
        sender.sendMessage("This is the trade command!");
    }
}
