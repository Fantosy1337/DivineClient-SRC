package me.Divine.value;

import me.Divine.*;
import me.Divine.module.*;
import net.minecraft.client.*;
import java.util.*;

public class ASDASD
{
    public boolean onPacket(final Object packet, final ASD.Side side) {
        boolean suc = true;
        for (final Module hack : Divine.instance.moduleManager.getModuleList()) {
            if (hack.isToggled()) {
                if (Minecraft.func_71410_x().field_71441_e == null) {
                    continue;
                }
                suc &= hack.onPacket(packet, side);
            }
        }
        return suc;
    }
}
