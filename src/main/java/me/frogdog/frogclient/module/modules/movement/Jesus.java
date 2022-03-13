package me.frogdog.frogclient.module.modules.movement;

import me.frogdog.frogclient.event.Listener;
import me.frogdog.frogclient.event.events.TickEvent;
import me.frogdog.frogclient.module.ModuleType;
import me.frogdog.frogclient.module.ToggleableModule;
import me.frogdog.frogclient.properties.EnumProperty;
import me.frogdog.frogclient.util.Timer;

public class Jesus extends ToggleableModule {
	private final EnumProperty<Mode> mode = new EnumProperty<Mode> (Mode.Solid, "Mode", "m");
	
	Timer timer = new Timer();

	public Jesus() {
		super("Jesus", new String[] {"Jesus", "jesus"}, ModuleType.MOVEMENT);
		this.offerProperties(this.mode);
        this.offerProperties(this.keybind);
		this.listeners.add(new Listener<TickEvent>("tick_listener") {
			
			@Override
			public void call(TickEvent event) {
				if (Jesus.this.mode.getValue() == Mode.Solid) {
					
				}
				
				if (Jesus.this.mode.getValue() == Mode.Dolphin) {
					if (mc.player.isInWater() || mc.player.isInLava()) {
						mc.player.jump();
					}
				}
			}
		});
		
	}
	
	public enum Mode {
		Solid,
		Dolphin
	}
	
	

}
