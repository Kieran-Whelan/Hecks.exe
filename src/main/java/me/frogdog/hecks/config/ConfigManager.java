package me.frogdog.hecks.config;

import me.frogdog.hecks.util.math.Registry;

import java.util.ArrayList;

public final class ConfigManager extends Registry<Config> {

    public ConfigManager() {
        this.registry = new ArrayList<Config>();
    }
}
