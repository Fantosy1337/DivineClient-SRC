package me.Divine.event;

import com.darkmagician6.eventapi.events.callables.*;
import net.minecraft.network.*;

public class EventPacketRecieve extends EventCancellable
{
    private Packet packet;
    
    public EventPacketRecieve(final Packet packet) {
        this.packet = packet;
    }
    
    public Packet getPacket() {
        return this.packet;
    }
}
