package me.Divine.module.combat;

import net.minecraftforge.fml.common.gameevent.*;
import net.minecraft.client.*;
import net.minecraft.item.*;
import net.minecraft.init.*;
import net.minecraft.util.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.Divine.module.*;
import net.minecraft.inventory.*;
import net.minecraft.entity.player.*;
import me.Divine.*;
import me.Divine.Notification.*;

public class AutoTotem extends Module
{
    private final int OFFHAND_SLOT = 45;
    
    @SubscribeEvent
    public void onPlayerTick(final TickEvent.PlayerTickEvent playerTickEvent) {
        final ItemStack itemStack = AutoTotem.mc.field_71439_g.func_184582_a(EntityEquipmentSlot.OFFHAND);
        final NonNullList nonNullList = Minecraft.func_71410_x().field_71439_g.field_71071_by.field_70462_a;
        for (int i = 0; i < nonNullList.size(); ++i) {
            if (nonNullList.get(i) != ItemStack.field_190927_a && (itemStack == null || itemStack.func_77973_b() != Items.field_190929_cY) && ((ItemStack)nonNullList.get(i)).func_77973_b() == Items.field_190929_cY) {
                new ItemStack(Items.field_190929_cY);
                this.b(i);
                break;
            }
        }
    }
    
    public AutoTotem() {
        super("AutoTotem", "automatically use totems", Category.COMBAT);
    }
    
    public void b(int n) {
        n = 0;
        if (AutoTotem.mc.field_71439_g.field_71070_bA instanceof ContainerPlayer && AutoTotem.mc.field_71439_g.field_70173_aa % 5 == 0) {
            AutoTotem.mc.field_71442_b.func_187098_a(0, 45, 0, ClickType.PICKUP, (EntityPlayer)AutoTotem.mc.field_71439_g);
            final int n2 = n + 36;
            AutoTotem.mc.field_71442_b.func_187098_a(0, n2, 0, ClickType.PICKUP, (EntityPlayer)AutoTotem.mc.field_71439_g);
        }
    }
    
    @Override
    public void onEnable() {
        Divine.instance.notificationManager.addNotification(NotificationType.ERROR, "AutoTotem Enabled", 2000);
        super.onEnable();
    }
}
