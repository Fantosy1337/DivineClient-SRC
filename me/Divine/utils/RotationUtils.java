package me.Divine.utils;

import java.util.*;
import net.minecraft.client.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.*;

public class RotationUtils
{
    public static float[] getAverageRotations(final List list) {
        double d = 0.0;
        double d2 = 0.0;
        double d3 = 0.0;
        for (final Object entityw : list) {
            final Entity entity = (Entity)entityw;
            d += entity.field_70165_t;
            d2 += entity.func_174813_aQ().field_72337_e - 2.0;
            d3 += entity.field_70161_v;
        }
        final float[] array = new float[2];
        final int n = 0;
        d /= list.size();
        d3 /= list.size();
        array[0] = getRotationFromPosition(d, d3, d2 /= list.size())[0];
        array[1] = getRotationFromPosition(d, d3, d2)[1];
        return array;
    }
    
    public static float getDistanceBetweenAngles(final float f, final float f2) {
        float f3 = Math.abs(f - f2) % 360.0f;
        if (f3 > 180.0f) {
            f3 = 360.0f - f3;
        }
        return f3;
    }
    
    public static float getTrajAngleSolutionLow(final float f, final float f2, final float f3) {
        final float f4 = f3 * f3 * f3 * f3 - 0.006f * (0.006f * (f * f) + 2.0f * f2 * (f3 * f3));
        return (float)Math.toDegrees(Math.atan((f3 * f3 - Math.sqrt(f4)) / (0.006f * f)));
    }
    
    public static float[] getRotationFromPosition(final double d, final double d2, final double d3) {
        final double d4 = d - Minecraft.func_71410_x().field_71439_g.field_70165_t;
        final double d5 = d2 - Minecraft.func_71410_x().field_71439_g.field_70161_v;
        final double d6 = d3 - Minecraft.func_71410_x().field_71439_g.field_70163_u - 0.6;
        final double d7 = MathHelper.func_76133_a(d4 * d4 + d5 * d5);
        final float f = (float)(Math.atan2(d5, d4) * 180.0 / 3.141592653589793) - 90.0f;
        final float f2 = (float)(-(Math.atan2(d6, d7) * 180.0 / 3.141592653589793));
        return new float[] { f, f2 };
    }
    
    public static float[] getNeededRotations(final Entity entityLivingBase) {
        final double d = entityLivingBase.field_70165_t - Minecraft.func_71410_x().field_71439_g.field_70165_t;
        final double d2 = entityLivingBase.field_70161_v - Minecraft.func_71410_x().field_71439_g.field_70161_v;
        final double d3 = entityLivingBase.field_70163_u + entityLivingBase.func_70047_e() - (Minecraft.func_71410_x().field_71439_g.func_174813_aQ().field_72338_b + (Minecraft.func_71410_x().field_71439_g.func_174813_aQ().field_72337_e - Minecraft.func_71410_x().field_71439_g.func_174813_aQ().field_72338_b));
        final double d4 = MathHelper.func_76133_a(d * d + d2 * d2);
        final float f = (float)(MathHelper.func_181159_b(d2, d) * 180.0 / 3.141592653589793) - 90.0f;
        final float f2 = (float)(-(MathHelper.func_181159_b(d3, d4) * 180.0 / 3.141592653589793));
        return new float[] { f, f2 };
    }
    
    public static float[] getRotations(final EntityLivingBase entityLivingBase, final String string) {
        if (string == "Head") {
            final double d = entityLivingBase.field_70165_t;
            final double d2 = entityLivingBase.field_70161_v;
            final double d3 = entityLivingBase.field_70163_u + entityLivingBase.func_70047_e() / 2.0f;
            return getRotationFromPosition(d, d2, d3);
        }
        if (string == "Chest") {
            final double d = entityLivingBase.field_70165_t;
            final double d4 = entityLivingBase.field_70161_v;
            final double d5 = entityLivingBase.field_70163_u + entityLivingBase.func_70047_e() / 2.0f - 0.75;
            return getRotationFromPosition(d, d4, d5);
        }
        if (string == "Dick") {
            final double d = entityLivingBase.field_70165_t;
            final double d6 = entityLivingBase.field_70161_v;
            final double d7 = entityLivingBase.field_70163_u + entityLivingBase.func_70047_e() / 2.0f - 1.2;
            return getRotationFromPosition(d, d6, d7);
        }
        if (string == "Legs") {
            final double d = entityLivingBase.field_70165_t;
            final double d8 = entityLivingBase.field_70161_v;
            final double d9 = entityLivingBase.field_70163_u + entityLivingBase.func_70047_e() / 2.0f - 1.5;
            return getRotationFromPosition(d, d8, d9);
        }
        final double d = entityLivingBase.field_70165_t;
        final double d10 = entityLivingBase.field_70161_v;
        final double d11 = entityLivingBase.field_70163_u + entityLivingBase.func_70047_e() / 2.0f - 0.5;
        return getRotationFromPosition(d, d10, d11);
    }
    
    public static float getNewAngle(float f) {
        if ((f %= 360.0f) >= 180.0f) {
            f -= 360.0f;
        }
        if (f < -180.0f) {
            f += 360.0f;
        }
        return f;
    }
}
