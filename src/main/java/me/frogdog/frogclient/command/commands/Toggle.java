package me.frogdog.frogclient.command.commands;

import me.frogdog.frogclient.Frog;
import me.frogdog.frogclient.command.Command;
import me.frogdog.frogclient.module.Module;
import me.frogdog.frogclient.module.ToggleableModule;
import me.frogdog.api.interfaces.Toggleable;

public class Toggle extends Command {
	
	@Override
	public void onClientCommand(String command, String[] args) throws Exception {
        Module module = Frog.getInstance().getModuleManager().getModuleByAlias((String) args(0));
        if (module == null) {
        	Command.sendClientSideMessage("No such module exists");
        } else if (!(module instanceof Toggleable)) {
        	Command.sendClientSideMessage("That module can't be toggled");
        } else {
            ToggleableModule toggleableModule = (ToggleableModule)module;
            toggleableModule.toggle();
	        Command.sendClientSideMessage(module.getLabel() + "has been set toggled");    
        }
        
	}

	private String args(int i) {
		return null;
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
