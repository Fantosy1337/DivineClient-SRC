package me.Divine.utils;

import net.minecraft.block.state.*;
import me.Divine.wrappers.*;
import net.minecraft.block.*;
import net.minecraft.block.material.*;
import net.minecraft.entity.player.*;
import net.minecraft.init.*;
import net.minecraft.util.*;
import net.minecraft.network.*;
import net.minecraft.client.multiplayer.*;
import net.minecraft.util.math.*;
import net.minecraft.client.network.*;
import net.minecraft.client.entity.*;
import net.minecraft.world.*;
import net.minecraft.network.play.client.*;
import net.minecraft.entity.*;
import java.util.*;

public final class BlockUtils
{
    public static IBlockState getState(final BlockPos pos) {
        return Wrapper.INSTANCE.world().func_180495_p(pos);
    }
    
    public static Block getBlock(final BlockPos pos) {
        return getState(pos).func_177230_c();
    }
    
    public static Material getMaterial(final BlockPos pos) {
        return getState(pos).func_185904_a();
    }
    
    public static boolean canBeClicked(final BlockPos pos) {
        return getBlock(pos).func_176209_a(getState(pos), false);
    }
    
    public static float getHardness(final BlockPos pos) {
        final IBlockState state = getState(pos);
        final Wrapper instance = Wrapper.INSTANCE;
        return state.func_185903_a((EntityPlayer)Wrapper.player(), (World)Wrapper.INSTANCE.world(), pos);
    }
    
    public static boolean isBlockMaterial(final BlockPos blockPos, final Block block) {
        return getBlock(blockPos) == Blocks.field_150350_a;
    }
    
    public static boolean isBlockMaterial(final BlockPos blockPos, final Material material) {
        return getState(blockPos).func_185904_a() == material;
    }
    
    public static boolean placeBlockLegit(final BlockPos pos) {
        final Wrapper instance = Wrapper.INSTANCE;
        final double field_70165_t = Wrapper.player().field_70165_t;
        final Wrapper instance2 = Wrapper.INSTANCE;
        final double field_70163_u = Wrapper.player().field_70163_u;
        final Wrapper instance3 = Wrapper.INSTANCE;
        final double n = field_70163_u + Wrapper.player().func_70047_e();
        final Wrapper instance4 = Wrapper.INSTANCE;
        final Vec3d eyesPos = new Vec3d(field_70165_t, n, Wrapper.player().field_70161_v);
        for (final EnumFacing side : EnumFacing.values()) {
            final BlockPos neighbor = pos.func_177972_a(side);
            final EnumFacing side2 = side.func_176734_d();
            if (eyesPos.func_72436_e(new Vec3d((Vec3i)pos).func_72441_c(0.5, 0.5, 0.5)) < eyesPos.func_72436_e(new Vec3d((Vec3i)neighbor).func_72441_c(0.5, 0.5, 0.5))) {
                if (getBlock(neighbor).func_176209_a(Wrapper.INSTANCE.world().func_180495_p(neighbor), false)) {
                    final Vec3d hitVec = new Vec3d((Vec3i)neighbor).func_72441_c(0.5, 0.5, 0.5).func_178787_e(new Vec3d(side2.func_176730_m()).func_186678_a(0.5));
                    if (eyesPos.func_72436_e(hitVec) <= 18.0625) {
                        faceVectorPacket(hitVec);
                    }
                }
            }
        }
        Wrapper.INSTANCE.sendPacket((Packet)new CPacketPlayerTryUseItem(EnumHand.MAIN_HAND));
        Utils.swingMainHand();
        return true;
    }
    
