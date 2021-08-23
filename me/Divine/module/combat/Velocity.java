package me.Divine.module.combat;

import me.Divine.module.*;
import me.Divine.*;
import me.Divine.settings.*;
import net.minecraftforge.event.entity.living.*;
import net.minecraft.client.entity.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class Velocity extends Module
{
    public Velocity() {
        super("Velocity", "", Category.COMBAT);
        final Divine instance = Divine.instance;
        Divine.settingsManager.rSetting(new Setting("Horizontal", this, 90.0, 0.0, 100.0, true));
        final Divine instance2 = Divine.instance;
        Divine.settingsManager.rSetting(new Setting("Vertical", this, 100.0, 0.0, 100.0, true));
    }
    
    @SubscribeEvent
    public void onLivingUpdate(final LivingEvent.LivingUpdateEvent e) {
        if (Velocity.mc.field_71439_g == null) {
            return;
        }
        final Divine instance = Divine.instance;
        final float horizontal = (float)Divine.settingsManager.getSettingByName(this, "Horizontal").getValDouble();
        final Divine instance2 = Divine.instance;
        final float vertical = (float)Divine.settingsManager.getSettingByName(this, "Vertical").getValDouble();
        if (Velocity.mc.field_71439_g.field_70737_aN == Velocity.mc.field_71439_g.field_70738_aO && Velocity.mc.field_71439_g.field_70738_aO > 0) {
            final EntityPlayerSP field_71439_g = Velocity.mc.field_71439_g;
            field_71439_g.field_70159_w *= horizontal / 100.0f;
            final EntityPlayerSP field_71439_g2 = Velocity.mc.field_71439_g;
            field_71439_g2.field_70181_x *= vertical / 100.0f;
            final EntityPlayerSP field_71439_g3 = Velocity.mc.field_71439_g;
            field_71439_g3.field_70179_y *= horizontal / 100.0f;
        }
    }
    
    @Override
    public void onEnable() {
        super.onEnable();
    }
    
    @Override
    public void onDisable() {
        super.onDisable();
    }
}
