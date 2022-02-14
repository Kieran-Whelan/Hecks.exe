package me.frogdog.froghack.module.modules.player;

import me.frogdog.froghack.module.Category;
import me.frogdog.froghack.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;

public class AutoWalk extends Module {
	
	private Minecraft mc = Minecraft.getMinecraft();

	public AutoWalk() {
		super("AutoWalk", "Automatically walks you forward", Category.PLAYER);
	}
	
	@Override
	public void onUpdate() {
		if(mc.currentScreen == null) {
			KeyBinding.setKeyBindState(mc.gameSettings.keyBindForward.getKeyCode(), true);
		}
	}

}
