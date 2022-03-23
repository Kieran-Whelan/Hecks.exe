package me.frogdog.frogclient.command.commands;

import me.frogdog.frogclient.Frog;
import me.frogdog.frogclient.command.Command;
import me.frogdog.frogclient.friend.Friend;

public final class AddFriend extends Command {

	public AddFriend() {
		super(new String[] {"add"}, "Add (username)");
	}

	@Override
	public void onClientCommand(String command, String[] args) throws Exception {
		String username = args[0];
		Frog.getInstance().getFriendManager().register(new Friend(username, username));
		Command.sendClientSideMessage("Added " + username + " as a friend");
	}

}
