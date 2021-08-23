package me.Divine.utils.system;

import net.minecraft.client.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import net.minecraft.entity.*;
import net.minecraft.util.math.*;

public class RotationUtils
{
    private static final Minecraft mc;
    
    public static void setTargetRotation(final float yaw, final float pitch) {
        RotationUtils.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Rotation(yaw, pitch, RotationUtils.mc.field_71439_g.field_70122_E));
    }
    
    public static float[] getRotation(final Entity entity) {
        final Vec3d eyesPos = new Vec3d(RotationUtils.mc.field_71439_g.field_70165_t + Math.random() / 10.0, RotationUtils.mc.field_71439_g.field_70163_u + RotationUtils.mc.field_71439_g.func_70047_e(), RotationUtils.mc.field_71439_g.field_70161_v + Math.random() / 10.0);
        final double diffX = entity.func_174791_d().field_72450_a - eyesPos.field_72450_a;
        final double diffY = entity.func_174791_d().field_72448_b - eyesPos.field_72448_b;
        final double diffZ = entity.func_174791_d().field_72449_c - eyesPos.field_72449_c;
        final double diffXZ = Math.sqrt(diffX * diffX + diffZ * diffZ);
        float yaw = MathHelper.func_76142_g((float)Math.toDegrees(Math.atan2(diffZ, diffX)) - 90.0f);
        float pitch = MathHelper.func_76142_g((float)(-Math.toDegrees(Math.atan2(diffY, diffXZ))) - 10.0f);
        final float f = RotationUtils.mc.field_71474_y.field_74341_c * 0.6f + 0.2f;
        final float gcd = f * f * f * 1.2f;
        yaw -= yaw % gcd;
        pitch -= pitch % gcd;
        return new float[] { yaw, pitch };
    }
    
    static {
        mc = Minecraft.func_71410_x();
    }
}
