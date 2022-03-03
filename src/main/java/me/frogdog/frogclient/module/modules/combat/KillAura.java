package me.frogdog.frogclient.module.modules.combat;

import me.frogdog.frogclient.module.ModuleType;
import me.frogdog.frogclient.module.ToggleableModule;

public class KillAura extends ToggleableModule {

	public KillAura() {
		super("KillAura", new String[] {"KillAura", "killaura"}, ModuleType.COMBAT);
		offerProperties();
	}

}
