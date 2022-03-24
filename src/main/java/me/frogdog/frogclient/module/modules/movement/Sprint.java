package me.frogdog.frogclient.module.modules.movement;

import me.frogdog.frogclient.event.Event;
import me.frogdog.frogclient.event.Listener;
import me.frogdog.frogclient.event.events.TickEvent;
import me.frogdog.frogclient.module.ModuleType;
import me.frogdog.frogclient.module.ToggleableModule;

public final class Sprint extends ToggleableModule {

    public Sprint() {
        super("Sprint", new String[]{"sprint", "Sprint"}, ModuleType.MOVEMENT);
		this.offerProperties(this.keybind);	
        this.listeners.add(new Listener<TickEvent>("tick_listener"){

            @Override
            public void call(TickEvent event) {
            	if(!mc.player.isSprinting() && mc.player.moveForward > 0 && !mc.player.collidedHorizontally) {
            		mc.player.setSprinting(true);
            	}
            }
        });
    }
}
