package com.frogdog.froghack.command;

import java.util.ArrayList;

import com.frogdog.froghack.command.commands.Commands;

public class CommandLoader {
	
	public void loadCommands() {
		CommandManager.commands = new ArrayList<>();
		CommandManager.addCommand(new Commands());
	}

}
