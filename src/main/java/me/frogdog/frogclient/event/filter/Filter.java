package me.frogdog.frogclient.event.filter;

import me.frogdog.frogclient.event.Event;
import me.frogdog.frogclient.event.Listener;

public interface Filter {
    boolean filter(Listener var1, Event var2);
}

