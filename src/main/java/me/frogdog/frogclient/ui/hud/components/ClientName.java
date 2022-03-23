package me.frogdog.frogclient.ui.hud.components;

import me.frogdog.frogclient.Frog;
import me.frogdog.frogclient.ui.hud.HudComponent;

public class ClientName extends HudComponent {

	public ClientName() {
		super("clientName", 2, 1);
	}

	@Override
	public String getComponent() {
		return Frog.getInstance().NAME + " " + Frog.getInstance().VERSION;
	}

}
