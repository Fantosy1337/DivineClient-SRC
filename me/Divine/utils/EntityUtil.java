package me.Divine.utils;

import net.minecraft.util.math.*;
import net.minecraft.entity.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.monster.*;

public class EntityUtil
{
    public static Vec3d getInterpolatedPos(final Entity entity, final float f) {
        return interpolateVec3d(entity.func_174791_d(), new Vec3d(entity.field_70142_S, entity.field_70137_T, entity.field_70136_U), f);
    }
    
    public static boolean isAnimal(final Entity entity) {
        return entity instanceof EntityAgeable || entity instanceof EntityAmbientCreature || entity instanceof EntityWaterMob || entity instanceof EntityGolem;
    }
    
    public static Vec3d interpolateVec3d(final Vec3d vec3d, final Vec3d vec3d2, final float f) {
        return vec3d.func_178788_d(vec3d2).func_186678_a((double)f).func_178787_e(vec3d2);
    }
}
