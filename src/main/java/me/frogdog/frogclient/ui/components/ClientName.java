package me.frogdog.frogclient.ui.components;

import me.frogdog.frogclient.Frog;
import me.frogdog.frogclient.ui.HudComponent;

public class ClientName extends HudComponent {

	public ClientName() {
		super("clientName", true, 2, 1);
	}

	@Override
	public String getComponent() {
		return Frog.getInstance().NAME + " " + Frog.getInstance().VERSION;
	}

}
