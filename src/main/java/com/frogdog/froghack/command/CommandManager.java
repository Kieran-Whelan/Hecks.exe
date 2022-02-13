package com.frogdog.froghack.command;

import java.util.ArrayList;

import com.mojang.realmsclient.gui.ChatFormatting;

public class CommandManager {
	public static ArrayList<Command> commands;
	boolean b;
	
	public static void addCommand(Command c) {
		commands.add(c);
	}
	
	public static ArrayList<Command> getClientCommands() {
		return commands;
	}
	
    public void callClientCommand(String input){
        String[] split = input.split(" (?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
        String command = split[0];
        String args = input.substring(command.length()).trim();
        b = false;
        commands.forEach(c ->{
            for(String s : c.getClientAlias()) {
                if (s.equalsIgnoreCase(command)) {
                    b = true;
                    try {
                        c.onClientCommand(args, args.split(" (?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"));
                    } catch (Exception e) {
                        if (!s.equalsIgnoreCase("friend")) {
                            Command.sendClientSideMessage(ChatFormatting.RED + c.getClientSyntax());
                        }

                    }
                }
            }
        });
        if(!b) Command.sendClientSideMessage(ChatFormatting.RED + "Unknown command!");
    }

}
