package com.iantapply.wynncraft.command.commands.administrator;

import com.iantapply.wynncraft.Wynncraft;
import com.iantapply.wynncraft.command.WynncraftCommand;
import com.iantapply.wynncraft.gui.menu.CharacterSelector;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class OpenGUICommand extends WynncraftCommand {
    @Override
    public String name() {
        return "opengui";
    }

    @Override
    public String syntax() {
        return "opengui";
    }

    @Override
    public String description() {
        return "Opens a specified GUI";
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
        Wynncraft.getInstance().getGuiCore().openGUI(new CharacterSelector(), player);
    }
}
