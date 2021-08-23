package me.Divine.event;

public class EventRender3D extends Event2
{
    public float particlTicks;
    
    public EventRender3D(final float particlTicks) {
        this.particlTicks = particlTicks;
    }
    
    public float getParticlTicks() {
        return this.particlTicks;
    }
    
    public void setParticlTicks(final float particlTicks) {
        this.particlTicks = particlTicks;
    }
}
