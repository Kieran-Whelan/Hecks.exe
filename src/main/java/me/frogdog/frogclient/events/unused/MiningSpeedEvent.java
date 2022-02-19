package me.frogdog.frogclient.events.unused;

import me.frogdog.api.event.Event;

public class MiningSpeedEvent
extends Event {
    private float speed = 1.0f;

    public float getSpeed() {
        return this.speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
}

