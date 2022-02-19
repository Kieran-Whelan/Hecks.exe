package me.frogdog.frogclient.command;

import java.util.ArrayList;

import com.mojang.realmsclient.gui.ChatFormatting;

import me.frogdog.api.registry.ListRegistry;
import me.frogdog.frogclient.command.commands.*;

public class CommandManager extends ListRegistry<Command>{
	
    public static final CommandManager INSTANCE = new CommandManager();
	
	boolean b;
	
	public CommandManager() {
        this.registry = new ArrayList();
        
        register(new Help());
        //register(new Commands());
        register(new Toggle());
		
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
	
