package me.frogdog.frogclient.command.commands;

import me.frogdog.frogclient.Frog;
import me.frogdog.frogclient.command.Command;
import me.frogdog.frogclient.friend.Friend;

public final class RemoveFriend extends Command {

	public RemoveFriend() {
		super(new String[] {"remove", "unadd"}, "Remove (username)");
	}

	@Override
	public void onClientCommand(String command, String[] args) throws Exception {
		String username = args[0];
		if(!(Frog.getInstance().getFriendManager().isFriend(username))) {
			Command.sendClientSideMessage(username + " is not on your friends list"); 
		} else {
			Friend friend = Frog.getInstance().getFriendManager().getFriendByAliasOrLabel(username);
			Frog.getInstance().getFriendManager().unregister(friend);
			Command.sendClientSideMessage("Removed " + username + " from your friends list");
		}
		
	}

}
