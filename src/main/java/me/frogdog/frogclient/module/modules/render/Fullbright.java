package me.frogdog.frogclient.module.modules.render;

import me.frogdog.api.event.Listener;
import me.frogdog.frogclient.Frog;
import me.frogdog.frogclient.events.TickEvent;
import me.frogdog.frogclient.keybind.Keybind;
import me.frogdog.frogclient.module.ModuleType;
import me.frogdog.frogclient.module.ToggleableModule;
import me.frogdog.frogclient.properties.EnumProperty;
import me.frogdog.frogclient.properties.NumberProperty;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public final class Fullbright extends ToggleableModule {
    private final EnumProperty<Mode> mode = new EnumProperty<Mode>(Mode.POTION, "Mode", "m");

    public Fullbright() {
    	super("Fulbright", new String[]{"fullbright", "Fullbright", "fb"}, -2366720, ModuleType.RENDER);
        this.offerProperties(this.mode);
        this.listeners.add(new Listener<TickEvent>("tick_listener"){

            @Override
            public void call(TickEvent event) {
            	if(Fullbright.this.mode.getValue() == Mode.GAMMA) {
            		Frog.getInstance().mc.gameSettings.gammaSetting = 800f;
            	} else if(Fullbright.this.mode.getValue() == Mode.POTION) {
            		Frog.getInstance().mc.player.addPotionEffect(new PotionEffect(Potion.getPotionById(16), 5, 0));
            	}
            }
        });
        
    }

    public enum Mode {
        GAMMA,
        POTION;

    }
}

