package me.Divine.event;

import net.minecraft.network.*;

public class EventPacket extends Event2
{
    private Packet<?> packet;
    private boolean outgoing;
    private boolean pre;
    
    public EventPacket(final Packet<?> packet, final boolean outgoing) {
        this.packet = packet;
        this.outgoing = outgoing;
        this.pre = true;
    }
    
    public EventPacket(final Packet<?> packet) {
        this.packet = packet;
        this.pre = false;
    }
    
    public boolean isPre() {
        return this.pre;
    }
    
    public boolean isPost() {
        return !this.pre;
    }
    
    public Packet getPacket() {
        return this.packet;
    }
    
    public void setPacket(final Packet packet) {
        this.packet = (Packet<?>)packet;
    }
    
    public boolean isOutgoing() {
        return this.outgoing;
    }
    
    public boolean isIncoming() {
        return !this.outgoing;
    }
}
