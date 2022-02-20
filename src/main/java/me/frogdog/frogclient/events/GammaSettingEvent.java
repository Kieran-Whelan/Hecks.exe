package me.frogdog.frogclient.events;

import me.frogdog.api.event.Event;

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

