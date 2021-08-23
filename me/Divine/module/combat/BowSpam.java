package me.Divine.module.combat;

import net.minecraftforge.fml.common.gameevent.*;
import net.minecraft.item.*;
import net.minecraft.util.math.*;
import net.minecraft.network.*;
import net.minecraft.network.play.client.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.Divine.module.*;

public class BowSpam extends Module
{
    @SubscribeEvent
    public void onPlayerTick(final TickEvent.PlayerTickEvent playerTickEvent) {
        if (BowSpam.mc.field_71439_g.field_71071_by.func_70448_g().func_77973_b() instanceof ItemBow && BowSpam.mc.field_71439_g.func_184587_cr() && BowSpam.mc.field_71439_g.func_184612_cw() >= 2.4) {
            BowSpam.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.field_177992_a, BowSpam.mc.field_71439_g.func_174811_aO()));
            BowSpam.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayerTryUseItem(BowSpam.mc.field_71439_g.func_184600_cs()));
            BowSpam.mc.field_71439_g.func_184597_cx();
        }
    }
    
    public BowSpam() {
        super("BowSpam", "", Category.COMBAT);
    }
}
