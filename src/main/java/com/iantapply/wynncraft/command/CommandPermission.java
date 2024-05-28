package com.iantapply.wynncraft.command;

import lombok.Getter;

/**
 * Represents a command permission that is associated with a command
 * <p>
 * The permission is formatted as "wynncraft.command.<command_name>" for simplicity.
 * Additionally, you can also assign required permission to also be a rank permission
 */
@Getter
public enum CommandPermission {
    EXAMPLE("wynncraft.command.example");

    private final String permission;

    CommandPermission(String permission) {
        this.permission = permission;
    }
}
