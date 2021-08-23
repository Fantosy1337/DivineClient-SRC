package me.Divine.event;

import com.darkmagician6.eventapi.events.callables.*;

public class EventPreMotionUpdates extends EventCancellable
{
    private boolean cancel;
    public float yaw;
    public float pitch;
    public double y;
    
    public EventPreMotionUpdates(final float yaw, final float pitch, final double y) {
        this.yaw = yaw;
        this.pitch = pitch;
        this.y = y;
    }
    
    public float getPitch() {
        return this.pitch;
    }
    
    public float getYaw() {
        return this.yaw;
    }
    
    public double getY() {
        return this.y;
    }
    
    public void setPitch(final float pitch) {
        this.pitch = pitch;
    }
    
    public void setYaw(final float yaw) {
        this.yaw = yaw;
    }
    
    public void setY(final double y) {
        this.y = y;
    }
    
    @Override
    public boolean isCancelled() {
        return this.cancel;
    }
    
    @Override
    public void setCancelled(final boolean state) {
        this.cancel = state;
    }
}
