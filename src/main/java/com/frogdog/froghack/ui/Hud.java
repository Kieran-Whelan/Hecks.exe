package com.frogdog.froghack.ui;

import java.awt.Color;
import java.util.Collections;
import java.util.Comparator;

import com.frogdog.froghack.Frog;
import com.frogdog.froghack.module.Module;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Hud extends Gui {
	
	private Minecraft mc = Minecraft.getMinecraft();
	
	public static class ModuleComparator implements Comparator<Module> {

		@Override
		public int compare(Module o1, Module o2) {
			if(Minecraft.getMinecraft().fontRenderer.getStringWidth(o1.getName()) > Minecraft.getMinecraft().fontRenderer.getStringWidth(o2.getName())) {
				return -1;
			}
			if(Minecraft.getMinecraft().fontRenderer.getStringWidth(o1.getName()) > Minecraft.getMinecraft().fontRenderer.getStringWidth(o2.getName())) {
				return 1;
			}
			return 0;
		}
		
		
	}
	
	private final ResourceLocation watermark = new ResourceLocation(Frog.MODID, "textures/watermark.png");
	
	@SubscribeEvent
	public void renderOverlay(RenderGameOverlayEvent event) {
		
		Collections.sort(Frog.moduleManager.modules, new ModuleComparator());
		ScaledResolution sr = new ScaledResolution(mc);
		FontRenderer fr = mc.fontRenderer;
		
		if(event.getType() == RenderGameOverlayEvent.ElementType.HELMET) {
			mc.renderEngine.bindTexture(watermark);
			drawScaledCustomSizeModalRect(10, 15, 0, 0, 40, 40, 40, 40, 40, 40);
		}
		
		if(event.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
			fr.drawStringWithShadow("FrogHack "+Frog.VERSION+"", 2, 1, rainbow(1 * 360));
		}
		
		if(event.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
			int y = 2;
			final int[] counter = {1};
			for(Module m : Frog.moduleManager.getModuleList()) {
				if(!m.getName().equalsIgnoreCase("TabGui") && m.isToggled()) {
					fr.drawStringWithShadow(m.getName(), sr.getScaledWidth() -fr.getStringWidth(m.getName()) - 2, y, rainbow(counter[0] * 360));
					y += fr.FONT_HEIGHT;
					counter[0]++;
				}
			}
		}
		
	}
	
	public static int rainbow(int delay) {
		double rainbowState = Math.ceil((System.currentTimeMillis() + delay) / 20.0);
		rainbowState %= 360;
		return Color.getHSBColor((float) (rainbowState / 360.0f), 0.5f, 1f).getRGB();
	}
	
	
}
