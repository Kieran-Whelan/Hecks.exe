package me.frogdog.froghack.module;

import me.frogdog.froghack.event.events.Render2DEvent;
import me.frogdog.froghack.event.events.Render3DEvent;
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
			MinecraftForge.EVENT_BUS.register(this);
		} else {
			MinecraftForge.EVENT_BUS.unregister(this);
		}
	}
	
	public void toggle() {
		this.toggled = !this.toggled;
		
		if(this.toggled) {
			this.enable();
		} else {
			this.disable();
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
	
	public void enable() {
		onEnable();
		setToggled(true);
	}
	
	public void disable() {
		onDisable();
		setToggled(false);
	}
	
	public void onEnable() {}
	
	public void onDisable() {}
	
	public void onUpdate() {}

	public void onRender2D(Render2DEvent event) {}

	public void onRender3D(Render3DEvent event) {}
	
}
