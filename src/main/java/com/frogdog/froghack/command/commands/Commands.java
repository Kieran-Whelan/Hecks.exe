package com.frogdog.froghack.command.commands;

import com.frogdog.froghack.command.Command;
import com.frogdog.froghack.command.CommandManager;

public class Commands extends Command {

	@Override
	public void onClientCommand(String command, String[] args) throws Exception {
		Command.sendClientSideMessage("Commands: ");
		CommandManager.getClientCommands().stream().forEach(cc -> {
			Command.sendClientSideMessage(cc.getClientSyntax());
		});
		
		
	}

	@Override
	public String getClientSyntax() {
		return "Commands";
	}

	@Override
	public String[] getClientAlias() {
		return new String[] {"allcommands", "commands", "command"};
	}

}
