package me.frogdog.frogclient.command.commands;

import me.frogdog.frogclient.Frog;
import me.frogdog.frogclient.command.Command;

public class HudToggle extends Command {

	@Override
	public void onClientCommand(String command, String[] args) throws Exception {
		Frog.getInstance().getHudManager().getRegistry().forEach(h -> {
			if (h.getLabel().equalsIgnoreCase(args[0])) {
				h.toogleShow();
			}
		});
	}

	@Override
	public String getSyntax() {
		return "hudtoggle (component)";
	}

	@Override
	public String[] getCommandAlias() {
		return new String[] {"hudtoggle"};
	}

}
