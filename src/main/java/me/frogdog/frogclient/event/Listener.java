package me.frogdog.frogclient.event;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.concurrent.CopyOnWriteArrayList;

import me.frogdog.frogclient.event.filter.Filter;

public abstract class Listener<E extends Event> {
    private final String identifier;
    private Class<E> event;
    private final java.util.List<Filter> filters = new CopyOnWriteArrayList<Filter>();

    @SuppressWarnings({ "unchecked", "rawtypes" })
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

    public Class<E> getEvent() {
        return this.event;
    }

    public final String getIdentifier() {
        return this.identifier;
    }

    public final java.util.List<Filter> getFilters() {
        return this.filters;
    }

    public void addFilters(Filter ... filters) {
        for (Filter filter : filters) {
            this.filters.add(filter);
        }
    }

    public abstract void call(E event);
}

