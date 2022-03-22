package me.frogdog.frogclient.command;

import java.util.ArrayList;

import com.mojang.realmsclient.gui.ChatFormatting;

import me.frogdog.frogclient.command.commands.*;
import me.frogdog.frogclient.util.registry.ListRegistry;

public class CommandManager extends ListRegistry<Command>{

	private boolean found;

	public CommandManager() {
        this.registry = new ArrayList<Command>();
        
        register(new Help());
        register(new Commands());
        register(new Toggle());
        register(new HudToggle());
        register(new Bind());
        register(new AddFriend());
        register(new RemoveFriend());
		
	}
	
	public void callClientCommand(String input){
	    String[] split = input.split(" (?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
	    String command = split[0];
	    String args = input.substring(command.length()).trim();
		found = false;
	    this.getRegistry().forEach(c ->{
	        for(String s : c.getCommandAlias()) {
	            if (s.equalsIgnoreCase(command)) {
					found = true;
	                try {
	                    c.onClientCommand(args, args.split(" (?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"));
	                } catch (Exception e) {
	                        Command.sendClientSideMessage(ChatFormatting.GREEN + c.getSyntax());
	                }
	            }
	        }
	    });
		if (!found) {
			Command.sendClientSideMessage(ChatFormatting.GREEN + "Unknown command!");
		}

	}
	
}
	