    public static boolean placeBlockSimple(final BlockPos pos) {
        final Wrapper instance = Wrapper.INSTANCE;
        final double field_70165_t = Wrapper.player().field_70165_t;
        final Wrapper instance2 = Wrapper.INSTANCE;
        final double field_70163_u = Wrapper.player().field_70163_u;
        final Wrapper instance3 = Wrapper.INSTANCE;
        final double n = field_70163_u + Wrapper.player().func_70047_e();
        final Wrapper instance4 = Wrapper.INSTANCE;
        final Vec3d eyesPos = new Vec3d(field_70165_t, n, Wrapper.player().field_70161_v);
        for (final EnumFacing side : EnumFacing.values()) {
            final BlockPos neighbor = pos.func_177972_a(side);
            final EnumFacing side2 = side.func_176734_d();
            if (getBlock(neighbor).func_176209_a(getState(neighbor), false)) {
                final Vec3d hitVec = new Vec3d((Vec3i)neighbor).func_72441_c(0.5, 0.5, 0.5).func_178787_e(new Vec3d(side2.func_176730_m()).func_186678_a(0.5));
                if (eyesPos.func_72436_e(hitVec) <= 36.0) {
                    final PlayerControllerMP controller = Wrapper.INSTANCE.controller();
                    final Wrapper instance5 = Wrapper.INSTANCE;
                    controller.func_187099_a(Wrapper.player(), Wrapper.INSTANCE.world(), neighbor, side2, hitVec, EnumHand.MAIN_HAND);
                    return true;
                }
            }
        }
        return false;
    }
    
    public static void faceVectorPacket(final Vec3d vec) {
        final double field_72450_a = vec.field_72450_a;
        final Wrapper instance = Wrapper.INSTANCE;
        final double diffX = field_72450_a - Wrapper.player().field_70165_t;
        final double field_72448_b = vec.field_72448_b;
        final Wrapper instance2 = Wrapper.INSTANCE;
        final double field_70163_u = Wrapper.player().field_70163_u;
        final Wrapper instance3 = Wrapper.INSTANCE;
        final double diffY = field_72448_b - (field_70163_u + Wrapper.player().func_70047_e());
        final double field_72449_c = vec.field_72449_c;
        final Wrapper instance4 = Wrapper.INSTANCE;
        final double diffZ = field_72449_c - Wrapper.player().field_70161_v;
        final double dist = MathHelper.func_76133_a(diffX * diffX + diffZ * diffZ);
        final float yaw = (float)Math.toDegrees(Math.atan2(diffZ, diffX)) - 90.0f;
        final float pitch = (float)(-Math.toDegrees(Math.atan2(diffY, dist)));
        final Wrapper instance5 = Wrapper.INSTANCE;
        final NetHandlerPlayClient field_71174_a = Wrapper.player().field_71174_a;
        final Wrapper instance6 = Wrapper.INSTANCE;
        final float field_70177_z = Wrapper.player().field_70177_z;
        final float n = yaw;
        final Wrapper instance7 = Wrapper.INSTANCE;
        final float n2 = field_70177_z + MathHelper.func_76142_g(n - Wrapper.player().field_70177_z);
        final Wrapper instance8 = Wrapper.INSTANCE;
        final float field_70125_A = Wrapper.player().field_70125_A;
        final float n3 = pitch;
        final Wrapper instance9 = Wrapper.INSTANCE;
        final float n4 = field_70125_A + MathHelper.func_76142_g(n3 - Wrapper.player().field_70125_A);
        final Wrapper instance10 = Wrapper.INSTANCE;
        field_71174_a.func_147297_a((Packet)new CPacketPlayer.Rotation(n2, n4, Wrapper.player().field_70122_E));
    }
    
