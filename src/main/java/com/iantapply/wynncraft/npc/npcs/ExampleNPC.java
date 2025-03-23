package com.iantapply.wynncraft.npc.npcs;

import com.iantapply.wynncraft.npc.NPC;
import com.iantapply.wynncraft.world.NPCLocation;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerInfoUpdatePacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ClientInformation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.CommonListenerCookie;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ExampleNPC implements NPC {

    /**
     * Spawns the NPC in the game world with all of its settings
     * including the entity type
     */
    @Override
    public void spawn(Location location) {
        MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
        ServerLevel world = ((CraftWorld) location.getWorld()).getHandle();

        GameProfile gameProfile = new GameProfile(UUID.randomUUID(), this.name());
        Property skinProperty = new Property("", "");
        gameProfile.getProperties().put("textures", skinProperty);

        ServerPlayer npc = new ServerPlayer(server, world, gameProfile, ClientInformation.createDefault());
        npc.connection = new ServerGamePacketListenerImpl(server, new Connection(PacketFlow.SERVERBOUND), npc,
                CommonListenerCookie.createInitial(gameProfile, false));
        npc.setPos(location.getX(), location.getY(), location.getZ());

        npc.setCustomName(Component.literal(this.name()));
        npc.setCustomNameVisible(true);

        world.addFreshEntity(npc);

        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            ServerGamePacketListenerImpl connection = ((CraftPlayer) onlinePlayer).getHandle().connection;

            connection.send(new ClientboundPlayerInfoUpdatePacket(ClientboundPlayerInfoUpdatePacket.Action.ADD_PLAYER, npc));
            connection.send(new ClientboundAddEntityPacket(npc.getId(), npc.getUUID(), location.getX(), location.getY(), location.getZ(), location.getPitch(), location.getYaw(), npc.getType(), 0, npc.getDeltaMovement(), npc.getYHeadRot()));
        }
    }

    /**
     * What should be done when the player interacts with the NPC
     * This is usually starting dialogue or a new quest
     */
    @Override
    public void onInteract(Player player) {
        // Play the first and only dialogue line in the NPC
        playDialogue(player, 0);
    }

    /**
     * The name of the NPC displayed at the top of the entity
     *
     * @return The name of the NPC
     */
    @Override
    public String name() {
        return "Example NPC";
    }

    /**
     * The description of the NPC that is displayed below the name
     *
     * @return The description of the NPC
     */
    @Override
    public String description() {
        return "NPC";
    }

    /**
     * The location of the NPC as a region or area
     *
     * @return The location name of the NPC
     */
    @Override
    public NPCLocation location() {
        return NPCLocation.EXAMPLE_NPC;
    }

    @Override
    public World serverWorld() {
        return Bukkit.getWorld("world");
    }
}
