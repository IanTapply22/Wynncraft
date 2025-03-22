package com.iantapply.wynncraft.pack;

import com.iantapply.wynncraft.Wynncraft;
import com.iantapply.wynncraft.logger.Logger;
import com.iantapply.wynncraft.logger.LoggingLevel;
import com.sun.net.httpserver.HttpServer;
import lombok.Getter;

import java.net.InetSocketAddress;

@Getter
public class ResourcePackCore {
    private HttpServer httpServer;
    private final int port;

    public ResourcePackCore(int port) {
        this.port = port;
    }

    /**
     * Initializes the resource pack core instance creates the CDN server instance and contexts
     */
    public void initialize() {
        if (!Wynncraft.getInstance().getConfig().getBoolean("WYNNCRAFT_CDN_ENABLE")) {
            Logger.log(LoggingLevel.INFO, "Wynncraft CDN is set to disabled. Resource pack downloading will default to fallback URL and won't have a fallback.");
            return;
        }

        Logger.log(LoggingLevel.INFO, "Initializing the resource pack core instance...");
        this.createServerInstance();
        if (this.httpServer != null) {
            this.createServerContexts();
        } else {
            Logger.log(LoggingLevel.ERROR, "HTTP server instance is null, cannot create server contexts.");
        }
        this.httpServer.start();
        Logger.log(LoggingLevel.INFO, "Resource pack core instance started");
    }

    /**
     * Starts the base server instance on the specified port without contexts
     */
    public void createServerInstance() {
        try {
            this.httpServer = HttpServer.create(new InetSocketAddress(this.port), 0);
            Logger.log(LoggingLevel.INFO, "Created CDN server instance on port " + this.port);
        } catch (Exception e) {
            Logger.log(LoggingLevel.ERROR, "Failed to create CDN server instance on port " + this.port + ": " + e.getMessage());
        }
    }

    /**
     * Creates the server contexts for the resource pack CDN
     */
    public void createServerContexts() {
        if (this.httpServer != null) {
            this.httpServer.createContext(Wynncraft.getInstance().getConfigurationCore().getString("WYNNCRAFT_RESOURCE_PACK_CONTEXT"), new ResourcePackHandler());
            this.httpServer.setExecutor(null);
            Logger.log(LoggingLevel.INFO, "Created resource pack CDN contexts");
        } else {
            Logger.log(LoggingLevel.ERROR, "HTTP server instance is null, cannot create server contexts.");
        }
    }

    /**
     * Closes the HTTP server CDN instance
     */
    public void closeServerInstance() {
        if (this.httpServer == null) return;
        this.httpServer.stop(0);
    }
}