    public static void faceBlockClient(final BlockPos blockPos) {
        final double n = blockPos.func_177958_n() + 0.5;
        final Wrapper instance = Wrapper.INSTANCE;
        final double diffX = n - Wrapper.player().field_70165_t;
        final double n2 = blockPos.func_177956_o() + 0.0;
        final Wrapper instance2 = Wrapper.INSTANCE;
        final double field_70163_u = Wrapper.player().field_70163_u;
        final Wrapper instance3 = Wrapper.INSTANCE;
        final double diffY = n2 - (field_70163_u + Wrapper.player().func_70047_e());
        final double n3 = blockPos.func_177952_p() + 0.5;
        final Wrapper instance4 = Wrapper.INSTANCE;
        final double diffZ = n3 - Wrapper.player().field_70161_v;
        final double dist = MathHelper.func_76133_a(diffX * diffX + diffZ * diffZ);
        final float yaw = (float)(Math.atan2(diffZ, diffX) * 180.0 / 3.141592653589793) - 90.0f;
        final float pitch = (float)(-(Math.atan2(diffY, dist) * 180.0 / 3.141592653589793));
        final Wrapper instance5 = Wrapper.INSTANCE;
        final EntityPlayerSP player = Wrapper.player();
        final Wrapper instance6 = Wrapper.INSTANCE;
        final float field_70177_z = Wrapper.player().field_70177_z;
        final float n4 = yaw;
        final Wrapper instance7 = Wrapper.INSTANCE;
        player.field_70177_z = field_70177_z + MathHelper.func_76142_g(n4 - Wrapper.player().field_70177_z);
        final Wrapper instance8 = Wrapper.INSTANCE;
        final EntityPlayerSP player2 = Wrapper.player();
        final Wrapper instance9 = Wrapper.INSTANCE;
        final float field_70125_A = Wrapper.player().field_70125_A;
        final float n5 = pitch;
        final Wrapper instance10 = Wrapper.INSTANCE;
        player2.field_70125_A = field_70125_A + MathHelper.func_76142_g(n5 - Wrapper.player().field_70125_A);
    }
    
    public static void faceBlockPacket(final BlockPos blockPos) {
        final double n = blockPos.func_177958_n() + 0.5;
        final Wrapper instance = Wrapper.INSTANCE;
        final double diffX = n - Wrapper.player().field_70165_t;
        final double n2 = blockPos.func_177956_o() + 0.0;
        final Wrapper instance2 = Wrapper.INSTANCE;
        final double field_70163_u = Wrapper.player().field_70163_u;
        final Wrapper instance3 = Wrapper.INSTANCE;
        final double diffY = n2 - (field_70163_u + Wrapper.player().func_70047_e());
        final double n3 = blockPos.func_177952_p() + 0.5;
        final Wrapper instance4 = Wrapper.INSTANCE;
        final double diffZ = n3 - Wrapper.player().field_70161_v;
        final double dist = MathHelper.func_76133_a(diffX * diffX + diffZ * diffZ);
        final float yaw = (float)(Math.atan2(diffZ, diffX) * 180.0 / 3.141592653589793) - 90.0f;
        final float pitch = (float)(-(Math.atan2(diffY, dist) * 180.0 / 3.141592653589793));
        final Wrapper instance5 = Wrapper.INSTANCE;
        final NetHandlerPlayClient field_71174_a = Wrapper.player().field_71174_a;
        final Wrapper instance6 = Wrapper.INSTANCE;
        final float field_70177_z = Wrapper.player().field_70177_z;
        final float n4 = yaw;
        final Wrapper instance7 = Wrapper.INSTANCE;
        final float n5 = field_70177_z + MathHelper.func_76142_g(n4 - Wrapper.player().field_70177_z);
        final Wrapper instance8 = Wrapper.INSTANCE;
        final float field_70125_A = Wrapper.player().field_70125_A;
        final float n6 = pitch;
        final Wrapper instance9 = Wrapper.INSTANCE;
        final float n7 = field_70125_A + MathHelper.func_76142_g(n6 - Wrapper.player().field_70125_A);
        final Wrapper instance10 = Wrapper.INSTANCE;
        field_71174_a.func_147297_a((Packet)new CPacketPlayer.Rotation(n5, n7, Wrapper.player().field_70122_E));
    }
    
