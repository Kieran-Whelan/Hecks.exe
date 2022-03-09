package me.frogdog.frogclient.module.modules.misc;

import me.frogdog.frogclient.event.Listener;
import me.frogdog.frogclient.event.events.TickEvent;
import me.frogdog.frogclient.module.ModuleType;
import me.frogdog.frogclient.module.ToggleableModule;
import me.frogdog.frogclient.properties.EnumProperty;

public class SmallOffhand extends ToggleableModule {
	private final EnumProperty<Mode> mode = new EnumProperty<Mode> (Mode.Small, "Mode", "m");

	public SmallOffhand() {
		super("SmallOffhand", new String[] {"SmallOffhand", "smalloffhand"}, ModuleType.MISCELLANEOUS);
		this.offerProperties(this.mode, this.keybind);
		this.listeners.add(new Listener<TickEvent>("tick_listener") {
			
			@Override
			public void call(TickEvent event) {
				if(SmallOffhand.this.mode.getValue() == Mode.Small) {
			        mc.entityRenderer.itemRenderer.equippedProgressOffHand = 0.5f;
				} else if(SmallOffhand.this.mode.getValue() == Mode.Invis) {
					mc.entityRenderer.itemRenderer.equippedProgressOffHand = -1.0f;
				}

			}
		});
		
	}
	
	public enum Mode {
		Small,
		Invis
	}
}
