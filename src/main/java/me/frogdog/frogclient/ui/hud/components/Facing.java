package me.frogdog.frogclient.ui.hud.components;

import me.frogdog.frogclient.Frog;
import me.frogdog.frogclient.ui.hud.HudComponent;

public class Facing extends HudComponent {

	public Facing() {
		super("facing", 2, 490);
	}

	@Override
	public String getComponent() {
		return "Facing: " + Frog.getInstance().mc.player.getHorizontalFacing().getName();
	}

}
