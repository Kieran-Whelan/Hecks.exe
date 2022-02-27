package me.frogdog.frogclient.config;

import java.util.ArrayList;

import me.frogdog.frogclient.util.registry.ListRegistry;

public final class ConfigManager
extends ListRegistry<Config> {

    public ConfigManager() {
        this.registry = new ArrayList<Config>();
    }
}

