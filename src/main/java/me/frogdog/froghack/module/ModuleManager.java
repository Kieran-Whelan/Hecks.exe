package me.frogdog.froghack.module;

import java.util.ArrayList;
import java.util.List;

import me.frogdog.froghack.module.modules.client.*;
import me.frogdog.froghack.module.modules.combat.*;
import me.frogdog.froghack.module.modules.exploit.*;
import me.frogdog.froghack.module.modules.player.*;
import me.frogdog.froghack.module.modules.render.*;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderWorldLastEvent;

public class ModuleManager {
	
	public static ArrayList<Module> modules;
	
	public ModuleManager() {
		(modules = new ArrayList<Module>()).clear();
		//Combat
		//Exploit
		//Player
		modules.add(new Sprint());
		modules.add(new AutoWalk());
		modules.add(new Scaffold());
		//Render
		modules.add(new NoRender());
		//Client
		modules.add(new FullBright());
	}
	
	public Module getModule (String name) {
		for(Module m : modules) {
			if(m.getName().equalsIgnoreCase(name)) {
				return m;
			}
		}
		return null;
	}
	
	public ArrayList<Module> getModuleList() {
		return modules;
	}
	
	public static List<Module> getModulesByCategory(Category c) {
		List<Module> modules = new ArrayList<Module>();
		
		for(Module m : modules) {
			if(m.getCategory() == c)
				modules.add(m);
		}
		return modules;
	}
	
	public static void onUpdate() {
		modules.stream().filter(Module::isToggled).forEach(Module::onUpdate);
	}
	
}