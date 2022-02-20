package me.frogdog.frogclient.module.impl.toggle.movement;

import me.frogdog.api.event.Listener;
import me.frogdog.frogclient.Frog;
import me.frogdog.frogclient.events.GammaSettingEvent;
import me.frogdog.frogclient.events.InputEvent;
import me.frogdog.frogclient.events.TickEvent;
import me.frogdog.frogclient.module.ModuleType;
import me.frogdog.frogclient.module.ToggleableModule;
import me.frogdog.frogclient.module.impl.toggle.render.Fullbright;
import me.frogdog.frogclient.module.impl.toggle.render.Fullbright.Mode;
import me.frogdog.frogclient.properties.EnumProperty;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;

public final class Sprint extends ToggleableModule {
    private final EnumProperty<Mode> mode = new EnumProperty<Mode>(Mode.POTION, "Mode", "m");

    public Sprint() {
        super("Sprint", new String[]{"sprint", "Sprint"}, -7895677, ModuleType.MOVEMENT);
        this.listeners.add(new Listener<TickEvent>("tick_listener"){

            @Override
            public void call(TickEvent event) {
            	if(!Frog.getInstance().mc.player.isSprinting()) {
            		Frog.getInstance().mc.player.setSprinting(true);
            	}
            }
        });
    }
}
