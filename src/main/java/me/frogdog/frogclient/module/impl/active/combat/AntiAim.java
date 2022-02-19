package me.frogdog.frogclient.module.impl.active.combat;

import me.frogdog.api.event.Listener;
import me.frogdog.frogclient.Frog;
import me.frogdog.frogclient.events.PacketEvent;
import me.frogdog.frogclient.module.Module;

public final class AntiAim
extends Module {
    public AntiAim() {
        super("Anti Aim", new String[]{"antiaim", "aa"});
        Frog.getInstance().getEventManager().register(new Listener<PacketEvent>("anti_aim_packet_listener"){

            @Override
            public void call(PacketEvent event) {
//                if (event.getPacket() instanceof SPacketPlayerPosLook) {
//                    SPacketPlayerPosLook packet = (SPacketPlayerPosLook)event.getPacket();
//                    if (((AntiAim)AntiAim.this).minecraft.player.rotationYaw != -180.0f && ((AntiAim)AntiAim.this).minecraft.player.rotationPitch != 0.0f) {
//                        packet.yaw(((AntiAim)AntiAim.this).minecraft.player.rotationYaw);
//                        packet.pitch(((AntiAim)AntiAim.this).minecraft.player.rotationPitch);
//                    }
//                }
            }
        });
    }
}

