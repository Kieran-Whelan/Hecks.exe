package me.frogdog.frogclient.ui.components;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import me.frogdog.frogclient.Frog;
import me.frogdog.frogclient.module.Module;
import me.frogdog.frogclient.module.ToggleableModule;
import me.frogdog.frogclient.ui.HudComponent;
import me.frogdog.frogclient.util.minecraft.render.FontUtil;

public class ModuleArray extends HudComponent {
	
	private ArrayList<String> moduleArray = new ArrayList<String>();

	public ModuleArray() {
		super("moduleArray", true, 0, 0);
	}
	
	public static class ModuleComparator implements Comparator<String> {

		@Override
		public int compare(String m1, String m2) {
			if (FontUtil.getStringWidth(m1) > FontUtil.getStringWidth(m2)) {
				return -1;
			}
			if (FontUtil.getStringWidth(m1) > FontUtil.getStringWidth(m2)) {
				return 1;
			}
			return 0;
		}
		
	}
	

	@Override
	public ArrayList<String> getComponent() {
		moduleArray.clear();
			for (Module m : Frog.getInstance().getModuleManager().getRegistry()) {
				if (m instanceof ToggleableModule) {
		            ToggleableModule toggleableModule = (ToggleableModule)m;
		            if (toggleableModule.isRunning()) {
		            	moduleArray.add(m.getLabel());
		            }
				}
			}
			
		Collections.sort(moduleArray, new ModuleComparator());
		return moduleArray;
	}

}
