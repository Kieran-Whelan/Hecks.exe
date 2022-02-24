package me.frogdog.frogclient.module.modules.client;

import me.frogdog.frogclient.module.Module;
import me.frogdog.frogclient.properties.Property;

public class HudEditor extends Module {
    public final Property<Boolean> coords = new Property<>(false, "Coords", "coords");

	public HudEditor() {
		super("Hud", new String[] {"hud", "Hud"});
		offerProperties(this.coords);
	}

}
