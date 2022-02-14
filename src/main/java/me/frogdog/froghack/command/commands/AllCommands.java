package me.frogdog.froghack.command.commands;

import me.frogdog.froghack.command.Command;
import me.frogdog.froghack.command.CommandManager;

public class AllCommands extends Command {

	@Override
	public void onClientCommand(String command, String[] args) throws Exception {
		Command.sendClientSideMessage("Commands: ");
		CommandManager.getClientCommands().stream().forEach(cc -> {
			Command.sendClientSideMessage(cc.getClientSyntax());
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
