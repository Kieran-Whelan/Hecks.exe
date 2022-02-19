package me.frogdog.frogclient.presets;

import me.frogdog.api.interfaces.Labeled;

public abstract class Preset implements Labeled {

    private final String label;

    protected Preset(String label) {
        this.label = label;
    }

    @Override
    public String getLabel() {
        return this.label;
    }

    public abstract void onSet();
}

