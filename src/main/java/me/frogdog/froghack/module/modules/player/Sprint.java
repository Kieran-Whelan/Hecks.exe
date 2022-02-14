package me.frogdog.froghack.module.modules.player;

import org.lwjgl.input.Keyboard;

import me.frogdog.froghack.module.Category;
import me.frogdog.froghack.module.Module;
import net.minecraft.client.Minecraft;

public class Sprint extends Module {
	
	private Minecraft mc = Minecraft.getMinecraft();
	
	public Sprint() {
		super("Sprint", "Auto runs when you hold down W", Category.PLAYER);
		this.setKey(Keyboard.KEY_EQUALS);
		
	}
	
	@Override
	public void onUpdate() {
		if(!mc.player.isSprinting()) {
			mc.player.setSprinting(true);
		}
	}
	
}
