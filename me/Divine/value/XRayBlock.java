package me.Divine.value;

import net.minecraft.util.math.*;

public class XRayBlock
{
    private BlockPos blockPos;
    private XRayData xRayData;
    
    public XRayBlock(final BlockPos blockPos, final XRayData xRayData) {
        this.blockPos = blockPos;
        this.xRayData = xRayData;
    }
    
    public BlockPos getBlockPos() {
        return this.blockPos;
    }
    
    public XRayData getxRayData() {
        return this.xRayData;
    }
}
