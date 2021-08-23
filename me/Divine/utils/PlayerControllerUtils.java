package me.Divine.utils;

import net.minecraft.entity.*;
import net.minecraft.client.multiplayer.*;
import net.minecraft.client.*;
import net.minecraft.client.network.*;
import me.Divine.wrappers.*;
import net.minecraft.entity.player.*;
import net.minecraft.world.*;
import me.Divine.utils.system.*;
import net.minecraftforge.fml.relauncher.*;
import java.lang.reflect.*;

public class PlayerControllerUtils
{
    public static void setReach(final Entity entity, final double range) {
        final Minecraft mc = Wrapper.INSTANCE.mc();
        final Wrapper instance = Wrapper.INSTANCE;
        final EntityPlayer player = (EntityPlayer)Wrapper.player();
        if (player == entity) {
            class 1RangePlayerController extends PlayerControllerMP
            {
                private float range;
                
                public 1RangePlayerController(final Minecraft mcIn, final NetHandlerPlayClient netHandler) {
                    super(mcIn, netHandler);
                    final Wrapper instance = Wrapper.INSTANCE;
                    this.range = (float)Wrapper.player().func_110148_a(EntityPlayer.REACH_DISTANCE).func_111126_e();
                }
                
                public float func_78757_d() {
                    return this.range;
                }
                
                public void setBlockReachDistance(final float range) {
                    this.range = range;
                }
            }
            if (!(mc.field_71442_b instanceof 1RangePlayerController)) {
                final GameType gameType = (GameType)ReflectionHelper.getPrivateValue((Class)PlayerControllerMP.class, (Object)mc.field_71442_b, new String[] { Mapping.currentGameType });
                final NetHandlerPlayClient netClient = (NetHandlerPlayClient)ReflectionHelper.getPrivateValue((Class)PlayerControllerMP.class, (Object)mc.field_71442_b, new String[] { Mapping.connection });
                final 1RangePlayerController controller = new 1RangePlayerController(mc, netClient);
                final boolean isFlying = player.field_71075_bZ.field_75100_b;
                final boolean allowFlying = player.field_71075_bZ.field_75101_c;
                controller.func_78746_a(gameType);
                player.field_71075_bZ.field_75100_b = isFlying;
                player.field_71075_bZ.field_75101_c = allowFlying;
                mc.field_71442_b = controller;
            }
            ((1RangePlayerController)mc.field_71442_b).setBlockReachDistance((float)range);
        }
    }
    
    public static void setIsHittingBlock(final boolean isHittingBlock) {
        try {
            final Field field = PlayerControllerMP.class.getDeclaredField(Mapping.isHittingBlock);
            field.setAccessible(true);
            field.setBoolean(Wrapper.INSTANCE.controller(), isHittingBlock);
        }
        catch (Exception ex) {}
    }
    
    public static void setBlockHitDelay(final int blockHitDelay) {
        try {
            final Field field = PlayerControllerMP.class.getDeclaredField(Mapping.blockHitDelay);
            field.setAccessible(true);
            field.setInt(Wrapper.INSTANCE.controller(), blockHitDelay);
        }
        catch (Exception ex) {}
    }
    
    public static float getCurBlockDamageMP() {
        float getCurBlockDamageMP = 0.0f;
        try {
            final Field field = PlayerControllerMP.class.getDeclaredField(Mapping.curBlockDamageMP);
            field.setAccessible(true);
            getCurBlockDamageMP = field.getFloat(Wrapper.INSTANCE.controller());
        }
        catch (Exception ex) {}
        return getCurBlockDamageMP;
    }
}
