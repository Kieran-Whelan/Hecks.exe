package com.frogdog.froghack.module.modules.player;

import org.lwjgl.input.Keyboard;

import com.frogdog.froghack.module.Category;
import com.frogdog.froghack.module.Module;

public class Sprint extends Module{
	
	public Sprint() {
		super("Sprint", "Auto runs when you hold down W", Category.PLAYER);
		this.setKey(Keyboard.KEY_EQUALS);
	}

}