    public static void faceBlockClientHorizontally(final BlockPos blockPos) {
        final double n = blockPos.func_177958_n() + 0.5;
        final Wrapper instance = Wrapper.INSTANCE;
        final double diffX = n - Wrapper.player().field_70165_t;
        final double n2 = blockPos.func_177952_p() + 0.5;
        final Wrapper instance2 = Wrapper.INSTANCE;
        final double diffZ = n2 - Wrapper.player().field_70161_v;
        final float yaw = (float)(Math.atan2(diffZ, diffX) * 180.0 / 3.141592653589793) - 90.0f;
        final Wrapper instance3 = Wrapper.INSTANCE;
        final EntityPlayerSP player = Wrapper.player();
        final Wrapper instance4 = Wrapper.INSTANCE;
        final float field_70177_z = Wrapper.player().field_70177_z;
        final float n3 = yaw;
        final Wrapper instance5 = Wrapper.INSTANCE;
        player.field_70177_z = field_70177_z + MathHelper.func_76142_g(n3 - Wrapper.player().field_70177_z);
    }
    
    public static float getPlayerBlockDistance(final BlockPos blockPos) {
        return getPlayerBlockDistance(blockPos.func_177958_n(), blockPos.func_177956_o(), blockPos.func_177952_p());
    }
    
    public static float getPlayerBlockDistance(final double posX, final double posY, final double posZ) {
        final Wrapper instance = Wrapper.INSTANCE;
        final float xDiff = (float)(Wrapper.player().field_70165_t - posX);
        final Wrapper instance2 = Wrapper.INSTANCE;
        final float yDiff = (float)(Wrapper.player().field_70163_u - posY);
        final Wrapper instance3 = Wrapper.INSTANCE;
        final float zDiff = (float)(Wrapper.player().field_70161_v - posZ);
        return getBlockDistance(xDiff, yDiff, zDiff);
    }
    
    public static float getBlockDistance(final float xDiff, final float yDiff, final float zDiff) {
        return MathHelper.func_76129_c((xDiff - 0.5f) * (xDiff - 0.5f) + (yDiff - 0.5f) * (yDiff - 0.5f) + (zDiff - 0.5f) * (zDiff - 0.5f));
    }
    
    public static float getHorizontalPlayerBlockDistance(final BlockPos blockPos) {
        final Wrapper instance = Wrapper.INSTANCE;
        final float xDiff = (float)(Wrapper.player().field_70165_t - blockPos.func_177958_n());
        final Wrapper instance2 = Wrapper.INSTANCE;
        final float zDiff = (float)(Wrapper.player().field_70161_v - blockPos.func_177952_p());
        return MathHelper.func_76129_c((xDiff - 0.5f) * (xDiff - 0.5f) + (zDiff - 0.5f) * (zDiff - 0.5f));
    }
    
    public static boolean breakBlockSimple(final BlockPos pos) {
        EnumFacing side = null;
        final EnumFacing[] sides = EnumFacing.values();
        final Vec3d eyesPos = Utils.getEyesPos();
        final Vec3d relCenter = getState(pos).func_185900_c((IBlockAccess)Wrapper.INSTANCE.world(), pos).func_189972_c();
        final Vec3d center = new Vec3d((Vec3i)pos).func_178787_e(relCenter);
        final Vec3d[] hitVecs = new Vec3d[sides.length];
        for (int i = 0; i < sides.length; ++i) {
            final Vec3i dirVec = sides[i].func_176730_m();
            final Vec3d relHitVec = new Vec3d(relCenter.field_72450_a * dirVec.func_177958_n(), relCenter.field_72448_b * dirVec.func_177956_o(), relCenter.field_72449_c * dirVec.func_177952_p());
            hitVecs[i] = center.func_178787_e(relHitVec);
        }
        for (int i = 0; i < sides.length; ++i) {
            if (Wrapper.INSTANCE.world().func_147447_a(eyesPos, hitVecs[i], false, true, false) == null) {
                side = sides[i];
                break;
            }
        }
        if (side == null) {
            final double distanceSqToCenter = eyesPos.func_72436_e(center);
            for (int j = 0; j < sides.length; ++j) {
                if (eyesPos.func_72436_e(hitVecs[j]) < distanceSqToCenter) {
                    side = sides[j];
                    break;
                }
            }
        }
        if (side == null) {
            side = sides[0];
        }
        Utils.faceVectorPacket(hitVecs[side.ordinal()]);
        if (!Wrapper.INSTANCE.controller().func_180512_c(pos, side)) {
            return false;
        }
        Wrapper.INSTANCE.sendPacket((Packet)new CPacketAnimation(EnumHand.MAIN_HAND));
        return true;
    }
    
