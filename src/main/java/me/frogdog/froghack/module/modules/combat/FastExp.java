package me.frogdog.froghack.module.modules.combat;

import me.frogdog.froghack.module.Category;
import me.frogdog.froghack.module.Module;
import net.minecraft.client.Minecraft;

public class FastExp extends Module {
	
	private Minecraft mc = Minecraft.getMinecraft();

	public FastExp() {
		super("FastExp", "Throw exp bottle quickly", Category.COMBAT);
	}
	
}
