package me.frogdog.hecks.module.modules.misc;

import me.frogdog.hecks.command.Command;
import me.frogdog.hecks.module.ModuleType;
import me.frogdog.hecks.module.ToggleableModule;
import me.frogdog.hecks.property.NumberProperty;
import me.frogdog.hecks.util.PlayerUtil;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public final class ElytraAssist extends ToggleableModule {
    private final NumberProperty<Float> speedLimit = new NumberProperty<>(30.0f, 0f, 200f, "ReelDelay", "rd");

    private int timer = 40;

    public ElytraAssist() {
        super("ElytraAssist", new String[] {"ElytraAssist"}, "Macro that disables elytra using your hotkey", ModuleType.MISC);
        this.offerProperties(this.speedLimit, this.keybind);
    }

    @Override
    public void update(TickEvent event) {
        if (mc.player.isElytraFlying() && (float) PlayerUtil.getSpeed() <= speedLimit.getValue()) {
            timer++;
            if (timer >= 40) {
                timer = 0;
                Command.sendClientSideMessage("You are breaking the speed limit slow down!");
                mc.world.playSound(mc.player, mc.player.posX, mc.player.posY, mc.player.posZ, new SoundEvent(new ResourceLocation("ui.button.click")), SoundCategory.HOSTILE, 1, 1);
            }
        }
    }
}
