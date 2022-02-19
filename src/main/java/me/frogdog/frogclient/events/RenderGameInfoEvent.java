package me.frogdog.frogclient.events;

import me.frogdog.api.event.Event;
import net.minecraft.client.gui.ScaledResolution;

public class RenderGameInfoEvent
extends Event {
    private ScaledResolution scaledResolution;

    public RenderGameInfoEvent(ScaledResolution scaledResolution) {
        this.scaledResolution = scaledResolution;
    }

    public ScaledResolution getScaledResolution() {
        return this.scaledResolution;
    }
}

