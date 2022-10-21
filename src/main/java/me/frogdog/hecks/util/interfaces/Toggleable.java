package me.frogdog.hecks.util.interfaces;

public interface Toggleable {

    boolean isRunning();

    void setRunning(boolean running);

    void toggle();
}
