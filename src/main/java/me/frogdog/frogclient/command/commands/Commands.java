package me.frogdog.frogclient.command.commands;

import me.frogdog.frogclient.command.Command;
import me.frogdog.frogclient.command.CommandManager;
/**
public class Commands extends Command {
	
	//broken
	@Override
	public void onClientCommand(String command, String[] args) throws Exception {
		Command.sendClientSideMessage("Commands: ");
		CommandManager.getRegistry().forEach(cc -> {
			.sendClientSideMessage(cc.getClientSyntax());
		});	
	}
	
	@Override
	public String getClientSyntax() {
		return "AllCommands";
	}

	@Override
	public String[] getClientAlias() {
		return new String[] {"allcommands", "commands", "command"};
	}

}
**/
