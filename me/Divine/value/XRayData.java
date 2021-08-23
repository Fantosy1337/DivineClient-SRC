package me.Divine.value;

public class XRayData
{
    private int id;
    private int meta;
    private int red;
    private int green;
    private int blue;
    
    public XRayData(final int id, final int meta, final int red, final int green, final int blue) {
        this.id = id;
        this.meta = meta;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }
    
    public int getId() {
        return this.id;
    }
    
    public int getMeta() {
        return this.meta;
    }
    
    public void setId(final int id) {
        this.id = id;
    }
    
    public int getRed() {
        return this.red;
    }
    
    public void setRed(final int red) {
        this.red = red;
    }
    
    public int getGreen() {
        return this.green;
    }
    
    public void setGreen(final int green) {
        this.green = green;
    }
    
    public int getBlue() {
        return this.blue;
    }
    
    public void setBlue(final int blue) {
        this.blue = blue;
    }
}
