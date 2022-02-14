package me.frogdog.froghack.module.modules.client;

import me.frogdog.froghack.module.Category;
import me.frogdog.froghack.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class FullBright extends Module {
	
	private Minecraft mc = Minecraft.getMinecraft();

	public FullBright() {
		super("Fullbright", "Makes screen fully bright", Category.CLIENT);
		// TODO Auto-generated constructor stub
	}
	
	public void onUpdate() {
		mc.player.addPotionEffect(new PotionEffect(Potion.getPotionById(16), 100, 0));
	}

}
