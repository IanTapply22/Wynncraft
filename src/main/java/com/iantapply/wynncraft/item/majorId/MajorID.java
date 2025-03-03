package com.iantapply.wynncraft.item.majorId;

import org.bukkit.entity.Player;

public abstract class MajorID {

    public abstract int id();

    public abstract String name();

    public abstract String description();

    public void execute(Player player) {}
}
