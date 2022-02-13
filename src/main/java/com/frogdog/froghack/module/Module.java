package com.frogdog.froghack.module;

import net.minecraftforge.common.MinecraftForge;

public class Module {
	
	public String name;
	public String description;
	private Category category;
	public boolean toggled;
	private int key;
	
	
	public Module(String name, String description, Category category) {
	super();
	this.name = name;
	this.description = description;
	this.key = 0;
	this.category = category;
	this.toggled = false;
	
	}
	
	public boolean isToggled() {
		return toggled;
	}

	public void setToggled(boolean toggled) {
		this.toggled = toggled;
		
		if(this.toggled) {
			this.onEnable();
		} else {
			this.onDisable();
		}
	}
	
	public void toggle() {
		this.toggled = !this.toggled;
		
		if(this.toggled) {
			this.onEnable();
		} else {
			this.onDisable();
		}
	}

	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	public void onEnable() {
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	public void onDisable() {
		MinecraftForge.EVENT_BUS.unregister(this);
	}
	
	public void onUpdate() {}
	
	
	
	
	

}
