package me.Divine.utils;

import net.minecraft.client.*;
import net.minecraft.entity.*;
import net.minecraft.util.math.*;

public class RotationUtil
{
    public static RotationUtil instance;
    int[] randoms;
    public static float sYaw;
    public static float sPitch;
    public static float aacB;
    private static Minecraft mc;
    
    public RotationUtil() {
        this.randoms = new int[] { 0, 1 };
    }
    
    public static boolean canSeeEntityAtFov(final Entity entityLiving, final float scope) {
        final double diffX = entityLiving.field_70165_t - Minecraft.func_71410_x().field_71439_g.field_70165_t;
        final double diffZ = entityLiving.field_70161_v - Minecraft.func_71410_x().field_71439_g.field_70161_v;
        final float newYaw = (float)(Math.toDegrees(Math.atan2(diffZ, diffX)) - 90.0);
        final double difference = angleDifference(newYaw, Minecraft.func_71410_x().field_71439_g.field_70177_z);
        return difference <= scope;
    }
    
    public static double angleDifference(final double a, final double b) {
        float yaw360 = (float)(Math.abs(a - b) % 360.0);
        if (yaw360 > 180.0f) {
            yaw360 = 360.0f - yaw360;
        }
        return yaw360;
    }
    
    public static float[] getRotations(final Entity entity) {
        final double d = entity.field_70165_t + (entity.field_70165_t - entity.field_70142_S) - RotationUtil.mc.field_71439_g.field_70165_t;
        final double d2 = entity.field_70163_u + entity.func_70047_e() - RotationUtil.mc.field_71439_g.field_70163_u + RotationUtil.mc.field_71439_g.func_70047_e() - 3.5;
        final double d3 = entity.field_70161_v + (entity.field_70161_v - entity.field_70136_U) - RotationUtil.mc.field_71439_g.field_70161_v;
        final double d4 = Math.sqrt(Math.pow(d, 2.0) + Math.pow(d3, 2.0));
        float f = (float)Math.toDegrees(-Math.atan(d / d3));
        final float f2 = (float)(-Math.toDegrees(Math.atan(d2 / d4)));
        if (d < 0.0 && d3 < 0.0) {
            f = (float)(90.0 + Math.toDegrees(Math.atan(d3 / d)));
        }
        else if (d > 0.0 && d3 < 0.0) {
            final double v = Math.toDegrees(Math.atan(d3 / d));
            f = (float)(-90.0 + v);
            final float f3 = RotationUtil.mc.field_71474_y.field_74341_c * 0.8f;
            final float gcd = f * f * f * 1.2f;
            double var10000 = d - d % gcd;
            var10000 = d2 - d2 % gcd;
        }
        return new float[] { f, f2 };
    }
    
    public static float[] faceEntity(final Entity entityIn, float currentYaw, float currentPitch, final float yawSpeed, final float pitchSpeed, final boolean canHit) {
        final double x = entityIn.field_70165_t - RotationUtil.mc.field_71439_g.field_70165_t;
        final double y = entityIn.field_70163_u + entityIn.func_70047_e() - (RotationUtil.mc.field_71439_g.field_70163_u + RotationUtil.mc.field_71439_g.func_70047_e());
        final double z = entityIn.field_70161_v - RotationUtil.mc.field_71439_g.field_70161_v;
        final double randomYaw = 0.05;
        final double randomPitch = 0.05;
        final float range = RotationUtil.mc.field_71439_g.func_70032_d(entityIn);
        final float calculate = MathHelper.func_76133_a(x * x + z * z);
        final float calcYaw = (float)(MathHelper.func_181159_b(z, x) * 180.0 / 3.141592653589793 - 90.0 + (int)(4.0f / range));
        final float calcPitch = (float)(-(MathHelper.func_181159_b(y, (double)calculate) * 180.0 / 3.141592653589793) + 5.0f / range);
        currentPitch = (float)(entityIn.field_70163_u + entityIn.func_70047_e() - RotationUtil.mc.field_71439_g.field_70163_u + RotationUtil.mc.field_71439_g.func_70047_e()) / 3.0f;
        currentYaw = (float)((entityIn.func_174813_aQ().field_72338_b + entityIn.func_174813_aQ().field_72337_e) / 2.0 - RotationUtil.mc.field_71439_g.field_70163_u + RotationUtil.mc.field_71439_g.func_70047_e());
        final float f = RotationUtil.mc.field_71474_y.field_74341_c * 0.6f + 0.2f;
        final float gcd = f * f * f * 1.2f;
        float yaw = updateRotation(currentYaw, calcYaw, yawSpeed);
        float pitch = updateRotation(currentPitch, calcPitch, pitchSpeed);
        yaw -= yaw % gcd;
        pitch -= pitch % gcd;
        return new float[] { yaw, pitch };
    }
    
    public static float updateRotation(final float current, final float intended, final float speed) {
        float f = MathHelper.func_76142_g(intended - current);
        if (f > speed) {
            f = speed;
        }
        if (f < -speed) {
            f = -speed;
        }
        return current + f;
    }
    
    static {
        RotationUtil.instance = new RotationUtil();
        RotationUtil.mc = Minecraft.func_71410_x();
    }
}
