package me.frogdog.frogclient.ui;

import java.util.ArrayList;

import me.frogdog.frogclient.ui.components.Coords;
import me.frogdog.frogclient.util.registry.ListRegistry;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public final class HudManager extends ListRegistry<HudComponent> {
	
	public HudManager() {
		this.registry = new ArrayList<HudComponent>();
		
		register(new Coords());
	}
	
	@SubscribeEvent
	public void renderOverlay(RenderGameOverlayEvent event) {
		
	}

}
