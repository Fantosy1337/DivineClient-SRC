package me.Divine.utils;

import me.Divine.event.*;
import net.minecraft.client.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.*;
import net.minecraft.block.*;
import net.minecraft.block.state.*;
import java.util.*;

public class BlockUtil extends Event2
{
    protected static Minecraft mc;
    
    public static float getHorizontalPlayerBlockDistance(final BlockPos blockPos) {
        final float xDiff = (float)(BlockUtil.mc.field_71439_g.field_70165_t - blockPos.func_177958_n());
        final float zDiff = (float)(BlockUtil.mc.field_71439_g.field_70161_v - blockPos.func_177952_p());
        return MathHelper.func_76129_c((xDiff - 0.5f) * (xDiff - 0.5f) + (zDiff - 0.5f) * (zDiff - 0.5f));
    }
    
    public static boolean isOnIce(final Entity entity) {
        if (entity == null) {
            return false;
        }
        boolean onIce = false;
        final int y = (int)entity.func_174813_aQ().func_72317_d(0.0, -0.01, 0.0).field_72338_b;
        for (int x = MathHelper.func_76128_c(entity.func_174813_aQ().field_72340_a); x < MathHelper.func_76128_c(entity.func_174813_aQ().field_72336_d) + 1; ++x) {
            for (int z = MathHelper.func_76128_c(entity.func_174813_aQ().field_72339_c); z < MathHelper.func_76128_c(entity.func_174813_aQ().field_72334_f) + 1; ++z) {
                final Block block = BlockUtil.mc.field_71441_e.func_180495_p(new BlockPos(x, y, z)).func_177230_c();
                if (block != null && !(block instanceof BlockAir)) {
                    if (!(block instanceof BlockIce) && !(block instanceof BlockPackedIce)) {
                        return false;
                    }
                    onIce = true;
                }
            }
        }
        return onIce;
    }
    
    public static Block getBlock(final BlockPos pos) {
        return getState(pos).func_177230_c();
    }
    
    public static IBlockState getState(final BlockPos pos) {
        return BlockUtil.mc.field_71441_e.func_180495_p(pos);
    }
    
    public static ArrayList<BlockPos> getAllInBox(final BlockPos from, final BlockPos to) {
        final ArrayList<BlockPos> blocks = new ArrayList<BlockPos>();
        final BlockPos min = new BlockPos(Math.min(from.func_177958_n(), to.func_177958_n()), Math.min(from.func_177956_o(), to.func_177956_o()), Math.min(from.func_177952_p(), to.func_177952_p()));
        final BlockPos max = new BlockPos(Math.max(from.func_177958_n(), to.func_177958_n()), Math.max(from.func_177956_o(), to.func_177956_o()), Math.max(from.func_177952_p(), to.func_177952_p()));
        for (int x = min.func_177958_n(); x <= max.func_177958_n(); ++x) {
            for (int y = min.func_177956_o(); y <= max.func_177956_o(); ++y) {
                for (int z = min.func_177952_p(); z <= max.func_177952_p(); ++z) {
                    blocks.add(new BlockPos(x, y, z));
                }
            }
        }
        return blocks;
    }
    
    public static Block getBlock(final int x, final int y, final int z) {
        return BlockUtil.mc.field_71441_e.func_180495_p(new BlockPos(x, y, z)).func_177230_c();
    }
    
    static {
        BlockUtil.mc = Minecraft.func_71410_x();
    }
    
    public interface Collidable
    {
        boolean collideBlock(final Block p0);
    }
}
