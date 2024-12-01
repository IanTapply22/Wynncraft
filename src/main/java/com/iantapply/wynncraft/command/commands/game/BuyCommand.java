package com.iantapply.wynncraft.command.commands.game;

import com.iantapply.wynncraft.command.WynncraftCommand;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;

public class BuyCommand extends WynncraftCommand {
    /**
     * The name that is appended after the leading '/' character
     *
     * @return The name
     */
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

    /**
     * The syntax of the command to display in the help menu and other places
     * This should be in the format of "command [args]"
     *
     * @return The syntax
     */
    @Override
    public String syntax() {
        return "buy";
    }

    /**
     * The description of the command to display in the help menu and other places
     *
     * @return The description
     */
    @Override
    public String description() {
        return "If you bought gold coins before the old store got changed in September 2016, you can spend them using this command.";
    }

    /**
     * The minimum amount of arguments that are required to execute the command
     *
     * @return The minimum amount of arguments
     */
    @Override
    public int minArgs() {
        return 0;
    }

    /**
     * The maximum amount of arguments that are required to execute the command
     *
     * @return The maximum amount of arguments
     */
    @Override
    public int maxArgs() {
        return 0;
    }

    /**
     * The method that is called when the command is executed
     *
     * @param sender The sender of the command
     * @param args   The arguments of the command
     */
    @Override
    public void execute(CommandSender sender, String[] args) {
        sender.sendMessage("This is the buy command!");
    }
}
