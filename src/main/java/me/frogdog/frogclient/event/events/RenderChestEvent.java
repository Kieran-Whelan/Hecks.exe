package me.frogdog.frogclient.event.events;

import me.frogdog.frogclient.event.Event;

public class RenderChestEvent extends Event {
    private final Time time;

    public RenderChestEvent(Time time) {
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

