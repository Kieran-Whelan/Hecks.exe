package me.frogdog.frogclient.module.modules.world;

import me.frogdog.api.event.Listener;
import me.frogdog.frogclient.Frog;
import me.frogdog.frogclient.events.TickEvent;
import me.frogdog.frogclient.module.ModuleType;
import me.frogdog.frogclient.module.ToggleableModule;
import me.frogdog.frogclient.module.modules.render.Fullbright;
import me.frogdog.frogclient.module.modules.render.Fullbright.Mode;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;

public class Scaffold extends ToggleableModule {

	public Scaffold() {
		super("Scaffold", new String[] {"Scaffold", "scaffold"}, -2366720, ModuleType.WORLD);
        this.listeners.add(new Listener<TickEvent>("tick_listener"){

            @Override
            public void call(TickEvent event) {
            	BlockPos target = new BlockPos(Frog.getInstance().mc.player.posX, Frog.getInstance().mc.player.posY - 1, Frog.getInstance().mc.player.posZ);
            	if(Frog.getInstance().mc.world.getBlockState(target).getBlock().canPlaceBlockAt(Frog.getInstance().mc.world, target)) {
            		Frog.getInstance().mc.player.swingArm(EnumHand.MAIN_HAND);
            		KeyBinding.setKeyBindState(Frog.getInstance().mc.gameSettings.keyBindUseItem.getKeyCode(), true);
            	}
            }
        });
	}
	
	

}
