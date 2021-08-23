package me.Divine.event;

public abstract class Event2
{
    private boolean cancelled;
    
    public boolean isCancelled() {
        return this.cancelled;
    }
    
    public void setCancelled(final boolean cancelled) {
        this.cancelled = cancelled;
    }
    
    public enum State
    {
        PRE("PRE", 0), 
        POST("POST", 1);
        
        private State(final String string, final int number) {
        }
    }
}
