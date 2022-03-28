package me.frogdog.hecks.module.modules.combat;

import me.frogdog.hecks.module.ModuleType;
import me.frogdog.hecks.module.ToggleableModule;
import me.frogdog.hecks.property.NumberProperty;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public final class AutoLog extends ToggleableModule {
    private final NumberProperty<Integer> health = new NumberProperty<>(8, 0, 20, "Health");

    public AutoLog() {
        super("AutoLog", new String[] {"AutoLog"}, ModuleType.COMBAT);
        this.offerProperties(this.health, this.keybind);
    }

    public void update(TickEvent event) {
        if (mc.player.getHealth() < health.getValue()) {
            //mc.getConnection().onDisconnect();
            mc.world.sendQuittingDisconnectingPacket();
        }
    }
}
