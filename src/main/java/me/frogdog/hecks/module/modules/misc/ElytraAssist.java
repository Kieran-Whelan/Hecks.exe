package me.frogdog.hecks.module.modules.misc;

import me.frogdog.hecks.module.ModuleType;
import me.frogdog.hecks.module.ToggleableModule;
import me.frogdog.hecks.util.PlayerUtil;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public final class ElytraAssist extends ToggleableModule {

    public ElytraAssist() {
        super("ElytraAssist", new String[] {"ElytraAssist"}, "Macro that disables elytra using your hotkey", ModuleType.MISC);
        this.offerProperties(this.keybind);
    }

    @Override
    public void update(TickEvent event) {
        if (mc.player.isElytraFlying() && PlayerUtil.getSpeed() <= 40) {
            mc.player.sendChatMessage(".toggle Flight");
            mc.player.sendChatMessage("'toggle ElytraFlight");
            toggle();
        }
    }
}
