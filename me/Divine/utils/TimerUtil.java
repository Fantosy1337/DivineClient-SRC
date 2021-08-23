package me.Divine.utils;

public class TimerUtil
{
    private long previousMS;
    
    public TimerUtil() {
        this.previousMS = 0L;
    }
    
    public void reset() {
        this.previousMS = this.getTime();
    }
    
    public boolean hasReached(final double d) {
        return this.getTime() - this.previousMS >= d;
    }
    
    public long getTime() {
        return System.nanoTime() / 1000000L;
    }
}
