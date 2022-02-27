package me.frogdog.frogclient.module.modules.render;

import me.frogdog.frogclient.module.ModuleType;
import me.frogdog.frogclient.module.ToggleableModule;

public final class ClickGui extends ToggleableModule {
    public ClickGui() {
        super("ClickGui", new String[]{"clickgui"}, ModuleType.RENDER);
    }

    @Override
    public void onEnable() {
        super.onEnable();
        this.mc.displayGuiScreen(me.frogdog.frogclient.module.modules.render.clickgui.ClickGui.getClickGui());
        this.setRunning(false);
    }
}

