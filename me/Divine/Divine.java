package me.Divine;

import me.Divine.settings.*;
import me.Divine.clickgui.*;
import me.Divine.Notification.*;
import net.minecraft.client.gui.*;
import net.minecraftforge.common.*;
import net.minecraft.client.*;
import me.Divine.value.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftforge.fml.common.gameevent.*;
import org.lwjgl.input.*;
import me.Divine.module.*;
import java.util.*;

public class Divine
{
    public static Divine instance;
    public static int color;
    public ModuleManager moduleManager;
    public static SettingsManager settingsManager;
    public ClickGui clickGui;
    ASDASD asd;
    private boolean initialized;
    public NotificationManager notificationManager;
    public static final String NAME = "Divine";
    public static final String Build = "b0.1";
    public static final GuiScreen ClickGUi;
    public boolean destructed;
    
    public Divine() {
        this.initialized = false;
        this.notificationManager = new NotificationManager();
        this.destructed = false;
    }
    
    public void init() {
        MinecraftForge.EVENT_BUS.register((Object)this);
        Divine.settingsManager = new SettingsManager();
        this.moduleManager = new ModuleManager();
        this.clickGui = new ClickGui();
        this.asd = new ASDASD();
    }
    
    @SubscribeEvent
    public void fake(final TickEvent.ClientTickEvent a) {
        if (Minecraft.func_71410_x().field_71441_e != null && Minecraft.func_71410_x().field_71439_g != null) {
            if (!this.initialized) {
                new ASD(this.asd);
                this.initialized = true;
            }
            if (Divine.instance.equals(1)) {
                MinecraftForge.EVENT_BUS.register((Object)this);
                Divine.settingsManager = new SettingsManager();
                MinecraftForge.EVENT_BUS.register((Object)Divine.settingsManager);
                this.moduleManager = new ModuleManager();
                MinecraftForge.EVENT_BUS.register((Object)this.moduleManager);
                this.clickGui = new ClickGui();
                MinecraftForge.EVENT_BUS.register((Object)Divine.settingsManager);
            }
        }
        else {
            this.initialized = false;
        }
    }
    
    @SubscribeEvent
    public void key(final InputEvent.KeyInputEvent e) {
        if (Minecraft.func_71410_x().field_71441_e == null || Minecraft.func_71410_x().field_71439_g == null) {
            return;
        }
        try {
            if (Keyboard.isCreated() && Keyboard.getEventKeyState()) {
                final int keyCode = Keyboard.getEventKey();
                if (keyCode <= 0) {
                    return;
                }
                final ModuleManager moduleManager = this.moduleManager;
                for (final Module m : ModuleManager.modules) {
                    if (m.getKey() == keyCode && keyCode > 0) {
                        m.toggle();
                    }
                }
            }
        }
        catch (Exception q) {
            q.printStackTrace();
        }
    }
    
    public void onDestruct() {
        if (Minecraft.func_71410_x().field_71462_r != null && Minecraft.func_71410_x().field_71439_g != null) {
            Minecraft.func_71410_x().field_71439_g.func_71053_j();
        }
        this.destructed = true;
        MinecraftForge.EVENT_BUS.unregister((Object)this);
        int k = 0;
        while (true) {
            final int n = k;
            final ModuleManager moduleManager = this.moduleManager;
            if (n >= ModuleManager.modules.size()) {
                break;
            }
            final ModuleManager moduleManager2 = this.moduleManager;
            final Module m = ModuleManager.modules.get(k);
            MinecraftForge.EVENT_BUS.unregister((Object)m);
            this.moduleManager.getModuleList().remove(m);
            ++k;
        }
        this.moduleManager = null;
        this.clickGui = null;
    }
    
    static {
        ClickGUi = null;
    }
}
