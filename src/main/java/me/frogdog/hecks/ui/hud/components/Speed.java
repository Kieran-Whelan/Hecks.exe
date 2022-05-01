package me.frogdog.hecks.ui.hud.components;

import me.frogdog.hecks.Hecks;
import me.frogdog.hecks.ui.hud.HudComponent;
import me.frogdog.hecks.util.PlayerUtil;
import me.frogdog.hecks.util.Timer;
import net.minecraft.util.math.MathHelper;

public class Speed extends HudComponent {

    private final Timer timer = new Timer();

    public Speed() {
        super("speed", 2, 480);
    }

    @Override
    public String getComponent() {
        return "Speed: " + PlayerUtil.getSpeed() + "km/h";
    }

}
