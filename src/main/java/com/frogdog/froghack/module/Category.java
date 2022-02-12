package com.frogdog.froghack.module;

public enum Category {
	COMBAT("Combat"), EXPLOITS("Exploits"), RENDER("World"), PLAYER("Player"), CLIENT("Client");
	
	public String name;
	public int moduleIndex;
	
	Category(String name) {
		this.name = name;
	}
}
