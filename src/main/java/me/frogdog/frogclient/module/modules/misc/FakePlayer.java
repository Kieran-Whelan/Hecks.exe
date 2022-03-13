package me.frogdog.frogclient.module.modules.misc;

import java.util.UUID;

import com.mojang.authlib.GameProfile;

import me.frogdog.frogclient.module.ModuleType;
import me.frogdog.frogclient.module.ToggleableModule;
import net.minecraft.client.entity.EntityOtherPlayerMP;

public final class FakePlayer extends ToggleableModule {

	public FakePlayer() {
		super("FakePlayer", new String[] {"FakePlayer", "fakeplayer"}, ModuleType.MISCELLANEOUS);
		this.offerProperties(this.keybind);          
	}
	
    @Override
    public void onEnable() {
    	super.onEnable();
    	EntityOtherPlayerMP fakePlayer = new EntityOtherPlayerMP(mc.world, new GameProfile(UUID.fromString("0f75a81d-70e5-43c5-b892-f33c524284f2"), mc.player.getName() + "'s Clone"));
        fakePlayer.copyLocationAndAnglesFrom(mc.player);
        mc.world.addEntityToWorld(-100, fakePlayer);
    }

    @Override
    public void onDisable() {
    	super.onDisable();
        mc.world.removeEntityFromWorld(-100);
    }
}
