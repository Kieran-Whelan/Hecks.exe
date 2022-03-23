package me.frogdog.frogclient.ui.hud.components;

import me.frogdog.frogclient.Frog;
import me.frogdog.frogclient.ui.hud.HudComponent;
import me.frogdog.frogclient.util.Timer;
import net.minecraft.util.math.MathHelper;

public class Speed extends HudComponent {
	
	private final Timer timer = new Timer();

	public Speed() {
		super("speed", 2, 480);
	}

	@Override
	public String getComponent() {
		return "Speed: " + getSpeed() + "km/h";
	}
	
	public double getSpeed() {
		double speed;
		double prevPosX = 0;
		double prevPosZ = 0;
		if (timer.getPassedMillis(1000)) {
			prevPosX = Frog.getInstance().mc.player.prevPosX;
			prevPosZ = Frog.getInstance().mc.player.prevPosZ;
		}
		speed = Math.floor(((MathHelper.sqrt((Frog.getInstance().mc.player.posX - prevPosX) * (Frog.getInstance().mc.player.posX - prevPosX)+ (Frog.getInstance().mc.player.posZ - prevPosZ) * (Frog.getInstance().mc.player.posZ - prevPosZ))) / 1000.0f)/ (0.05f / 3600.0f));
		return speed;
		
	}

}
