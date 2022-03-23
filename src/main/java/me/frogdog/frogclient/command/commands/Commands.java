package me.frogdog.frogclient.command.commands;

import me.frogdog.frogclient.Frog;
import me.frogdog.frogclient.command.Command;

public final class Commands extends Command {

	public Commands() {
		super(new String[] {"commands, allcommands"}, "Commands");
	}

	@Override
	public void onClientCommand(String command, String[] args) throws Exception {
		Command.sendClientSideMessage("Commands: ");
		Frog.getInstance().getCommandManager().getRegistry().forEach(c -> {
			sendClientSideMessage(c.getSyntax());
		});	
	}

}
