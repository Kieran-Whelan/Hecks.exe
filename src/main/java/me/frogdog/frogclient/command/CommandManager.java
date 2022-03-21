package me.frogdog.frogclient.command;

import java.util.ArrayList;

import com.mojang.realmsclient.gui.ChatFormatting;

import me.frogdog.frogclient.command.commands.*;
import me.frogdog.frogclient.util.registry.ListRegistry;

public class CommandManager extends ListRegistry<Command>{
	
    public static final CommandManager INSTANCE = new CommandManager();
	
	boolean b;
	
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
	    b = false;
	    getRegistry().forEach(c ->{
	        for(String s : c.getClientAlias()) {
	            if (s.equalsIgnoreCase(command)) {
	                b = true;
	                try {
	                    c.onClientCommand(args, args.split(" (?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"));
	                } catch (Exception e) {
	                    if (!s.equalsIgnoreCase("friend")) {
	                        Command.sendClientSideMessage(ChatFormatting.GREEN + c.getClientSyntax());
	                    }
	
	                }
	            }
	        }
	    });
	    if(!b) Command.sendClientSideMessage(ChatFormatting.GREEN + "Unknown command!");
	}
	
}
	
