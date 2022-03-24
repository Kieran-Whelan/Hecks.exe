package me.frogdog.frogclient.module.modules.render;

import me.frogdog.frogclient.event.Event;
import me.frogdog.frogclient.event.Listener;
import me.frogdog.frogclient.event.events.RenderEvent;
import me.frogdog.frogclient.module.ModuleType;
import me.frogdog.frogclient.module.ToggleableModule;
import me.frogdog.frogclient.properties.EnumProperty;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public final class Fullbright extends ToggleableModule {
    private final EnumProperty<Mode> mode = new EnumProperty<Mode>(Mode.POTION, "Mode", "m");

    public Fullbright() {
    	super("Fullbright", new String[]{"fullbright", "Fullbright", "fb"}, ModuleType.RENDER);
        this.offerProperties(this.mode, this.keybind);
        this.listeners.add(new Listener<RenderEvent>("render_listener"){

            @Override
            public void call(RenderEvent event) {
            	if(Fullbright.this.mode.getValue() == Mode.GAMMA) {
            		mc.gameSettings.gammaSetting = 800f;
            	} else if(Fullbright.this.mode.getValue() == Mode.POTION) {
            		mc.player.addPotionEffect(new PotionEffect(Potion.getPotionById(16), 5, 0));
            	}
            }
        });
        
    }

    public enum Mode {
        GAMMA,
        POTION
    }

}

