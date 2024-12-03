package com.iantapply.wynncraft.database.constructor;

import java.util.UUID;

public record GuildPlayer(UUID uuid, String name, String prefix, String rank, String rankStars) {
}
