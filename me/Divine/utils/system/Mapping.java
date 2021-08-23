package me.Divine.utils.system;

import net.minecraft.client.*;

public class Mapping
{
    public static String session;
    public static String yaw;
    public static String pitch;
    public static String rightClickDelayTimer;
    public static String getPlayerInfo;
    public static String playerTextures;
    public static String currentGameType;
    public static String connection;
    public static String blockHitDelay;
    public static String isInWeb;
    public static String curBlockDamageMP;
    public static String isHittingBlock;
    public static String onUpdateWalkingPlayer;
    public static String fire;
    public static String isImmuneToFire;
    
    public static boolean isNotObfuscated() {
        try {
            return Minecraft.class.getDeclaredField("instance") != null;
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    static {
        Mapping.session = (isNotObfuscated() ? "session" : "field_71449_j");
        Mapping.yaw = (isNotObfuscated() ? "yaw" : "field_149476_e");
        Mapping.pitch = (isNotObfuscated() ? "pitch" : "field_149473_f");
        Mapping.rightClickDelayTimer = (isNotObfuscated() ? "rightClickDelayTimer" : "field_71467_ac");
        Mapping.getPlayerInfo = (isNotObfuscated() ? "getPlayerInfo" : "func_175155_b");
        Mapping.playerTextures = (isNotObfuscated() ? "playerTextures" : "field_187107_a");
        Mapping.currentGameType = (isNotObfuscated() ? "currentGameType" : "field_78779_k");
        Mapping.connection = (isNotObfuscated() ? "connection" : "field_78774_b");
        Mapping.blockHitDelay = (isNotObfuscated() ? "blockHitDelay" : "field_78781_i");
        Mapping.isInWeb = (isNotObfuscated() ? "isInWeb" : "field_70134_J");
        Mapping.curBlockDamageMP = (isNotObfuscated() ? "curBlockDamageMP" : "field_78770_f");
        Mapping.isHittingBlock = (isNotObfuscated() ? "isHittingBlock" : "field_78778_j");
        Mapping.onUpdateWalkingPlayer = (isNotObfuscated() ? "onUpdateWalkingPlayer" : "func_175161_p");
        Mapping.fire = (isNotObfuscated() ? "fire" : "field_190534_ay");
        Mapping.isImmuneToFire = (isNotObfuscated() ? "isImmuneToFire" : "field_70178_ae");
    }
}
