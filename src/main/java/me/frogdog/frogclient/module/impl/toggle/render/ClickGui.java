package me.frogdog.frogclient.module.impl.toggle.render;

import me.frogdog.frogclient.module.ModuleType;
import me.frogdog.frogclient.module.ToggleableModule;

public final class ClickGui extends ToggleableModule {
    public ClickGui() {
        super("Click Gui", new String[]{"clickgui"}, ModuleType.RENDER);
    }

    @Override
    protected void onEnable() {
        super.onEnable();
        this.minecraft.displayGuiScreen(me.frogdog.frogclient.module.impl.toggle.render.clickgui.ClickGui.getClickGui());
        this.setRunning(false);
    }
}

