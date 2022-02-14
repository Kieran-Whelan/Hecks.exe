package me.frogdog.froghack.module.modules.render;

import me.frogdog.froghack.module.Category;
import me.frogdog.froghack.module.Module;
import net.minecraft.client.Minecraft;

public class NoRender extends Module {
	
	private Minecraft mc = Minecraft.getMinecraft();

	public NoRender() {
		super("NoRender", "Stops certain items from being rendered client side", Category.RENDER);
	}
	
	public void onUpdate() {
		
	}

}
