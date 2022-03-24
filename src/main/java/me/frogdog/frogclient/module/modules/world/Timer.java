package me.frogdog.frogclient.module.modules.world;

import me.frogdog.frogclient.event.Listener;
import me.frogdog.frogclient.event.events.TickEvent;
import me.frogdog.frogclient.module.ModuleType;
import me.frogdog.frogclient.module.ToggleableModule;
import me.frogdog.frogclient.properties.NumberProperty;

public final class Timer extends ToggleableModule {
    private final NumberProperty<Float> speed = new NumberProperty<>(1f, 0f, 10f, "Speed", "TimerSpeed", "ts");

    public Timer() {
        super("Timer", new String[] {"timer", "timerspeed"}, ModuleType.WORLD);
        this.offerProperties(this.speed);
        this.offerProperties(this.keybind);
        this.listeners.add(new Listener<TickEvent>() {

            @Override
            public void call(TickEvent event) {
                mc.timer.tickLength = 50f / (Timer.this.speed.getValue() == 0f ? 0.1f : Timer.this.speed.getValue());
            }
        });
    }

    @Override
    public void onDisable() {
        super.onDisable();
        mc.timer.tickLength = 50f;

    }
}
