package me.frogdog.frogclient.command.commands;

import org.lwjgl.input.Keyboard;

import me.frogdog.frogclient.Frog;
import me.frogdog.frogclient.command.Command;

public class Bind extends Command {

	@Override
	public void onClientCommand(String command, String[] args) throws Exception {
		String key = args[1].toUpperCase();
		Frog.getInstance().getKeybindManager().getKeybindByLabel(args[0]).setKey(Keyboard.getKeyIndex(key));
		Command.sendClientSideMessage(args[0] + "'s bind has been set to " + key);
		
	}

	@Override
	public String getClientSyntax() {
		return "Bind";
	}

	@Override
	public String[] getClientAlias() {
		return new String[] {"bind"};
	}

}
