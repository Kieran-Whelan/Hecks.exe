package me.frogdog.frogclient.ui;

import java.awt.Color;
import java.util.Collections;
import java.util.Comparator;

import org.lwjgl.opengl.Display;

import me.frogdog.frogclient.Frog;
import me.frogdog.frogclient.module.Module;
import me.frogdog.frogclient.module.ToggleableModule;
import me.frogdog.frogclient.module.modules.client.HudEditor;
import me.frogdog.frogclient.properties.Property;
import me.frogdog.frogclient.util.Timer;
import me.frogdog.frogclient.util.interfaces.Toggleable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Hud extends Gui {
	
	public static final Hud INSTANCE = new Hud();
    public static final Property<Boolean> customFont = new Property<Boolean>(false, "CustomFont", "cf", "font");
    public HudEditor hudEditor = new HudEditor();
	
	private Minecraft mc = Minecraft.getMinecraft();
	private final Timer timer = new Timer();
	
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
			//watermark
			fr.drawStringWithShadow(Frog.getInstance().NAME + " " + Frog.getInstance().VERSION, 2, 1, rainbow(1 * 360));
			//direction
			fr.drawStringWithShadow("Facing: " + Frog.getInstance().mc.player.getHorizontalFacing().getName(), 2, 490, rainbow(1 * 360));
			//speed
			fr.drawStringWithShadow("Speed: " + getSpeed() + "km/h"  , 2, 480, rainbow(1 * 360));
			//Effects
			//fr.drawStringWithShadow(Frog.getInstance().mc.player.getActivePotionEffects().toString(), Display.getWidth() - 50, 500, rainbow(1 * 360));
			//coords
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
	
	public int rainbow(int delay) {
		double rainbowState = Math.ceil((System.currentTimeMillis() + delay) / 20.0);
		rainbowState %= 360;
		return Color.getHSBColor((float) (rainbowState / 360.0f), 0.5f, 1f).getRGB();
	}
	
	public double getSpeed() {
		double speed;
		double prevPosX = 0;
		double prevPosZ = 0;
		if (timer.getPassedMillis(1000)) {
			prevPosX = mc.player.prevPosX;
			prevPosZ = mc.player.prevPosZ;
		}
		speed = Math.floor(((MathHelper.sqrt((mc.player.posX - prevPosX) * (mc.player.posX - prevPosX)+ (mc.player.posZ - prevPosZ) * (mc.player.posZ - prevPosZ))) / 1000.0f)/ (0.05f / 3600.0f));
		return speed;
		
	}
	
	
}
