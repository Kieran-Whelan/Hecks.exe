package me.frogdog.frogclient.events.unused;

import me.frogdog.api.event.Event;

public class ItemInUseEvent
extends Event {
    private float speed;

    public ItemInUseEvent(float speed) {
        this.speed = speed;
    }

    public float getSpeed() {
        return this.speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
}

