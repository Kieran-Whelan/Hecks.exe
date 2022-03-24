package me.frogdog.frogclient.module.modules.world;

import me.frogdog.frogclient.event.Event;
import org.lwjgl.input.Keyboard;

import me.frogdog.frogclient.event.Listener;
import me.frogdog.frogclient.event.events.TickEvent;
import me.frogdog.frogclient.module.ModuleType;
import me.frogdog.frogclient.module.ToggleableModule;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;

public final class Scaffold extends ToggleableModule {
	
	float yaw;

	public Scaffold() {
		super("Scaffold", new String[] {"Scaffold", "scaffold"}, ModuleType.WORLD);
		this.offerProperties(this.keybind);
        this.listeners.add(new Listener<TickEvent>("tick_listener") {

			@Override
            public void call(TickEvent event) {
            	if (Keyboard.getEventKey() == Keyboard.KEY_W) {
            		KeyBinding.setKeyBindState(mc.gameSettings.keyBindForward.getKeyCode(), false);
            		KeyBinding.setKeyBindState(mc.gameSettings.keyBindBack.getKeyCode(), true);
            		placeBlock();
            	} else {

            	}
            }
        });
	}
	
	private void placeBlock() {
		mc.player.rotationYaw = yaw;
		mc.player.rotationPitch = 82;
		mc.player.swingArm(EnumHand.MAIN_HAND);
		KeyBinding.setKeyBindState(mc.gameSettings.keyBindUseItem.getKeyCode(), true);
		//KeyBinding.setKeyBindState(mc.gameSettings.keyBindSneak.getKeyCode(), true);

	}
	
	@Override
	public void onEnable() {
		super.onEnable();
		EnumFacing direction = mc.player.getHorizontalFacing();
		switch (direction) {
			case EAST:
				yaw = 90;
				break;
			case WEST:
				yaw = 270;
				break;
			case NORTH:
				yaw = 360;
				break;
			case SOUTH:
				yaw = 180;
				break;
			default:
				break;
		}
		
	}

}
