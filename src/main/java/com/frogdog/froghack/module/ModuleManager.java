package com.frogdog.froghack.module;

import java.util.ArrayList;
import java.util.List;

import com.frogdog.froghack.Frog;
import com.frogdog.froghack.module.modules.player.Sprint;

public class ModuleManager {
	
	public ArrayList<Module> modules;
	
	public ModuleManager() {
		(modules = new ArrayList<Module>()).clear();
		//Combat
		//Exploit
		//Player
		this.modules.add(new Sprint());
		//Render
		//Client
	}
	
	public Module getModule (String name) {
		for(Module m : this.modules) {
			if(m.getName().equalsIgnoreCase(name)) {
				return m;
			}
		}
		return null;
	}
	
	public ArrayList<Module> getModuleList() {
		return this.modules;
	}
	
	public static List<Module> getModulesByCategory(Category c) {
		List<Module> modules = new ArrayList<Module>();
		
		for(Module m : Frog.moduleManager.modules) {
			if(m.getCategory() == c)
				modules.add(m);
		}
		return modules;
	}
}
