package me.frogdog.frogclient.event.events;

import me.frogdog.frogclient.event.Event;

public class NightVisionEvent extends Event {
    private final Type type;

    public NightVisionEvent(Type type) {
        this.type = type;
    }

    public Type getType() {
        return this.type;
    }

    public static enum Type {
        TIME,
        VISUAL;

    }
}

