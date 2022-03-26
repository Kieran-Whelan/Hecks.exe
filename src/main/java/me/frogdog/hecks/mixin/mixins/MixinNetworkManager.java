package me.frogdog.hecks.mixin.mixins;

import me.frogdog.hecks.event.events.PacketEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.netty.channel.ChannelHandlerContext;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;

@Mixin(value = NetworkManager.class)
public class MixinNetworkManager {

    @Inject(method = "sendPacket(Lnet/minecraft/network/Packet;)V", at = @At("HEAD"), cancellable = true)
    public void onPacketSend(Packet<?> packet, CallbackInfo info) {
        //PacketEvent packetSendEvent = new PacketEvent(packet);
        //Frog.getInstance().getEventManager().dispatch(packetSendEvent);

        //if (packetSendEvent.isCanceled()) {
        //    info.cancel();
        //}
    }

    @Inject(method = "channelRead0", at = @At("HEAD"), cancellable = true)
    public void onPacketReceive(ChannelHandlerContext chc, Packet<?> packet, CallbackInfo info) {
        //PacketEvent packetReceiveEvent = new PacketEvent(packet);
        //Frog.getInstance().getEventManager().dispatch(packetReceiveEvent);

        //if (packetReceiveEvent.isCanceled()) {
        //    info.cancel();
        //}
    }
}
