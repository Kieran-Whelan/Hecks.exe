package me.frogdog.frogclient.module.modules.movement;

import me.frogdog.frogclient.event.Listener;
import me.frogdog.frogclient.event.events.TickEvent;
import me.frogdog.frogclient.module.ModuleType;
import me.frogdog.frogclient.module.ToggleableModule;
import net.minecraft.util.EnumFacing;

public final class YawLock extends ToggleableModule {
	
	float yaw;

	public YawLock() {
		super("LockYaw", new String[] {"RotationLock", "lock"}, ModuleType.MOVEMENT);
		this.offerProperties(this.keybind);	
		this.listeners.add(new Listener<TickEvent>("tick_event") {
			
			@Override
			public void call(TickEvent event) {
					if(mc.player.rotationYaw < yaw - 1.1f ) {
						mc.player.rotationYaw++;
					} else if(mc.player.rotationYaw > yaw + 1.1f ) {
						mc.player.rotationYaw--;
					} else {
						return;
					}
				}		
				
		});
		
	}
	
	@Override
	protected void onEnable() {
		super.onEnable();
		EnumFacing direction = mc.player.getHorizontalFacing();
		switch (direction) {
			case EAST:
				mc.player.rotationYaw = 270;
				yaw = 270;
				break;
			case WEST:
				mc.player.rotationYaw = 90;
				yaw = 90;
				break;
			case NORTH:
				mc.player.rotationYaw = 180;
				yaw = 180;
				break;
			case SOUTH:
				mc.player.rotationYaw = 360;
				yaw = 360;
				break;
			default:
				break;
		}

	}
}
