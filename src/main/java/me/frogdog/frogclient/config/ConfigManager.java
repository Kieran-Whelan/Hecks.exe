package me.frogdog.frogclient.config;

import java.util.ArrayList;

import me.frogdog.api.registry.ListRegistry;

public final class ConfigManager
extends ListRegistry<Config> {

    public ConfigManager() {
        this.registry = new ArrayList();
    }
}

