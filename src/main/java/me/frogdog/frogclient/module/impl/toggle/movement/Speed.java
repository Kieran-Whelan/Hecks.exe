package me.frogdog.frogclient.module.impl.toggle.movement;

import me.frogdog.api.event.Listener;
import me.frogdog.frogclient.Frog;
import me.frogdog.frogclient.events.MotionUpdateEvent;
import me.frogdog.frogclient.events.PacketEvent;
import me.frogdog.frogclient.events.TickEvent;
import me.frogdog.frogclient.module.ModuleType;
import me.frogdog.frogclient.module.ToggleableModule;
import me.frogdog.frogclient.module.impl.toggle.render.Fullbright.Mode;
import me.frogdog.frogclient.properties.EnumProperty;

public final class Speed extends ToggleableModule {
    private final EnumProperty<Mode> mode = new EnumProperty<Mode>(Mode.POTION, "Mode", "m");

    public Speed() {
        super("Speed", new String[]{"speed", "Speed"}, -7895677, ModuleType.MOVEMENT);
        Frog.getInstance().getEventManager().register(new Listener<MotionUpdateEvent>("motion_listener"){
        	
        	@Override
        	public void call(MotionUpdateEvent event) {
        	}
        });
    }
}
