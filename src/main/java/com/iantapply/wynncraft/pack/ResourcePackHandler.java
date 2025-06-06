package com.iantapply.wynncraft.pack;

import com.iantapply.wynncraft.Wynncraft;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class ResourcePackHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (exchange != null) {
            String hexHash = exchange.getRequestURI().getPath().substring(Wynncraft.getInstance().getConfigurationCore().getString("WYNNCRAFT_RESOURCE_PACK_CONTEXT").length());
            byte[] content = ResourcePackHelpers.getFileFromDirectory(hexHash);

            if (content == null) {
                exchange.sendResponseHeaders(404, -1);
                return;
            }

            String sha1Hash = ResourcePackHelpers.getFileSha1Hash(hexHash);
            if (sha1Hash == null || sha1Hash.isEmpty()) {
                exchange.sendResponseHeaders(500, -1);
                return;
            }

            exchange.getResponseHeaders().add("sha1-hash", sha1Hash);

            if (exchange.getRequestMethod().equalsIgnoreCase("head")) {
                exchange.sendResponseHeaders(200, -1);
            } else {
                exchange.sendResponseHeaders(200, content.length);
                exchange.getResponseBody().write(content);
                exchange.getResponseBody().flush();
            }
        }
    }
}
