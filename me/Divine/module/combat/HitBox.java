package me.Divine.module.combat;

import me.Divine.module.*;
import me.Divine.*;
import me.Divine.settings.*;
import net.minecraftforge.fml.common.gameevent.*;
import me.Divine.utils.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.*;
import java.util.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.client.entity.*;
import me.Divine.wrappers.*;

public class HitBox extends Module
{
    public HitBox() {
        super("HitBox", "Increase Hitbox size", Category.COMBAT);
        final Divine instance = Divine.instance;
        Divine.settingsManager.rSetting(new Setting("Width", this, 0.3, 0.1, 3.0, false));
        final Divine instance2 = Divine.instance;
        Divine.settingsManager.rSetting(new Setting("Height", this, 0.3, 0.1, 3.0, false));
    }
    
    @SubscribeEvent
    @Override
    public void onClientTick(final TickEvent.ClientTickEvent event) {
        for (final EntityPlayer player : Utils.getPlayersList()) {
            if (!this.check((EntityLivingBase)player)) {
                continue;
            }
            if (player == null) {
                continue;
            }
            final Divine instance = Divine.instance;
            final float width = (float)Divine.settingsManager.getSettingByName(this, "Width").getValDouble();
            final Divine instance2 = Divine.instance;
            final float height = (float)Divine.settingsManager.getSettingByName(this, "Height").getValDouble();
            Utils.setEntityBoundingBoxSize((Entity)player, width, height);
        }
    }
    
    @Override
    public void onDisable() {
        for (final EntityPlayer player : Utils.getPlayersList()) {
            Utils.setEntityBoundingBoxSize((Entity)player);
        }
        super.onDisable();
    }
    
    public boolean check(final EntityLivingBase entity) {
        if (!(entity instanceof EntityPlayerSP)) {
            final Wrapper instance = Wrapper.INSTANCE;
            if (entity != Wrapper.player() && !entity.field_70128_L) {
                return true;
            }
        }
        return false;
    }
}
