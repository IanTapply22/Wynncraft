package com.iantapply.wynncraft.command.commands.cosmetic;

import com.iantapply.wynncraft.command.WynncraftCommand;
import org.bukkit.command.CommandSender;

/**
 * A command used to change the rank tag that appears in chat
 */
public class ChangeTagCommand extends WynncraftCommand {
    @Override
    public String name() {
        return "changetag";
    }

    @Override
    public String syntax() {
        return "changetag <VIP|VIP+|HERO|CHAMPION|RESET>";
    }

    @Override
    public String description() {
        return "Changes your rank tag.";
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
        sender.sendMessage("This is the changetag command!");
    }
}
