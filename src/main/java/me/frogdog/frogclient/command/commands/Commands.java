package me.frogdog.frogclient.command.commands;

import me.frogdog.frogclient.Frog;
import me.frogdog.frogclient.command.Command;

public class Commands extends Command {
	
	//broken
	@Override
	public void onClientCommand(String command, String[] args) throws Exception {
		Command.sendClientSideMessage("Commands: ");
		Frog.getInstance().getCommandManager().getRegistry().forEach(c -> {
			sendClientSideMessage(c.getSyntax());
		});	
	}
	
	@Override
	public String getSyntax() {
		return "Commands";
	}

	@Override
	public String[] getCommandAlias() {
		return new String[] {"Commands", "commands"};
	}

}
