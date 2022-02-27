package me.frogdog.frogclient.event;

@SuppressWarnings("rawtypes")
public interface EventManager {

    void register(Listener listener);

    void unregister(Listener listener);

    void clear();

    void dispatch(Event event);

	java.util.List<Listener> getListeners();
}

