package me.frogdog.frogclient.ui.components;

import me.frogdog.frogclient.Frog;
import me.frogdog.frogclient.ui.HudComponent;
import net.minecraft.util.ResourceLocation;

public class Watermark extends HudComponent {
	
	private final ResourceLocation watermark = new ResourceLocation(Frog.getInstance().MODID , "textures/watermark.png");

	public Watermark() {
		super("watermark", 10, 15);
	}

	@Override
	public ResourceLocation getComponent() {
		return watermark;
	}
	
	

}
