package me.frogdog.frogclient.event;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.concurrent.CopyOnWriteArrayList;

import me.frogdog.frogclient.event.events.TickEvent;
import me.frogdog.frogclient.event.filter.Filter;

public abstract class Listener<E extends Event> {
    private String identifier;
    private Class<E> event;
    private final java.util.List<Filter> filters = new CopyOnWriteArrayList<Filter>();

    public Listener(String identifier) {
        this.identifier = identifier;
        Type generic = this.getClass().getGenericSuperclass();
        if (generic instanceof ParameterizedType) {
            for (Type type : ((ParameterizedType)generic).getActualTypeArguments()) {
                if (!(type instanceof Class) || !Event.class.isAssignableFrom((Class)type)) continue;
                this.event = (Class)type;
                break;
            }
        }
    }

	public Listener() {
        Type generic = this.getClass().getGenericSuperclass();
        if (generic instanceof ParameterizedType) {
            for (Type type : ((ParameterizedType)generic).getActualTypeArguments()) {
                if (!(type instanceof Class) || !Event.class.isAssignableFrom((Class)type)) continue;
                this.event = (Class)type;
                break;
            }
        }
    }

    public Class<E> getEvent() {
        return this.event;
    }

    public final java.util.List<Filter> getFilters() {
        return this.filters;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void addFilters(Filter ... filters) {
        for (Filter filter : filters) {
            this.filters.add(filter);
        }
    }

    public abstract void call(E event);
}

