package me.frogdog.hecks.ui.hud.components;

import me.frogdog.hecks.Hecks;
import me.frogdog.hecks.module.Module;
import me.frogdog.hecks.module.ToggleableModule;
import me.frogdog.hecks.ui.hud.HudComponent;
import me.frogdog.hecks.util.comparator.ModuleComparator;

import java.util.ArrayList;
import java.util.Collections;

public final class ModuleArray extends HudComponent {

    private ArrayList<String> moduleArray = new ArrayList<String>();

    public ModuleArray() {
        super("moduleArray", 0, 0);
    }

    @Override
    public ArrayList<String> getComponent() {
        moduleArray.clear();
        for (Module m : Hecks.getInstance().getModuleManager().getRegistry()) {
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
