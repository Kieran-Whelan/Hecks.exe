package me.frogdog.frogclient.module.modules.movement;

import me.frogdog.frogclient.event.Listener;
import me.frogdog.frogclient.event.events.TickEvent;
import me.frogdog.frogclient.Frog;
import me.frogdog.frogclient.module.ModuleType;
import me.frogdog.frogclient.module.ToggleableModule;

public final class Sprint extends ToggleableModule {

    public Sprint() {
        super("Sprint", new String[]{"sprint", "Sprint"}, -2366720, ModuleType.MOVEMENT);
        this.listeners.add(new Listener<TickEvent>("key_listener"){

            @Override
            public void call(TickEvent event) {
            	if(!Frog.getInstance().mc.player.isSprinting()) {
            		Frog.getInstance().mc.player.setSprinting(true);
            	}
            }
        });
    }
}
