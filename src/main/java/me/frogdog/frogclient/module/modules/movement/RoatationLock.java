package me.frogdog.frogclient.module.modules.movement;

import me.frogdog.frogclient.event.Listener;
import me.frogdog.frogclient.event.events.TickEvent;
import me.frogdog.frogclient.module.ModuleType;
import me.frogdog.frogclient.module.ToggleableModule;
import me.frogdog.frogclient.properties.Property;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.math.MathHelper;

public class RoatationLock extends ToggleableModule {
    private final Property<Boolean> yaw = new Property<Boolean>(false, "Yaw", "y");
    private final Property<Boolean> pitch = new Property<Boolean>(false, "Pitch", "p");

	public RoatationLock() {
		super("LockYaw", new String[] {"RotationLock", "lock"}, -2366720, ModuleType.MOVEMENT);
		offerProperties(this.pitch, this.yaw);
		this.listeners.add(new Listener<TickEvent>("tick_event") {
			
			@Override
			public void call(TickEvent event) {
				if(yaw.getValue() == true) {
					mc.player.rotationYaw = MathHelper.clamp(-180, -180, 180);
				}
				
				if(pitch.getValue() == true) {
					mc.player.rotationPitch = MathHelper.clamp(-180, 180, 180);
				}
			}
		});
	}

}
