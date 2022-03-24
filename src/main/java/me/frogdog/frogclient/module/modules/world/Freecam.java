package me.frogdog.frogclient.module.modules.world;

import me.frogdog.frogclient.event.Event;
import me.frogdog.frogclient.event.Listener;
import me.frogdog.frogclient.event.events.PacketEvent;
import me.frogdog.frogclient.event.events.TickEvent;
import me.frogdog.frogclient.module.ModuleType;
import me.frogdog.frogclient.module.ToggleableModule;
import me.frogdog.frogclient.properties.NumberProperty;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.network.play.client.CPacketPlayer;

public final class Freecam extends ToggleableModule {
	private final NumberProperty<Integer> speed = new NumberProperty<Integer>(5, 0, 50, "Speed");

    double startX, startY, startZ;
    EntityOtherPlayerMP entity;

    private EntityOtherPlayerMP clonedPlayer;

    private boolean isRidingEntity;
    private Entity ridingEntity;

	public Freecam() {
		super("Freecam", new String[] {"Freecam", "freecam"}, ModuleType.WORLD);
		this.offerProperties(this.keybind);
        this.listeners.add(new Listener<TickEvent>("tick_listener") {

            @Override
            public void call(TickEvent event) {
                mc.player.noClip = true;
            }

        });

        this.listeners.add(new Listener<PacketEvent.Send>("packet_listener") {

            @Override
            public void call(PacketEvent.Send event) {
                if (event.getPacket() instanceof CPacketPlayer.Position) {
                    event.setCanceled(true);
                }

                if (event.getPacket() instanceof CPacketPlayer.Rotation) {
                    event.setCanceled(true);
                }

                if (event.getPacket() instanceof CPacketPlayer.PositionRotation) {
                    event.setCanceled(true);
                }
            }

        });
	}

    @Override
    public void onEnable() {
    	super.onEnable();
        entity = new EntityOtherPlayerMP(mc.world, mc.getSession().getProfile());
        entity.copyLocationAndAnglesFrom(mc.player);
        entity.rotationYaw = mc.player.rotationYaw;
        entity.rotationYawHead = mc.player.rotationYawHead;
        mc.world.addEntityToWorld(696984837, entity);
        mc.player.capabilities.allowFlying = true;
        startX = mc.player.posX;
        startY = mc.player.posZ;
        startZ = mc.player.posZ;

    }

    @Override
    public void onDisable() {
    	super.onDisable();
        mc.player.noClip = false;
        mc.player.capabilities.allowFlying = false;
        mc.player.capabilities.isFlying = false;
        mc.world.removeEntity(entity);
    }

}
