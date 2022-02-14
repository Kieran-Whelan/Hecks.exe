package me.frogdog.froghack.command;

import java.util.ArrayList;

import me.frogdog.froghack.command.commands.*;

public class CommandLoader {
	
	public void loadCommands() {
		CommandManager.commands = new ArrayList<>();
		CommandManager.addCommand(new AllCommands());
		CommandManager.addCommand(new Help());
		CommandManager.addCommand(new Toggle());
	}

}

