package me.frogdog.froghack.command.commands;

import me.frogdog.froghack.Frog;
import me.frogdog.froghack.command.Command;
import me.frogdog.froghack.module.Module;

public class Toggle extends Command {
	
	@Override
	public void onClientCommand(String command, String[] args) throws Exception {
		for(Module m : Frog.moduleManager.modules) {
			if(m.getName().equalsIgnoreCase(args[0])) {
				m.toggle();
				Command.sendClientSideMessage(m.getName() + " set to " + m.isToggled());
				break;
			}
		}
		
	}

	@Override
	public String getClientSyntax() {
		return "Toggle";
	}

	@Override
	public String[] getClientAlias() {
		return new String[] {"toggle"};
	}
	

}
