package me.frogdog.frogclient.module.modules.movement;

import me.frogdog.api.event.Listener;
import me.frogdog.frogclient.Frog;
import me.frogdog.frogclient.events.TickEvent;
import me.frogdog.frogclient.module.ModuleType;
import me.frogdog.frogclient.module.ToggleableModule;
import net.minecraft.client.settings.KeyBinding;

public class AutoWalk extends ToggleableModule {

	public AutoWalk() {
		super("AutoWalk", new String[] {"AutoWalk", "autowalk"}, -2366720, ModuleType.MOVEMENT);
		this.offerProperties();
		this.listeners.add(new Listener<TickEvent>("tick_event") {
			
			@Override
			public void call(TickEvent event) {
				KeyBinding.setKeyBindState(Frog.getInstance().mc.gameSettings.keyBindForward.getKeyCode(), true);
			}
		});
	}

}
