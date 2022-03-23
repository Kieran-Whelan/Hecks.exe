package me.frogdog.frogclient.ui.hud;

import java.util.ArrayList;

import me.frogdog.frogclient.ui.hud.components.*;
import me.frogdog.frogclient.ui.hud.HudComponent;
import me.frogdog.frogclient.ui.hud.components.*;
import me.frogdog.frogclient.util.registry.ListRegistry;

public final class HudManager extends ListRegistry<HudComponent> {
	
	public HudManager() {
		this.registry = new ArrayList<HudComponent>();
		
		register(new Coords());
		register(new ClientName());
		register(new Facing());
		register(new Speed());
		register(new ModuleArray());
		register(new Watermark());
		
	}

}
