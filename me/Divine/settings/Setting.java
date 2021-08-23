package me.Divine.settings;

import me.Divine.module.*;
import java.util.*;

public class Setting
{
    public boolean focused;
    public String name;
    private Module parent;
    private String mode;
    public String activeMode;
    public double increment;
    public double value;
    public double minimum;
    public double maximum;
    private String sval;
    private ArrayList<String> options;
    private boolean bval;
    private double dval;
    private double min;
    private double max;
    private boolean onlyint;
    
    public Setting(final String name, final Module parent, final String sval, final ArrayList<String> options) {
        this.onlyint = false;
        this.name = name;
        this.parent = parent;
        this.sval = sval;
        this.options = options;
        this.mode = "Combo";
    }
    
    public Setting(final String name, final Module parent, final boolean bval) {
        this.onlyint = false;
        this.name = name;
        this.parent = parent;
        this.bval = bval;
        this.mode = "Check";
    }
    
    public Setting(final String name, final Module parent, final double dval, final double min, final double max, final boolean onlyint) {
        this.onlyint = false;
        this.name = name;
        this.parent = parent;
        this.dval = dval;
        this.min = min;
        this.max = max;
        this.onlyint = onlyint;
        this.mode = "Slider";
    }
    
    public String getName() {
        return this.name;
    }
    
    public Module getParentMod() {
        return this.parent;
    }
    
    public String getValString() {
        return this.sval;
    }
    
    public void setValString(final String in) {
        this.sval = in;
    }
    
    public ArrayList<String> getOptions() {
        return this.options;
    }
    
    public boolean getValBoolean() {
        return this.bval;
    }
    
    public void setValBoolean(final boolean in) {
        this.bval = in;
    }
    
    public double getValDouble() {
        if (this.onlyint) {
            this.dval = (int)this.dval;
        }
        return this.dval;
    }
    
    public void setValDouble(final double in) {
        this.dval = in;
    }
    
    public double getMin() {
        return this.min;
    }
    
    public double getMax() {
        return this.max;
    }
    
    public boolean isCombo() {
        return this.mode.equalsIgnoreCase("Combo");
    }
    
    public boolean isCheck() {
        return this.mode.equalsIgnoreCase("Check");
    }
    
    public boolean isSlider() {
        return this.mode.equalsIgnoreCase("Slider");
    }
    
    public boolean onlyInt() {
        return this.onlyint;
    }
    
    public Setting(final String string, final double d, final double d2, final double d3, final double d4) {
        this.onlyint = false;
        this.name = string;
        this.value = d;
        this.minimum = d2;
        this.maximum = d3;
        this.increment = d4;
    }
    
    public double getMaximum() {
        return this.maximum;
    }
    
    public void setMaximum(final double d) {
        this.maximum = d;
    }
    
    public double getMinimum() {
        return this.minimum;
    }
    
    public void setMinimum(final double d) {
        this.minimum = d;
    }
    
    public void increment(final boolean bl) {
        this.setValue(this.getValue() + (bl ? 1 : -1) * this.increment);
    }
    
    public double getValue() {
        return this.value;
    }
    
    public void setValue(final double d) {
        final double d2 = 1.0 / this.increment;
        this.value = Math.round(Math.max(this.minimum, Math.min(this.maximum, d)) * d2) / d2;
    }
    
    public double getIncrement() {
        return this.increment;
    }
    
    public void setIncrement(final double d) {
        this.increment = d;
    }
}
