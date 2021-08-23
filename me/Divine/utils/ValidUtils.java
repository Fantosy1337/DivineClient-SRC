package me.Divine.utils;

import me.Divine.wrappers.*;
import net.minecraft.entity.*;

public class ValidUtils
{
    public static boolean isInAttackFOV(final EntityLivingBase entity, final int fov) {
        return Utils.getDistanceFromMouse(entity) <= fov;
    }
    
    public static boolean isInAttackRange(final EntityLivingBase entity, final float range) {
        final Wrapper instance = Wrapper.INSTANCE;
        return entity.func_70032_d((Entity)Wrapper.player()) <= range;
    }
    
    public static boolean isInvisible(final EntityLivingBase entity) {
        return entity.func_82150_aj();
    }
}
