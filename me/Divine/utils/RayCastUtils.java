package me.Divine.utils;

import me.Divine.wrappers.*;
import net.minecraft.util.*;
import com.google.common.base.*;
import java.util.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.monster.*;

public class RayCastUtils
{
    public static Entity rayCast(final double range, final float yaw, final float pitch) {
        final double d2;
        final double d0 = d2 = range;
        final Wrapper instance = Wrapper.INSTANCE;
        final Vec3d vec3 = Wrapper.player().func_174824_e(1.0f);
        boolean flag = false;
        final boolean flag2 = true;
        if (d0 > 3.0) {
            flag = true;
        }
        final Vec3d vec4 = getVectorForRotation(pitch, yaw);
        final Vec3d vec5 = vec3.func_72441_c(vec4.field_72450_a * d0, vec4.field_72448_b * d0, vec4.field_72449_c * d0);
        Entity pointedEntity = null;
        Vec3d vec6 = null;
        final float f = 1.0f;
        final List list = Wrapper.INSTANCE.world().func_175674_a(Wrapper.INSTANCE.mc().func_175606_aa(), Wrapper.INSTANCE.mc().func_175606_aa().func_174813_aQ().func_72317_d(vec4.field_72450_a * d0, vec4.field_72448_b * d0, vec4.field_72449_c * d0).func_72321_a((double)f, (double)f, (double)f), Predicates.and(EntitySelectors.field_180132_d, Entity::func_70067_L));
        double d3 = d2;
        for (int i = 0; i < list.size(); ++i) {
            final Entity entity1 = list.get(i);
            final float f2 = entity1.func_70111_Y();
            final AxisAlignedBB axisalignedbb = entity1.func_174813_aQ().func_72321_a((double)f2, (double)f2, (double)f2);
            final RayTraceResult movingobjectposition = axisalignedbb.func_72327_a(vec3, vec5);
            if (axisalignedbb.func_72318_a(vec3)) {
                if (d3 >= 0.0) {
                    pointedEntity = entity1;
                    vec6 = ((movingobjectposition == null) ? vec3 : movingobjectposition.field_72307_f);
                    d3 = 0.0;
                }
            }
            else if (movingobjectposition != null) {
                final double d4 = vec3.func_72438_d(movingobjectposition.field_72307_f);
                if (d4 < d3 || d3 == 0.0) {
                    final boolean flag3 = false;
                    if (entity1 == Wrapper.INSTANCE.mc().func_175606_aa().func_184187_bx() && !flag3) {
                        if (d3 == 0.0) {
                            pointedEntity = entity1;
                            vec6 = movingobjectposition.field_72307_f;
                        }
                    }
                    else {
                        pointedEntity = entity1;
                        vec6 = movingobjectposition.field_72307_f;
                        d3 = d4;
                    }
                }
            }
        }
        return pointedEntity;
    }
    
    public static Vec3d getVectorForRotation(final float pitch, final float yaw) {
        final float f = MathHelper.func_76134_b(-yaw * 0.017453292f - 3.1415927f);
        final float f2 = MathHelper.func_76126_a(-yaw * 0.017453292f - 3.1415927f);
        final float f3 = -MathHelper.func_76134_b(-pitch * 0.017453292f);
        final float f4 = MathHelper.func_76126_a(-pitch * 0.017453292f);
        return new Vec3d((double)(f2 * f3), (double)f4, (double)(f * f3));
    }
    
    public static Vec3d interpolateVec3d(final Vec3d current, final Vec3d last, final float partialTicks) {
        return current.func_178788_d(last).func_186678_a((double)partialTicks).func_178787_e(last);
    }
    
    public static boolean isAnimal(final Entity entity) {
        return entity instanceof EntityAgeable || entity instanceof EntityAmbientCreature || entity instanceof EntityWaterMob || entity instanceof EntityGolem;
    }
    
    public static Vec3d getInterpolatedPos(final Entity entity, final float partialTicks) {
        return interpolateVec3d(entity.func_174791_d(), new Vec3d(entity.field_70142_S, entity.field_70137_T, entity.field_70136_U), partialTicks);
    }
}
