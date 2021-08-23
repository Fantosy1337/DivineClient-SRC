package me.Divine.module.combat;

import me.Divine.module.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraft.entity.item.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.*;
import net.minecraft.entity.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class TriggerBot extends Module
{
    public TriggerBot() {
        super("TriggerBot", "", Category.COMBAT);
    }
    
    private void setRotation(final float f, final float f2) {
        TriggerBot.mc.field_71439_g.field_70177_z = f % 360.0f;
        TriggerBot.mc.field_71439_g.field_70125_A = f2 % 360.0f;
    }
    
    @SubscribeEvent
    public void onPlayerTick(final TickEvent.PlayerTickEvent playerTickEvent) {
        final Entity entity = TriggerBot.mc.field_71476_x.field_72308_g;
        if (entity == null || TriggerBot.mc.field_71439_g.func_70032_d(entity) > 3.5 || entity instanceof EntityEnderCrystal || entity.field_70128_L || ((EntityLivingBase)entity).func_110143_aJ() <= 0.0f || !(entity instanceof EntityPlayer)) {
            return;
        }
        double d = (entity.field_70177_z + 180.0f) % 360.0f;
        double d2 = TriggerBot.mc.field_71439_g.field_70177_z % 360.0f;
        if (d < 0.0) {
            d += 360.0;
        }
        if (d2 < 0.0) {
            d2 += 360.0;
        }
        if (TriggerBot.mc.field_71439_g.func_184825_o(0.0f) >= 1.0f) {
            TriggerBot.mc.field_71439_g.field_70177_z = this.getRotations(entity)[0];
            TriggerBot.mc.field_71439_g.field_70125_A = this.getRotations(entity)[1];
            TriggerBot.mc.field_71442_b.func_78764_a((EntityPlayer)TriggerBot.mc.field_71439_g, entity);
            TriggerBot.mc.field_71439_g.func_184609_a(EnumHand.MAIN_HAND);
        }
    }
    
    public float[] getRotations(final Entity entity) {
        final double d = entity.field_70165_t + (entity.field_70165_t - entity.field_70142_S) - TriggerBot.mc.field_71439_g.field_70165_t;
        final double d2 = entity.field_70163_u + entity.func_70047_e() - TriggerBot.mc.field_71439_g.field_70163_u + TriggerBot.mc.field_71439_g.func_70047_e() - 3.5;
        final double d3 = entity.field_70161_v + (entity.field_70161_v - entity.field_70136_U) - TriggerBot.mc.field_71439_g.field_70161_v;
        final double d4 = Math.sqrt(Math.pow(d, 2.0) + Math.pow(d3, 2.0));
        float f = (float)Math.toDegrees(-Math.atan(d / d3));
        final float f2 = (float)(-Math.toDegrees(Math.atan(d2 / d4)));
        if (d < 0.0 && d3 < 0.0) {
            f = (float)(90.0 + Math.toDegrees(Math.atan(d3 / d)));
        }
        else if (d > 0.0 && d3 < 0.0) {
            f = (float)(-90.0 + Math.toDegrees(Math.atan(d3 / d)));
        }
        return new float[] { f, f2 };
    }
}
