package me.frogdog.frogclient.module.modules.movement;

import me.frogdog.frogclient.event.Listener;
import me.frogdog.frogclient.event.events.TickEvent;
import me.frogdog.frogclient.module.ModuleType;
import me.frogdog.frogclient.module.ToggleableModule;
import net.minecraft.client.settings.KeyBinding;

public final class AutoWalk extends ToggleableModule {
	

	public AutoWalk() {
		super("AutoWalk", new String[] {"AutoWalk", "autowalk"}, ModuleType.MOVEMENT);
		this.offerProperties();
		this.listeners.add(new Listener<TickEvent>("tick_event") {
			
			@Override
			public void call(TickEvent event) {
				KeyBinding.setKeyBindState(mc.gameSettings.keyBindForward.getKeyCode(), true);
			}
		});
	}

}
