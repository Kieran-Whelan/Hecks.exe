package me.frogdog.api.event.filter;

import me.frogdog.api.event.Event;
import me.frogdog.api.event.Listener;

public interface Filter {
    boolean filter(Listener var1, Event var2);
}

