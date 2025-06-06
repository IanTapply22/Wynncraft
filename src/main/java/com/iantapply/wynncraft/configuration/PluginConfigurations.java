package com.iantapply.wynncraft.configuration;

public class PluginConfigurations {
    public static final String NAME = "Wynncraft";
    public static final String VERSION = "1.3.1";
    public static final String DEVELOPER_CREDITS = "IanTapply22";
    public static final String DESCRIPTION = "A Wynncraft recreation";
    public static final String MAIN_CONFIG_FILE = "config.yml";
    public static final int BSTATS_PLUGIN_ID = 24674;

    /**
     * Checks if the server and plugin are running in PaperMC mode
     * @return A boolean to determine if the server is running PaperMC
     */
    public static boolean isRunningPaper() {
        boolean isPaper = false;
        try {
            // Any other works, just the shortest I could find.
            Class.forName("com.destroystokyo.paper.ParticleBuilder");
            isPaper = true;
        } catch (ClassNotFoundException ignored) {}

        return isPaper;
    }
}
