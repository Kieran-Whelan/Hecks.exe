package me.frogdog.frogclient.module.modules.combat;

import me.frogdog.api.event.Listener;
import me.frogdog.frogclient.Frog;
import me.frogdog.frogclient.events.PacketEvent;
import me.frogdog.frogclient.module.Module;
import me.frogdog.frogclient.module.ModuleType;
import me.frogdog.frogclient.module.ToggleableModule;

public final class AntiAim extends ToggleableModule {
    public AntiAim() {
        super("Anti Aim", new String[]{"antiaim", "aa"}, -785690, ModuleType.COMBAT);
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

