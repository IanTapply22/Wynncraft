package com.iantapply.wynncraft.gui;

import com.iantapply.wynncraft.logger.Logger;
import com.iantapply.wynncraft.logger.LoggingLevel;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A class with utilities to manage the core functionality regarding
 * GUIs registered and used on the Wynncraft server.
 */
@Getter @Setter
public class WynncraftGUICore {
    private JavaPlugin plugin;

    /**
     * Creates a new instance of the GUI core
     */
    public WynncraftGUICore(JavaPlugin plugin) {
        if (plugin == null) {
            Logger.log(LoggingLevel.ERROR, "The Wynncraft plugin cannot be null. Please provide a valid instance.");
        }

        this.plugin = plugin;
    }

    /**
     * Opens a GUI to the specified player
     * @param gui The GUI to open
     */
    public void openGUI(WynncraftGUI gui, Player player) {
        // Open the GUI and start a task to update the GUI every 20 ticks
        gui.open(player);
        gui.startUpdater(this.getPlugin());

        // Trigger the event specified in the GUI if it exists
        if (gui.triggerEvent() != null) {
            gui.triggerEvent().callEvent();
        }
    }

    /**
     * Closes a GUI for the specified player
     * @param gui The GUI to close
     */
    public void closeGUI(WynncraftGUI gui, Player player) {
        // Close the GUI and stop the task to update the GUI
        gui.onClose(player);
        player.closeInventory();
        gui.stopUpdater();
    }
}
