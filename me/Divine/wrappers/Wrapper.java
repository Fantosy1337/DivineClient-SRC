package me.Divine.wrappers;

import net.minecraft.client.*;
import net.minecraft.client.entity.*;
import net.minecraft.client.settings.*;
import net.minecraft.client.gui.*;
import net.minecraft.network.*;
import net.minecraft.entity.player.*;
import net.minecraft.client.multiplayer.*;

public class Wrapper
{
    public static volatile Wrapper INSTANCE;
    
    public Minecraft mc() {
        return Minecraft.func_71410_x();
    }
    
    public static EntityPlayerSP player() {
        return Wrapper.INSTANCE.mc().field_71439_g;
    }
    
    public WorldClient world() {
        return Wrapper.INSTANCE.mc().field_71441_e;
    }
    
    public GameSettings mcSettings() {
        return Wrapper.INSTANCE.mc().field_71474_y;
    }
    
    public FontRenderer fontRenderer() {
        return Wrapper.INSTANCE.mc().field_71466_p;
    }
    
    public void sendPacket(final Packet packet) {
        player().field_71174_a.func_147297_a(packet);
    }
    
    public InventoryPlayer inventory() {
        return player().field_71071_by;
    }
    
    public PlayerControllerMP controller() {
        return Wrapper.INSTANCE.mc().field_71442_b;
    }
    
    static {
        Wrapper.INSTANCE = new Wrapper();
    }
}
