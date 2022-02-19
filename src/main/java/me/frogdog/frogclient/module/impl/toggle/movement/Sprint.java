package me.frogdog.frogclient.module.impl.toggle.movement;

import me.frogdog.frogclient.Frog;
import me.frogdog.frogclient.module.ModuleType;
import me.frogdog.frogclient.module.ToggleableModule;
import me.frogdog.frogclient.module.impl.toggle.render.Fullbright.Mode;
import me.frogdog.frogclient.properties.EnumProperty;

public final class Sprint extends ToggleableModule {
    private final EnumProperty<Mode> mode = new EnumProperty<Mode>(Mode.POTION, "Mode", "m");

    public Sprint() {
        super("Sprint", new String[]{"sprint", "Sprint"}, -7895677, ModuleType.MOVEMENT);
    }

	public static void onTick() {
		if(!Frog.getInstance().mc.player.isSprinting()) {
			Frog.getInstance().mc.player.setSprinting(true);
		}
		
	}
}
