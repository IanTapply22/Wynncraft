package com.iantapply.wynncraft.event.wynncraft;

import lombok.Getter;
import lombok.Setter;
import net.kyori.adventure.text.Component;

@Getter @Setter
public class ExampleEvent extends WynncraftEvent {
    private Component message;

    public ExampleEvent(Component message) {
        this.message = message;
    }
}
