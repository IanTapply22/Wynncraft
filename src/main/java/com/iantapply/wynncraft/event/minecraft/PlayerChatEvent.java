package com.iantapply.wynncraft.event.minecraft;

import com.iantapply.wynncraft.Wynncraft;
import com.iantapply.wynncraft.database.model.PlayerModel;
import com.iantapply.wynncraft.player.WynncraftPlayer;
import com.iantapply.wynncraft.rank.Rank;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerChatEvent implements Listener {

    @EventHandler
    public void onPlayerChat(AsyncChatEvent event) {
        WynncraftPlayer wynncraftPlayer = Wynncraft.getInstance().getPlayerCore().getPlayer(event.getPlayer().getUniqueId());
        PlayerModel playerModel = wynncraftPlayer.getPlayerModel();

        // Convert Adventure Component to String
        String message = PlainTextComponentSerializer.plainText().serialize(event.message());

        // Format the nickname or username
        String name = (playerModel.getNickname() != null) ? playerModel.getNickname() : playerModel.getUsername();

        // Start with parsing as regular player rank
        Component chatMessage = Component.text(playerModel.getServer() + " ").color(NamedTextColor.GRAY)
                .append(playerModel.getRank().getChatDisplay())
                .append(Component.empty())
                .append(Component.text(name + ": ").color(playerModel.getRank().getChatDisplay().color()))
                .append(Component.text(message).color(NamedTextColor.WHITE));

        // If they have support rank, display that instead
        if (playerModel.getSupportRank() != null) {
            chatMessage = Component.text(playerModel.getServer() + " ").color(NamedTextColor.GRAY)
                    .append(playerModel.getSupportRank().getChatDisplay())
                    .append(Component.text(" "))
                    .append(Component.text(name + ": ").color(playerModel.getSupportRank().getChatDisplay().color()))
                    .append(Component.text(message).color(NamedTextColor.WHITE));
        }

        // If they have a rank other than player, display that layered over top. A player can only have one rank shown in chat
        if (playerModel.getRank() != null && playerModel.getRank() != Rank.PLAYER) {
            chatMessage = Component.text(playerModel.getServer() + " ").color(NamedTextColor.GRAY)
                    .append(playerModel.getRank().getChatDisplay())
                    .append(Component.text(" "))
                    .append(Component.text(name + ": ").color(playerModel.getRank().getChatDisplay().color()))
                    .append(Component.text(message).color(NamedTextColor.WHITE));
        }

        // Broadcast the formatted message using Adventure API
        event.getPlayer().getServer().sendMessage(chatMessage);

        // Cancel the event to prevent default chat handling
        event.setCancelled(true);
    }
}
