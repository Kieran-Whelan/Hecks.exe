package me.frogdog.frogclient.command.commands;

import me.frogdog.frogclient.Frog;
import me.frogdog.frogclient.command.Command;
import me.frogdog.frogclient.properties.Property;

public final class HudToggle extends Command {

	public HudToggle() {
		super(new String[] {"hudtoggle"}, "HudToggle (component)");
	}

	@Override
	public void onClientCommand(String command, String[] args) throws Exception {
		Property property = Frog.getInstance().getModuleManager().getModuleByAlias("HudEditor").getPropertyByAlias(args[0]);
		if (property.getValue() instanceof Boolean && property.getValue().equals(true)) {
			property.setValue(false);
		} else {
			property.setValue(true);
		}
	}

}
