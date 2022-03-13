package me.frogdog.frogclient.ui;

import java.util.ArrayList;

import me.frogdog.frogclient.ui.components.*;
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