    public static void breakBlocksPacketSpam(final Iterable<BlockPos> blocks) {
        final Vec3d eyesPos = Utils.getEyesPos();
        final Wrapper instance = Wrapper.INSTANCE;
        final NetHandlerPlayClient connection = Wrapper.player().field_71174_a;
        for (final BlockPos pos : blocks) {
            final Vec3d posVec = new Vec3d((Vec3i)pos).func_72441_c(0.5, 0.5, 0.5);
            final double distanceSqPosVec = eyesPos.func_72436_e(posVec);
            for (final EnumFacing side : EnumFacing.values()) {
                final Vec3d hitVec = posVec.func_178787_e(new Vec3d(side.func_176730_m()).func_186678_a(0.5));
                if (eyesPos.func_72436_e(hitVec) < distanceSqPosVec) {
                    connection.func_147297_a((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, pos, side));
                    connection.func_147297_a((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, pos, side));
                    break;
                }
            }
        }
    }
    
    public static LinkedList<BlockPos> findBlocksNearEntity(final EntityLivingBase entity, final int blockId, final int blockMeta, final int distance) {
        final LinkedList<BlockPos> blocks = new LinkedList<BlockPos>();
        final Wrapper instance = Wrapper.INSTANCE;
        int x = (int)Wrapper.player().field_70165_t - distance;
        while (true) {
            final int n = x;
            final Wrapper instance2 = Wrapper.INSTANCE;
            if (n > (int)Wrapper.player().field_70165_t + distance) {
                break;
            }
            final Wrapper instance3 = Wrapper.INSTANCE;
            int z = (int)Wrapper.player().field_70161_v - distance;
            while (true) {
                final int n2 = z;
                final Wrapper instance4 = Wrapper.INSTANCE;
                if (n2 > (int)Wrapper.player().field_70161_v + distance) {
                    break;
                }
                for (int height = Wrapper.INSTANCE.world().func_189649_b(x, z), y = 0; y <= height; ++y) {
                    final BlockPos blockPos = new BlockPos(x, y, z);
                    final IBlockState blockState = Wrapper.INSTANCE.world().func_180495_p(blockPos);
                    if (blockId == -1 || blockMeta == -1) {
                        blocks.add(blockPos);
                    }
                    else {
                        final int id = Block.func_149682_b(blockState.func_177230_c());
                        final int meta = blockState.func_177230_c().func_176201_c(blockState);
                        if (id == blockId && meta == blockMeta) {
                            blocks.add(blockPos);
                        }
                    }
                }
                ++z;
            }
            ++x;
        }
        return blocks;
    }
    
    public static boolean isCollidable(final Block block) {
        return block != Blocks.field_150350_a && block != Blocks.field_185773_cZ && block != Blocks.field_150459_bM && block != Blocks.field_150330_I && block != Blocks.field_150398_cm && block != Blocks.field_150356_k && block != Blocks.field_150358_i && block != Blocks.field_150353_l && block != Blocks.field_150394_bc && block != Blocks.field_150388_bm && block != Blocks.field_150469_bN && block != Blocks.field_150393_bb && block != Blocks.field_150328_O && block != Blocks.field_150337_Q && block != Blocks.field_150429_aA && block != Blocks.field_150329_H && block != Blocks.field_150478_aa && block != Blocks.field_150437_az && block != Blocks.field_150327_N && block != Blocks.field_150395_bd && block != Blocks.field_150355_j && block != Blocks.field_150321_G && block != Blocks.field_150464_aj;
    }
}
