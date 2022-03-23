package me.frogdog.frogclient.command.commands;

import me.frogdog.frogclient.Frog;
import me.frogdog.frogclient.command.Command;
import me.frogdog.frogclient.properties.Property;

public class HudToggle extends Command {

	@Override
	public void onClientCommand(String command, String[] args) throws Exception {
		Property property = Frog.getInstance().getModuleManager().getModuleByAlias("HudEditor").getPropertyByAlias(args[0]);
		if (property.getValue() instanceof Boolean && property.getValue().equals(true)) {
			property.setValue(false);
		} else {
			property.setValue(true);
		}
	}

	@Override
	public String getSyntax() {
		return "HudToggle (component)";
	}

	@Override
	public String[] getCommandAlias() {
		return new String[] {"hudtoggle"};
	}

}
