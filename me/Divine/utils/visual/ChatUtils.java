package me.Divine.utils.visual;

import me.Divine.wrappers.*;
import net.minecraft.util.text.*;

public class ChatUtils
{
    public static void component(final ITextComponent component) {
        final Wrapper instance = Wrapper.INSTANCE;
        if (Wrapper.player() == null || Wrapper.INSTANCE.mc().field_71456_v.func_146158_b() == null) {
            return;
        }
        Wrapper.INSTANCE.mc().field_71456_v.func_146158_b().func_146227_a(new TextComponentTranslation("", new Object[0]).func_150257_a(component));
    }
    
    public static void message(final Object message) {
        component((ITextComponent)new TextComponentTranslation("§8123§7 " + message, new Object[0]));
    }
    
    public static void warning(final Object message) {
        message("§8[§eWARNING§8]§e " + message);
    }
    
    public static void error(final Object message) {
        message("§8[§4ERROR§8]§c " + message);
    }
}
