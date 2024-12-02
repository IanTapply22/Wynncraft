package com.iantapply.wynncraft.command.commands.game;

import com.iantapply.wynncraft.command.WynncraftCommand;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;

public class BuyCommand extends WynncraftCommand {
    @Override
    public String name() {
        return "buy";
    }

    @Override
    public ArrayList<String> aliases() {
        ArrayList<String> aliases = new ArrayList<>();
        aliases.add("cash");
        aliases.add("store");
        aliases.add("gold");
        aliases.add("goldcoins");
        aliases.add("gc");
        aliases.add("shop");

        return aliases;
    }

    @Override
    public String syntax() {
        return "buy";
    }

    @Override
    public String description() {
        return "If you bought gold coins before the old store got changed in September 2016, you can spend them using this command.";
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
        sender.sendMessage("This is the buy command!");
    }
}
