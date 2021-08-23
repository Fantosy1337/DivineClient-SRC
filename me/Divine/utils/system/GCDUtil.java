package me.Divine.utils.system;

import net.minecraft.entity.player.*;
import net.minecraft.client.*;

public class GCDUtil implements MinecraftHelper
{
    public float yaw;
    public float pitch;
    
    public GCDUtil(final float yaw, final float pitch) {
        this.yaw = yaw;
        this.pitch = pitch;
    }
    
    public static float getFixedRotation(final float rot) {
        return getDeltaMouse(rot) * getGCDValue();
    }
    
    public static float getGCDValue() {
        return (float)(getGCD() * 0.15);
    }
    
    public static float getGCD() {
        final float f1 = (float)(GCDUtil.mc.field_71474_y.field_74341_c * 0.6 + 0.2);
        return f1 * f1 * f1 * 8.0f;
    }
    
    public static float getDeltaMouse(final float delta) {
        return (float)Math.round(delta / getGCDValue());
    }
    
    public static GCDUtil copy$default(final GCDUtil var0, float var1, float var2, final int var3, final Object var4) {
        if ((var3 & 0x1) != 0x0) {
            var1 = var0.yaw;
        }
        if ((var3 & 0x2) != 0x0) {
            var2 = var0.pitch;
        }
        return var0.copy(var1, var2);
    }
    
    public final void toPlayer(final EntityPlayer player) {
        float var2 = this.yaw;
        boolean var3 = false;
        if (!Float.isNaN(var2)) {
            var2 = this.pitch;
            var3 = false;
            if (!Float.isNaN(var2)) {
                this.fixedSensitivity(Minecraft.func_71410_x().field_71474_y.field_74341_c);
                player.field_70177_z = this.yaw;
                player.field_70125_A = this.pitch;
            }
        }
    }
    
    public void fixedSensitivity(final float sensitivity) {
        final float f = sensitivity * 0.6f + 0.2f;
        final float gcd = f * f * f * 1.2f;
        this.yaw -= this.yaw % gcd;
        this.pitch -= this.pitch % gcd;
    }
    
    public final float getYaw() {
        return this.yaw;
    }
    
    public final void setYaw(final float var1) {
        this.yaw = var1;
    }
    
    public final float getPitch() {
        return this.pitch;
    }
    
    public final void setPitch(final float var1) {
        this.pitch = var1;
    }
    
    public final float component1() {
        return this.yaw;
    }
    
    public final float component2() {
        return this.pitch;
    }
    
    public final GCDUtil copy(final float yaw, final float pitch) {
        return new GCDUtil(yaw, pitch);
    }
    
    @Override
    public String toString() {
        return "Rotation(yaw=" + this.yaw + ", pitch=" + this.pitch + ")";
    }
    
    @Override
    public int hashCode() {
        return Float.hashCode(this.yaw) * 31 + Float.hashCode(this.pitch);
    }
    
    @Override
    public boolean equals(final Object var1) {
        if (this == var1) {
            return true;
        }
        if (var1 instanceof GCDUtil) {
            final GCDUtil var2 = (GCDUtil)var1;
            return Float.compare(this.yaw, var2.yaw) == 0 && Float.compare(this.pitch, var2.pitch) == 0;
        }
        return false;
    }
}
