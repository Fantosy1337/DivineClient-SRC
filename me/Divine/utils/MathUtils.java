package me.Divine.utils;

import java.math.*;
import net.minecraft.util.math.*;

public class MathUtils
{
    public static int getMiddle(final int i, final int j) {
        return (i + j) / 2;
    }
    
    public static double getMiddleDouble(final int i, final int j) {
        return (i + (double)j) / 2.0;
    }
    
    public static double round(final double value, final int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
    
    public static float getAngleDifference(final float direction, final float rotationYaw) {
        final float phi = Math.abs(rotationYaw - direction) % 360.0f;
        final float distance = (phi > 180.0f) ? (360.0f - phi) : phi;
        return distance;
    }
    
    public static int clamp(final int num, final int min, final int max) {
        return (num < min) ? min : ((num > max) ? max : num);
    }
    
    public static float clamp(final float num, final float min, final float max) {
        return (num < min) ? min : ((num > max) ? max : num);
    }
    
    public static double clamp(final double num, final double min, final double max) {
        return (num < min) ? min : ((num > max) ? max : num);
    }
    
    public static int floor(final float value) {
        return MathHelper.func_76141_d(value);
    }
    
    public static int floor(final double value) {
        return MathHelper.func_76128_c(value);
    }
    
    public static int ceil(final float value) {
        return MathHelper.func_76123_f(value);
    }
    
    public static int ceil(final double value) {
        return MathHelper.func_76143_f(value);
    }
    
    public static float sin(final float value) {
        return MathHelper.func_76126_a(value);
    }
    
    public static float cos(final float value) {
        return MathHelper.func_76134_b(value);
    }
    
    public static float wrapDegrees(final float value) {
        return MathHelper.func_76142_g(value);
    }
    
    public static double wrapDegrees(final double value) {
        return MathHelper.func_76138_g(value);
    }
}
