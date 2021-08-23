package me.Divine.utils;

import java.util.*;
import net.minecraft.entity.player.*;

public class EntityBot
{
    private String name;
    private int id;
    private UUID uuid;
    private boolean invisible;
    private boolean ground;
    
    public EntityBot(final EntityPlayer player) {
        this.name = String.valueOf(player.func_146103_bH().getName());
        this.id = player.func_145782_y();
        this.uuid = player.func_146103_bH().getId();
        this.invisible = player.func_82150_aj();
        this.ground = player.field_70122_E;
    }
    
    public int getId() {
        return this.id;
    }
    
    public String getName() {
        return this.name;
    }
    
    public UUID getUuid() {
        return this.uuid;
    }
    
    public boolean isInvisible() {
        return this.invisible;
    }
    
    public boolean isGround() {
        return this.ground;
    }
}
