package me.frogdog.api.interfaces;

/**
 * An interface for Objects that can be toggled.
 */
public interface Toggleable {

    boolean isRunning();

    void setRunning(boolean var1);

    void toggle();
}

