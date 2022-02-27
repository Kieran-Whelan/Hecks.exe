package me.frogdog.frogclient.module.modules.world;

import me.frogdog.frogclient.Frog;
import me.frogdog.frogclient.event.Listener;
import me.frogdog.frogclient.event.events.TickEvent;
import me.frogdog.frogclient.module.ModuleType;
import me.frogdog.frogclient.module.ToggleableModule;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;

public class Freecam extends ToggleableModule {

	public Freecam() {
		super("Freecam", new String[] {"Freecam", "freecam"}, -2366720, ModuleType.WORLD);
        this.listeners.add(new Listener<TickEvent>("tick_listener"){

            @Override
            public void call(TickEvent event) {
            }
        });
	}

}
