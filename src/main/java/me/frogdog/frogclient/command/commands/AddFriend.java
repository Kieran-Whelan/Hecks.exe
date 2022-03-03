package me.frogdog.frogclient.command.commands;

import me.frogdog.frogclient.Frog;
import me.frogdog.frogclient.command.Command;
import me.frogdog.frogclient.friend.Friend;

public class AddFriend extends Command {

	@Override
	public void onClientCommand(String command, String[] args) throws Exception {
		String username = args[0];
		Frog.getInstance().getFriendManager().register(new Friend(username, username));
		Command.sendClientSideMessage("Added " + username + " as a friend");
		
	}

	@Override
	public String getClientSyntax() {
		return "Add (username)";
	}

	@Override
	public String[] getClientAlias() {
		return new String[] {"add"};
	}

}
