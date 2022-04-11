package me.frogdog.hecks.module.modules.combat;

import me.frogdog.hecks.event.events.InputEvent;
import me.frogdog.hecks.module.ModuleType;
import me.frogdog.hecks.module.ToggleableModule;
import net.minecraft.util.EnumHand;

public final class Trigger extends ToggleableModule {

    public Trigger() {
        super("Trigger", new String[] {"Trigger", "AutoClicker"}, ModuleType.COMBAT);
        this.offerProperties(this.keybind);
    }

    @Override
    public void input(me.frogdog.hecks.event.events.InputEvent event) {
        if (event.getType() == InputEvent.Type.MOUSE_LEFT_CLICK) {
            mc.player.jump();
        }
    }

}
