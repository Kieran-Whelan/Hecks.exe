package me.frogdog.frogclient.events;

import me.frogdog.api.event.Event;

public class RenderEntityEvent
extends Event {
    private final Time time;

    public RenderEntityEvent(Time time) {
        this.time = time;
    }

    public Time getTime() {
        return this.time;
    }

    public static enum Time {
        BEFORE,
        AFTER;

    }
}

