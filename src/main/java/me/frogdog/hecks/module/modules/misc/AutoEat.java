package me.frogdog.hecks.module.modules.misc;

import me.frogdog.hecks.module.ModuleType;
import me.frogdog.hecks.module.ToggleableModule;
import me.frogdog.hecks.property.NumberProperty;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public final class AutoEat extends ToggleableModule {
    private final NumberProperty<Integer> hunger = new NumberProperty<>(15, 0, 20, "Health");

    public AutoEat() {
        super("AutoEat", new String[] {"AutoEat"}, ModuleType.MISC);
        this.offerProperties(this.hunger, this.keybind);
    }

    public void update(TickEvent event) {
        if (mc.player.getFoodStats().getFoodLevel() < hunger.getValue()) {
        }
    }
}
