package me.frogdog.frogclient.ui;

import java.awt.Color;
import java.util.Collections;
import java.util.Comparator;

import me.frogdog.frogclient.Frog;
import me.frogdog.frogclient.module.Module;
import me.frogdog.frogclient.module.ToggleableModule;
import me.frogdog.frogclient.properties.Property;
import me.frogdog.frogclient.util.interfaces.Toggleable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Hud extends Gui {
	
	public static final Hud INSTANCE = new Hud();
    public static final Property<Boolean> customFont = new Property<Boolean>(false, "CustomFont", "cf", "font");
	
	private Minecraft mc = Minecraft.getMinecraft();
	
	public static class ModuleComparator implements Comparator<Module> {

		@Override
		public int compare(Module o1, Module o2) {
			if(Minecraft.getMinecraft().fontRenderer.getStringWidth(o1.getLabel()) > Minecraft.getMinecraft().fontRenderer.getStringWidth(o2.getLabel())) {
				return -1;
			}
			if(Minecraft.getMinecraft().fontRenderer.getStringWidth(o1.getLabel()) > Minecraft.getMinecraft().fontRenderer.getStringWidth(o2.getLabel())) {
				return 1;
			}
			return 0;
		}
		
		
	}
	
	private final ResourceLocation watermark = new ResourceLocation(Frog.getInstance().MODID , "textures/watermark.png");
	
	@SubscribeEvent
	public void renderOverlay(RenderGameOverlayEvent event) {
		
		Collections.sort(Frog.getInstance().getModuleManager().getRegistry(), new ModuleComparator());
		ScaledResolution sr = new ScaledResolution(mc);
		FontRenderer fr = mc.fontRenderer;
		
		if(event.getType() == RenderGameOverlayEvent.ElementType.HELMET) {
			mc.renderEngine.bindTexture(watermark);
			drawScaledCustomSizeModalRect(10, 15, 0, 0, 40, 40, 40, 40, 40, 40);
		}
		
		if(event.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
			fr.drawStringWithShadow(Frog.getInstance().NAME + " " + Frog.getInstance().VERSION, 2, 1, rainbow(1 * 360));
			if(Frog.getInstance().mc.player.dimension == 0) {
				fr.drawStringWithShadow("X " + (long) Frog.getInstance().mc.player.posX + " Y " + (long) Frog.getInstance().mc.player.posY + " Z " +  (long) Frog.getInstance().mc.player.posZ + " Nether: X " + (long) Frog.getInstance().mc.player.posX / 8 + " Z " + (long) Frog.getInstance().mc.player.posZ / 8, 2, 500, rainbow(1 * 360));
			} else if(Frog.getInstance().mc.player.dimension == -1) {
				fr.drawStringWithShadow("X " + (long) Frog.getInstance().mc.player.posX + " Y " + (long) Frog.getInstance().mc.player.posY + " Z " +  (long) Frog.getInstance().mc.player.posZ + " OverWorld: X " + (long) Frog.getInstance().mc.player.posX * 8 + " Z " + (long) Frog.getInstance().mc.player.posZ * 8, 2, 500, rainbow(1 * 360));
			} else {
				fr.drawStringWithShadow("X " + (long) Frog.getInstance().mc.player.posX + " Y " + (long) Frog.getInstance().mc.player.posY + " Z " +  (long) Frog.getInstance().mc.player.posZ, 2, 500, rainbow(1 * 360));
			}
		}
		
		if(event.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
			int y = 2;
			final int[] counter = {1};
			for(Module m : Frog.getInstance().getModuleManager().getRegistry()) {
				if(!m.getLabel().equalsIgnoreCase(null) && m instanceof Toggleable) {
		            ToggleableModule toggleableModule = (ToggleableModule)m;
					if (toggleableModule.isRunning()) {
						fr.drawStringWithShadow(m.getLabel(), sr.getScaledWidth() -fr.getStringWidth(m.getLabel()) - 2, y, rainbow(counter[0] * 360));
						y += fr.FONT_HEIGHT;
						counter[0]++;
					}
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
