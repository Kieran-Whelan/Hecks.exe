package me.frogdog.frogclient.command.commands;

import me.frogdog.frogclient.command.Command;

public class Help extends Command {

	@Override
	public void onClientCommand(String command, String[] args) throws Exception {
		Command.sendClientSideMessage("To see a list of all commands please use " +prefix+ "commands");
		
	}

	@Override
	public String getClientSyntax() {
		return "Help";
	}

	@Override
	public String[] getClientAlias() {
		return new String[] {"help"};
	}

}
