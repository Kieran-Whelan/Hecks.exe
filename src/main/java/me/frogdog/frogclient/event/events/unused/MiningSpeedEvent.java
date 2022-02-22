package me.frogdog.frogclient.event.events.unused;

import me.frogdog.frogclient.event.Event;

public class MiningSpeedEvent extends Event {
    private float speed = 1.0f;

    public float getSpeed() {
        return this.speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
}

