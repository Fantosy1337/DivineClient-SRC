package me.Divine.module.combat;

import net.minecraft.entity.player.*;
import me.Divine.module.*;
import me.Divine.*;
import me.Divine.settings.*;
import me.Divine.value.*;
import net.minecraft.network.play.client.*;
import java.lang.reflect.*;
import net.minecraftforge.fml.common.gameevent.*;
import me.Divine.utils.system.*;
import net.minecraft.entity.*;
import java.util.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class Killaura2 extends Module
{
    private EntityPlayer bot;
    private float pitch;
    private float yaw;
    
    public Killaura2() {
        super("Kill", "", Category.COMBAT);
        Divine.settingsManager.rSetting(new Setting("Range", this, 3.2, 0.1, 6.0, false));
    }
    
    @Override
    public boolean onPacket(final Object packet, final ASD.Side side) {
        if (packet instanceof CPacketPlayer) {
            try {
                final Field yawASD = CPacketPlayer.class.getDeclaredFields()[3];
                yawASD.setAccessible(true);
                final Field pitchASD = CPacketPlayer.class.getDeclaredFields()[4];
                pitchASD.setAccessible(true);
                yawASD.setFloat(packet, this.yaw);
                pitchASD.setFloat(packet, this.pitch);
            }
            catch (Exception ex) {}
        }
        return super.onPacket(packet, side);
    }
    
    @Override
    public void onEnable() {
        this.bot = null;
        super.onEnable();
    }
    
    @SubscribeEvent
    public void onPlayerTick(final TickEvent.PlayerTickEvent playerTickEvent) {
        final float aurarange = (float)Divine.settingsManager.getSettingByName(this, "Range").getValDouble();
        final boolean criticals = true;
        this.bot = (EntityPlayer)Killaura2.mc.field_71439_g.func_110144_aD();
        if (this.bot != null || Killaura2.mc.field_71439_g.func_110144_aD() instanceof EntityPlayer) {}
        for (final EntityPlayer targetez : Killaura2.mc.field_71441_e.field_73010_i) {
            if (targetez != Killaura2.mc.field_71439_g && (this.bot == null || targetez == this.bot)) {
                if (targetez.func_82150_aj()) {
                    continue;
                }
                final float[] data = RotationUtils.getRotation((Entity)targetez);
                this.yaw = data[0];
                this.pitch = data[0];
                if (Killaura2.mc.field_71439_g.func_184825_o(0.0f) != 1.0f || Killaura2.mc.field_71439_g.func_70032_d((Entity)targetez) > aurarange) {
                    continue;
                }
                Killaura2.mc.field_71442_b.func_78764_a((EntityPlayer)Killaura2.mc.field_71439_g, (Entity)targetez);
            }
        }
    }
}
