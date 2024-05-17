package com.iantapply.wynncraft.npc.types;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.bukkit.Location;

public class Villager extends net.minecraft.world.entity.npc.Villager {

    public Villager(Location location) {
        super(EntityType.VILLAGER, (Level) location.getWorld());

        Vec3 locationVector = new Vec3(location.getX(), location.getY(), location.getZ());
        setPos(locationVector);
        level().addFreshEntity(this);
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        return super.mobInteract(player, hand);
    }
}
