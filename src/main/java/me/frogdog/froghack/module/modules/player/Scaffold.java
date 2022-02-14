package me.frogdog.froghack.module.modules.player;

import me.frogdog.froghack.module.Category;
import me.frogdog.froghack.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;

public class Scaffold extends Module {
	
	private Minecraft mc = Minecraft.getMinecraft();

	public Scaffold() {
		super("Scaffold", "Places blocks underneath player", Category.PLAYER);
		// TODO Auto-generated constructor stub
	}
	
	public void onUpdate() {
		if(mc.currentScreen == null) {
			KeyBinding.setKeyBindState(mc.gameSettings.keyBindUseItem.getKeyCode(), true);
		}
	}

}
