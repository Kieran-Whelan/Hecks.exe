package me.frogdog.frogclient.ui.hud;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;

import me.frogdog.frogclient.Frog;
import me.frogdog.frogclient.module.modules.client.HudEditor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Hud extends Gui {
	
	public static final Hud INSTANCE = new Hud();
	public Minecraft mc = Minecraft.getMinecraft();
	
	@SuppressWarnings("unchecked")
	@SubscribeEvent
	public void renderOverlay(RenderGameOverlayEvent event) {
		
		ScaledResolution sr = new ScaledResolution(mc);
		FontRenderer fr = mc.fontRenderer;
		
		if (event.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
			for (HudComponent h : Frog.getInstance().getHudManager().getRegistry()) {
				if (HudEditor.getShow(h.getLabel())) {
					if (h.getComponent() instanceof String) {
						fr.drawStringWithShadow((String) h.getComponent(), h.getxPos(), h.getyPos(), rainbow(1 * 360));
					} else if (h.getComponent() instanceof ArrayList) {
						int y = 2;
						for (String s : (Collection<? extends String>) h.getComponent()) {
							fr.drawStringWithShadow(s, sr.getScaledWidth() - fr.getStringWidth(s) - 2, y, rainbow(1 * 360));
							y += fr.FONT_HEIGHT;
						}
					} else if (h.getComponent() instanceof ResourceLocation) {
						mc.renderEngine.bindTexture((ResourceLocation) h.getComponent());
						drawScaledCustomSizeModalRect(h.getxPos(), h.getyPos(), 0, 0, 40, 40, 40, 40, 40, 40);
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
	
	
}
