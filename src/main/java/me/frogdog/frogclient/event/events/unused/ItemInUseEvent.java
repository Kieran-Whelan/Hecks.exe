package me.frogdog.frogclient.event.events.unused;

import me.frogdog.frogclient.event.Event;

public class ItemInUseEvent extends Event {
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

