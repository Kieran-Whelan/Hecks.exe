package me.frogdog.frogclient.events;

import me.frogdog.api.event.Event;
import net.minecraft.network.Packet;

public class PacketEvent
extends Event {
    private Packet packet;

    public PacketEvent(Packet packet) {
        this.packet = packet;
    }

    public Packet getPacket() {
        return this.packet;
    }

    public void setPacket(Packet packet) {
        this.packet = packet;
    }
}
