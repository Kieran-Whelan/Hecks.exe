package me.frogdog.frogclient.event.events;

import me.frogdog.frogclient.event.Event;

public class GammaSettingEvent extends Event {
    private float gammaSetting;

    public GammaSettingEvent(float gammaSetting) {
        this.gammaSetting = gammaSetting;
    }

    public float getGammaSetting() {
        return this.gammaSetting;
    }

    public void setGammaSetting(float gammaSetting) {
        this.gammaSetting = gammaSetting;
    }
}

