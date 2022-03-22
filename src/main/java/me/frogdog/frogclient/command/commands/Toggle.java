package me.frogdog.frogclient.command.commands;

import me.frogdog.frogclient.Frog;
import me.frogdog.frogclient.command.Command;
import me.frogdog.frogclient.module.Module;
import me.frogdog.frogclient.module.ToggleableModule;
import me.frogdog.frogclient.util.interfaces.Toggleable;

public class Toggle extends Command {
	
	@Override
	public void onClientCommand(String command, String[] args) throws Exception {
        Module module = Frog.getInstance().getModuleManager().getModuleByAlias(args[0]);
        if (module == null) {
        	Command.sendClientSideMessage("No such module exists");
        } else if (!(module instanceof Toggleable)) {
        	Command.sendClientSideMessage("That module can't be toggled");
        } else {
            ToggleableModule toggleableModule = (ToggleableModule)module;
            toggleableModule.toggle();
	        Command.sendClientSideMessage(module.getLabel() + " has been set to " + toggleableModule.isRunning());    
        }
        
	}

	@Override
	public String getSyntax() {
		return "Toggle (module name)";
	}

	@Override
	public String[] getCommandAlias() {
		return new String[] {"toggle"};
	}
	

}
