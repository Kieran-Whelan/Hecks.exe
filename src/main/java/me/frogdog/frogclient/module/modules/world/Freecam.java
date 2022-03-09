package me.frogdog.frogclient.module.modules.world;

import me.frogdog.frogclient.event.Listener;
import me.frogdog.frogclient.event.events.PushOutOfBlocksEvent;
import me.frogdog.frogclient.event.events.TickEvent;
import me.frogdog.frogclient.module.ModuleType;
import me.frogdog.frogclient.module.ToggleableModule;
import me.frogdog.frogclient.properties.NumberProperty;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class Freecam extends ToggleableModule {
	private final NumberProperty<Integer> speed = new NumberProperty<Integer>(5, 0, 50, "Speed");
    private double posX, posY, posZ;
    private float pitch, yaw;

    private EntityOtherPlayerMP clonedPlayer;

    private boolean isRidingEntity;
    private Entity ridingEntity;

	public Freecam() {
		super("Freecam", new String[] {"Freecam", "freecam"}, ModuleType.WORLD);
		this.offerProperties(this.keybind);
        this.listeners.add(new Listener<TickEvent>("tick_listener"){

            @Override
            public void call(TickEvent event) {
                mc.player.capabilities.isFlying = true;
                mc.player.capabilities.setFlySpeed(speed.getValue()/100f);
                mc.player.noClip = true;
                mc.player.onGround = false;
                mc.player.fallDistance = 0;
            }
        });
        
        this.listeners.add(new Listener<PushOutOfBlocksEvent>("push_listener"){

			@Override
			public void call(PushOutOfBlocksEvent event) {
				event.setCanceled(true);
			}
        });
	}
	
    @Override
    public void onEnable() {
    	super.onEnable();
        if(mc.player != null) {
            isRidingEntity = mc.player.getRidingEntity() != null;

            if (mc.player.getRidingEntity() == null) {
                posX = mc.player.posX;
                posY = mc.player.posY;
                posZ = mc.player.posZ;
            }
            else {
                ridingEntity = mc.player.getRidingEntity();
                mc.player.dismountRidingEntity();
            }

            pitch = mc.player.rotationPitch;
            yaw = mc.player.rotationYaw;

            clonedPlayer = new EntityOtherPlayerMP(mc.world, mc.getSession().getProfile());
            clonedPlayer.copyLocationAndAnglesFrom(mc.player);
            clonedPlayer.rotationYawHead = mc.player.rotationYawHead;
            mc.world.addEntityToWorld(-100, clonedPlayer);
            mc.player.capabilities.isFlying = true;
            mc.player.capabilities.setFlySpeed(speed.getValue()/100f);
            mc.player.noClip = true;
        }
    }

    @Override
    public void onDisable() {
    	super.onDisable();
        EntityPlayer localPlayer = mc.player;
        if(localPlayer != null) {
            mc.player.setPositionAndRotation(posX, posY, posZ, yaw, pitch);
            mc.world.removeEntityFromWorld(-100);
            clonedPlayer = null;
            posX = posY = posZ = 0.D;
            pitch = yaw = 0.f;
            mc.player.capabilities.isFlying = false;
            mc.player.capabilities.setFlySpeed(0.05f);
            mc.player.noClip = false;
            mc.player.motionX = mc.player.motionY = mc.player.motionZ = 0.f;

            if (isRidingEntity) {
                mc.player.startRiding(ridingEntity, true);
            }
        }
    }

}
