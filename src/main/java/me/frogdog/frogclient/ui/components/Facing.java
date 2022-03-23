package me.frogdog.frogclient.ui.components;

import me.frogdog.frogclient.Frog;
import me.frogdog.frogclient.ui.HudComponent;

public class Facing extends HudComponent {

	public Facing() {
		super("facing", 2, 490);
	}

	@Override
	public String getComponent() {
		return "Facing: " + Frog.getInstance().mc.player.getHorizontalFacing().getName();
	}

}
