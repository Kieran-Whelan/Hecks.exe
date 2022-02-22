package me.frogdog.frogclient.event.events;

import me.frogdog.frogclient.event.Event;

public class RenderEvent extends Event {
    private float partialTicks;

    public RenderEvent(float partialTicks) {
        this.partialTicks = partialTicks;
    }

    public float getPartialTicks() {
        return this.partialTicks;
    }
}

