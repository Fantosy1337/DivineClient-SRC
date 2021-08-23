package me.Divine.module.combat;

import me.Divine.utils.*;
import me.Divine.module.*;
import java.util.*;
import me.Divine.*;
import me.Divine.settings.*;
import me.Divine.value.*;
import me.Divine.wrappers.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import net.minecraft.world.*;
import net.minecraft.entity.*;
import net.minecraft.init.*;

public class Criticals extends Module
{
    TimerUtils timer;
    boolean cancelSomePackets;
    
    public Criticals() {
        super("Criticals", "", Category.COMBAT);
        final ArrayList<String> mode = new ArrayList<String>();
        mode.add("Packet");
        mode.add("Jump");
        final Divine instance = Divine.instance;
        Divine.settingsManager.rSetting(new Setting("Mode", this, "Packet", mode));
        this.timer = new TimerUtils();
    }
    
    @Override
    public boolean onPacket(final Object packet, final ASD.Side side) {
        final Wrapper instance = Wrapper.INSTANCE;
        if (Wrapper.player().field_70122_E && side == ASD.Side.OUT) {
            if (packet instanceof CPacketUseEntity) {
                final CPacketUseEntity attack = (CPacketUseEntity)packet;
                if (attack.func_149565_c() == CPacketUseEntity.Action.ATTACK) {
                    final Divine instance2 = Divine.instance;
                    if (Divine.settingsManager.getSettingByName(this, "Mode").getValString().equals("Packet")) {
                        final Wrapper instance3 = Wrapper.INSTANCE;
                        if (Wrapper.player().field_70124_G && this.timer.isDelay(500L)) {
                            final Wrapper instance4 = Wrapper.INSTANCE;
                            final Wrapper instance5 = Wrapper.INSTANCE;
                            final double field_70165_t = Wrapper.player().field_70165_t;
                            final Wrapper instance6 = Wrapper.INSTANCE;
                            final double n = Wrapper.player().field_70163_u + 0.0627;
                            final Wrapper instance7 = Wrapper.INSTANCE;
                            instance4.sendPacket((Packet)new CPacketPlayer.Position(field_70165_t, n, Wrapper.player().field_70161_v, false));
                            final Wrapper instance8 = Wrapper.INSTANCE;
                            final Wrapper instance9 = Wrapper.INSTANCE;
                            final double field_70165_t2 = Wrapper.player().field_70165_t;
                            final Wrapper instance10 = Wrapper.INSTANCE;
                            final double field_70163_u = Wrapper.player().field_70163_u;
                            final Wrapper instance11 = Wrapper.INSTANCE;
                            instance8.sendPacket((Packet)new CPacketPlayer.Position(field_70165_t2, field_70163_u, Wrapper.player().field_70161_v, false));
                            final Entity entity = attack.func_149564_a((World)Wrapper.INSTANCE.world());
                            if (entity != null) {
                                final Wrapper instance12 = Wrapper.INSTANCE;
                                Wrapper.player().func_71009_b(entity);
                            }
                            this.timer.setLastMS();
                            this.cancelSomePackets = true;
                        }
                    }
                    else {
                        final Divine instance13 = Divine.instance;
                        if (Divine.settingsManager.getSettingByName(this, "Mode").getValString().equals("Jump")) {
                            final Divine instance14 = Divine.instance;
                            this.suff = Divine.settingsManager.getSettingByName(this, "Mode").getValString();
                        }
                    }
                    if (this.canJump()) {
                        final Wrapper instance15 = Wrapper.INSTANCE;
                        Wrapper.player().func_70664_aZ();
                    }
                }
            }
            else {
                final Divine instance16 = Divine.instance;
                if (Divine.settingsManager.getSettingByName(this, "Mode").getValString().equals("Packet") && packet instanceof CPacketPlayer) {
                    final Divine instance17 = Divine.instance;
                    this.suff = Divine.settingsManager.getSettingByName(this, "Mode").getValString();
                    if (this.cancelSomePackets) {
                        return this.cancelSomePackets = false;
                    }
                }
            }
        }
        return true;
    }
    
    boolean canJump() {
        final Wrapper instance = Wrapper.INSTANCE;
        if (Wrapper.player().func_70617_f_()) {
            return false;
        }
        final Wrapper instance2 = Wrapper.INSTANCE;
        if (Wrapper.player().func_70090_H()) {
            return false;
        }
        final Wrapper instance3 = Wrapper.INSTANCE;
        if (Wrapper.player().func_180799_ab()) {
            return false;
        }
        final Wrapper instance4 = Wrapper.INSTANCE;
        if (Wrapper.player().func_70093_af()) {
            return false;
        }
        final Wrapper instance5 = Wrapper.INSTANCE;
        if (Wrapper.player().func_184218_aH()) {
            return false;
        }
        final Wrapper instance6 = Wrapper.INSTANCE;
        return !Wrapper.player().func_70644_a(MobEffects.field_76440_q);
    }
}
