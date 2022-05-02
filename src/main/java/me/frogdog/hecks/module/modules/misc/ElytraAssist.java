package me.frogdog.hecks.module.modules.misc;

import me.frogdog.hecks.module.ModuleType;
import me.frogdog.hecks.module.ToggleableModule;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;

public final class ElytraAssist extends ToggleableModule {

    public ElytraAssist() {
        super("ElytraAssist", new String[] {"ElytraAssist"}, "Macro that disables elytra using your hotkey", ModuleType.MISC);
        this.offerProperties(this.keybind);
    }

    @Override
    public void update(TickEvent event) {
        if (mc.player.isInWater()) {
            mc.player.sendChatMessage("'toggle ElytraFlight");
            mc.player.sendChatMessage(".toggle Flight off");
            toggle();
        }
    }
}
