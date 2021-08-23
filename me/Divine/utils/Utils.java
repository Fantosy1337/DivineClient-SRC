package me.Divine.utils;

import me.Divine.wrappers.*;
import java.awt.*;
import java.awt.datatransfer.*;
import net.minecraft.entity.player.*;
import net.minecraft.inventory.*;
import net.minecraft.client.multiplayer.*;
import net.minecraft.potion.*;
import java.util.*;
import net.minecraft.network.*;
import net.minecraft.network.play.client.*;
import net.minecraft.client.entity.*;
import net.minecraft.item.*;
import net.minecraft.client.gui.*;
import java.math.*;
import net.minecraft.util.*;
import net.minecraft.entity.projectile.*;
import net.minecraft.client.*;
import me.Divine.utils.system.*;
import net.minecraft.util.math.*;
import java.lang.reflect.*;
import net.minecraft.world.*;
import net.minecraft.block.*;
import net.minecraft.entity.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.monster.*;

public class Utils
{
    public static boolean lookChanged;
    public static float[] rotationsToBlock;
    private static final Random RANDOM;
    
    public static boolean nullCheck() {
        final Wrapper instance = Wrapper.INSTANCE;
        return Wrapper.player() == null || Wrapper.INSTANCE.world() == null;
    }
    
    public static void copy(final String content) {
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(content), null);
    }
    
    public static int random(final int min, final int max) {
        return Utils.RANDOM.nextInt(max - min) + min;
    }
    
    public static Vec3d getRandomCenter(final AxisAlignedBB bb) {
        return new Vec3d(bb.field_72340_a + (bb.field_72336_d - bb.field_72340_a) * 0.8 * Math.random(), bb.field_72338_b + (bb.field_72337_e - bb.field_72338_b) * Math.random() + 0.1 * Math.random(), bb.field_72339_c + (bb.field_72334_f - bb.field_72339_c) * 0.8 * Math.random());
    }
    
    public static boolean isMoving(final Entity e) {
        return e.field_70159_w != 0.0 && e.field_70179_y != 0.0 && (e.field_70181_x != 0.0 || e.field_70181_x > 0.0);
    }
    
    public static boolean canBeClicked(final BlockPos pos) {
        return BlockUtils.getBlock(pos).func_176209_a(BlockUtils.getState(pos), false);
    }
    
    public static Vec3d getEyesPos() {
        final Wrapper instance = Wrapper.INSTANCE;
        final double field_70165_t = Wrapper.player().field_70165_t;
        final Wrapper instance2 = Wrapper.INSTANCE;
        final double field_70163_u = Wrapper.player().field_70163_u;
        final Wrapper instance3 = Wrapper.INSTANCE;
        final double n = field_70163_u + Wrapper.player().func_70047_e();
        final Wrapper instance4 = Wrapper.INSTANCE;
        return new Vec3d(field_70165_t, n, Wrapper.player().field_70161_v);
    }
    
    public static void faceVectorPacketInstant(final Vec3d vec) {
        Utils.rotationsToBlock = getNeededRotations(vec);
    }
    
    public static List<Entity> getEntityList() {
        return (List<Entity>)Wrapper.INSTANCE.world().func_72910_y();
    }
    
    public static List<EntityPlayer> getPlayersList() {
        return (List<EntityPlayer>)Wrapper.INSTANCE.world().field_73010_i;
    }
    
    public static boolean isNullOrEmptyStack(final ItemStack stack) {
        return stack == null || stack.func_190926_b();
    }
    
    public static void windowClick(final int windowId, final int slotId, final int mouseButton, final ClickType type) {
        final PlayerControllerMP controller = Wrapper.INSTANCE.controller();
        final Wrapper instance = Wrapper.INSTANCE;
        controller.func_187098_a(windowId, slotId, mouseButton, type, (EntityPlayer)Wrapper.player());
    }
    
    public static void swingMainHand() {
        final Wrapper instance = Wrapper.INSTANCE;
        Wrapper.player().func_184609_a(EnumHand.MAIN_HAND);
    }
    
    public static void attack(final Entity entity) {
        final PlayerControllerMP controller = Wrapper.INSTANCE.controller();
        final Wrapper instance = Wrapper.INSTANCE;
        controller.func_78764_a((EntityPlayer)Wrapper.player(), entity);
    }
    
    public static void addEffect(final int id, final int duration, final int amplifier) {
        final Wrapper instance = Wrapper.INSTANCE;
        Wrapper.player().func_70690_d(new PotionEffect(Potion.func_188412_a(id), duration, amplifier));
    }
    
    public static void removeEffect(final int id) {
        final Wrapper instance = Wrapper.INSTANCE;
        Wrapper.player().func_184589_d(Potion.func_188412_a(id));
    }
    
    public static void clearEffects() {
        final Wrapper instance = Wrapper.INSTANCE;
        for (final PotionEffect effect : Wrapper.player().func_70651_bq()) {
            final Wrapper instance2 = Wrapper.INSTANCE;
            Wrapper.player().func_184589_d(effect.func_188419_a());
        }
    }
    
    public static double[] teleportToPosition(final double[] startPosition, final double[] endPosition, final double setOffset, final double slack, final boolean extendOffset, final boolean onGround) {
        boolean wasSneaking = false;
        final Wrapper instance = Wrapper.INSTANCE;
        if (Wrapper.player().func_70093_af()) {
            wasSneaking = true;
        }
        double startX = startPosition[0];
        double startY = startPosition[1];
        double startZ = startPosition[2];
        final double endX = endPosition[0];
        final double endY = endPosition[1];
        final double endZ = endPosition[2];
        double distance = Math.abs(startX - startY) + Math.abs(startY - endY) + Math.abs(startZ - endZ);
        int count = 0;
        while (distance > slack) {
            distance = Math.abs(startX - endX) + Math.abs(startY - endY) + Math.abs(startZ - endZ);
            if (count > 120) {
                break;
            }
            final double offset = (extendOffset && (count & 0x1) == 0x0) ? (setOffset + 0.15) : setOffset;
            final double diffX = startX - endX;
            final double diffY = startY - endY;
            final double diffZ = startZ - endZ;
            if (diffX < 0.0) {
                if (Math.abs(diffX) > offset) {
                    startX += offset;
                }
                else {
                    startX += Math.abs(diffX);
                }
            }
            if (diffX > 0.0) {
                if (Math.abs(diffX) > offset) {
                    startX -= offset;
                }
                else {
                    startX -= Math.abs(diffX);
                }
            }
            if (diffY < 0.0) {
                if (Math.abs(diffY) > offset) {
                    startY += offset;
                }
                else {
                    startY += Math.abs(diffY);
                }
            }
            if (diffY > 0.0) {
                if (Math.abs(diffY) > offset) {
                    startY -= offset;
                }
                else {
                    startY -= Math.abs(diffY);
                }
            }
            if (diffZ < 0.0) {
                if (Math.abs(diffZ) > offset) {
                    startZ += offset;
                }
                else {
                    startZ += Math.abs(diffZ);
                }
            }
            if (diffZ > 0.0) {
                if (Math.abs(diffZ) > offset) {
                    startZ -= offset;
                }
                else {
                    startZ -= Math.abs(diffZ);
                }
            }
            if (wasSneaking) {
                final Wrapper instance2 = Wrapper.INSTANCE;
                final Wrapper instance3 = Wrapper.INSTANCE;
                instance2.sendPacket((Packet)new CPacketEntityAction((Entity)Wrapper.player(), CPacketEntityAction.Action.STOP_SNEAKING));
            }
            Wrapper.INSTANCE.mc().func_147114_u().func_147298_b().func_179290_a((Packet)new CPacketPlayer.Position(startX, startY, startZ, onGround));
            ++count;
        }
        if (wasSneaking) {
            final Wrapper instance4 = Wrapper.INSTANCE;
            final Wrapper instance5 = Wrapper.INSTANCE;
            instance4.sendPacket((Packet)new CPacketEntityAction((Entity)Wrapper.player(), CPacketEntityAction.Action.START_SNEAKING));
        }
        return new double[] { startX, startY, startZ };
    }
    
    public static void selfDamage(final double posY) {
        final Wrapper instance = Wrapper.INSTANCE;
        if (!Wrapper.player().field_70122_E) {
            return;
        }
        for (int i = 0; i <= 64.0; ++i) {
            final Wrapper instance2 = Wrapper.INSTANCE;
            final Wrapper instance3 = Wrapper.INSTANCE;
            final double field_70165_t = Wrapper.player().field_70165_t;
            final Wrapper instance4 = Wrapper.INSTANCE;
            final double n = Wrapper.player().field_70163_u + posY;
            final Wrapper instance5 = Wrapper.INSTANCE;
            instance2.sendPacket((Packet)new CPacketPlayer.Position(field_70165_t, n, Wrapper.player().field_70161_v, false));
            final Wrapper instance6 = Wrapper.INSTANCE;
            final Wrapper instance7 = Wrapper.INSTANCE;
            final double field_70165_t2 = Wrapper.player().field_70165_t;
            final Wrapper instance8 = Wrapper.INSTANCE;
            final double field_70163_u = Wrapper.player().field_70163_u;
            final Wrapper instance9 = Wrapper.INSTANCE;
            instance6.sendPacket((Packet)new CPacketPlayer.Position(field_70165_t2, field_70163_u, Wrapper.player().field_70161_v, i == 64.0));
        }
        final Wrapper instance10 = Wrapper.INSTANCE;
        final EntityPlayerSP player = Wrapper.player();
        player.field_70159_w *= 0.2;
        final Wrapper instance11 = Wrapper.INSTANCE;
        final EntityPlayerSP player2 = Wrapper.player();
        player2.field_70179_y *= 0.2;
        swingMainHand();
    }
    
    public static String getPlayerName(final EntityPlayer player) {
        return (player.func_146103_bH() != null) ? player.func_146103_bH().getName() : "null";
    }
    
    public static boolean isPlayer(final Entity entity) {
        if (entity instanceof EntityPlayer) {
            final EntityPlayer player = (EntityPlayer)entity;
            final String entityName = getPlayerName(player);
            final Wrapper instance = Wrapper.INSTANCE;
            final String playerName = getPlayerName((EntityPlayer)Wrapper.player());
            if (entityName.equals(playerName)) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean checkEnemyNameColor(final EntityLivingBase entity) {
        final String name = entity.func_145748_c_().func_150254_d();
        final Wrapper instance = Wrapper.INSTANCE;
        return !getEntityNameColor((EntityLivingBase)Wrapper.player()).equals(getEntityNameColor(entity));
    }
    
    public static String getEntityNameColor(final EntityLivingBase entity) {
        final String name = entity.func_145748_c_().func_150254_d();
        if (name.contains("§")) {
            if (name.contains("§1")) {
                return "§1";
            }
            if (name.contains("§2")) {
                return "§2";
            }
            if (name.contains("§3")) {
                return "§3";
            }
            if (name.contains("§4")) {
                return "§4";
            }
            if (name.contains("§5")) {
                return "§5";
            }
            if (name.contains("§6")) {
                return "§6";
            }
            if (name.contains("§7")) {
                return "§7";
            }
            if (name.contains("§8")) {
                return "§8";
            }
            if (name.contains("§9")) {
                return "§9";
            }
            if (name.contains("§0")) {
                return "§0";
            }
            if (name.contains("§e")) {
                return "§e";
            }
            if (name.contains("§d")) {
                return "§d";
            }
            if (name.contains("§a")) {
                return "§a";
            }
            if (name.contains("§b")) {
                return "§b";
            }
            if (name.contains("§c")) {
                return "§c";
            }
            if (name.contains("§f")) {
                return "§f";
            }
        }
        return "null";
    }
    
    public static int getPlayerArmorColor(final EntityPlayer player, final ItemStack stack) {
        if (player == null || stack == null || stack.func_77973_b() == null || !(stack.func_77973_b() instanceof ItemArmor)) {
            return -1;
        }
        final ItemArmor itemArmor = (ItemArmor)stack.func_77973_b();
        if (itemArmor == null || itemArmor.func_82812_d() != ItemArmor.ArmorMaterial.LEATHER) {
            return -1;
        }
        return itemArmor.func_82814_b(stack);
    }
    
    public static boolean checkEnemyColor(final EntityPlayer enemy) {
        final int colorEnemy0 = getPlayerArmorColor(enemy, enemy.field_71071_by.func_70440_f(0));
        final int colorEnemy2 = getPlayerArmorColor(enemy, enemy.field_71071_by.func_70440_f(1));
        final int colorEnemy3 = getPlayerArmorColor(enemy, enemy.field_71071_by.func_70440_f(2));
        final int colorEnemy4 = getPlayerArmorColor(enemy, enemy.field_71071_by.func_70440_f(3));
        final Wrapper instance = Wrapper.INSTANCE;
        final int colorPlayer0 = getPlayerArmorColor((EntityPlayer)Wrapper.player(), Wrapper.INSTANCE.inventory().func_70440_f(0));
        final Wrapper instance2 = Wrapper.INSTANCE;
        final int colorPlayer2 = getPlayerArmorColor((EntityPlayer)Wrapper.player(), Wrapper.INSTANCE.inventory().func_70440_f(1));
        final Wrapper instance3 = Wrapper.INSTANCE;
        final int colorPlayer3 = getPlayerArmorColor((EntityPlayer)Wrapper.player(), Wrapper.INSTANCE.inventory().func_70440_f(2));
        final Wrapper instance4 = Wrapper.INSTANCE;
        final int colorPlayer4 = getPlayerArmorColor((EntityPlayer)Wrapper.player(), Wrapper.INSTANCE.inventory().func_70440_f(3));
        return (colorEnemy0 != colorPlayer0 || colorPlayer0 == -1 || colorEnemy0 == 1) && (colorEnemy2 != colorPlayer2 || colorPlayer2 == -1 || colorEnemy2 == 1) && (colorEnemy3 != colorPlayer3 || colorPlayer3 == -1 || colorEnemy3 == 1) && (colorEnemy4 != colorPlayer4 || colorPlayer4 == -1 || colorEnemy4 == 1);
    }
    
    public static boolean screenCheck() {
        return !(Wrapper.INSTANCE.mc().field_71462_r instanceof GuiScreen);
    }
    
    public static double round(final double value, final int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
    
    public static EntityLivingBase getWorldEntityByName(final String name) {
        EntityLivingBase entity = null;
        for (final Object object : getEntityList()) {
            if (object instanceof EntityLivingBase) {
                final EntityLivingBase entityForCheck = (EntityLivingBase)object;
                if (!entityForCheck.func_70005_c_().contains(name)) {
                    continue;
                }
                entity = entityForCheck;
            }
        }
        return entity;
    }
    
    public static float[] getDirectionToBlock(final int var0, final int var1, final int var2, final EnumFacing var3) {
        final EntityEgg var4 = new EntityEgg((World)Wrapper.INSTANCE.world());
        var4.field_70165_t = var0 + 0.5;
        var4.field_70163_u = var1 + 0.5;
        var4.field_70161_v = var2 + 0.5;
        final EntityEgg entityEgg = var4;
        entityEgg.field_70165_t += var3.func_176730_m().func_177958_n() * 0.25;
        final EntityEgg entityEgg2 = var4;
        entityEgg2.field_70163_u += var3.func_176730_m().func_177956_o() * 0.25;
        final EntityEgg entityEgg3 = var4;
        entityEgg3.field_70161_v += var3.func_176730_m().func_177952_p() * 0.25;
        return getDirectionToEntity((Entity)var4);
    }
    
    private static float[] getDirectionToEntity(final Entity var0) {
        final float[] array = new float[2];
        final int n = 0;
        final float yaw = getYaw(var0);
        final Wrapper instance = Wrapper.INSTANCE;
        array[n] = yaw + Wrapper.player().field_70177_z;
        final int n2 = 1;
        final float pitch = getPitch(var0);
        final Wrapper instance2 = Wrapper.INSTANCE;
        array[n2] = pitch + Wrapper.player().field_70125_A;
        return array;
    }
    
    public static float getPitch(final Entity entity) {
        final double field_70165_t = entity.field_70165_t;
        final Wrapper instance = Wrapper.INSTANCE;
        final double x = field_70165_t - Wrapper.player().field_70165_t;
        final double field_70163_u = entity.field_70163_u;
        final Wrapper instance2 = Wrapper.INSTANCE;
        double y = field_70163_u - Wrapper.player().field_70163_u;
        final double field_70161_v = entity.field_70161_v;
        final Wrapper instance3 = Wrapper.INSTANCE;
        final double z = field_70161_v - Wrapper.player().field_70161_v;
        final double n = y;
        final Wrapper instance4 = Wrapper.INSTANCE;
        y = n / Wrapper.player().func_70032_d(entity);
        double pitch = Math.asin(y) * 57.29577951308232;
        pitch = -pitch;
        return (float)pitch;
    }
    
    public static float getYaw(final Entity entity) {
        final double field_70165_t = entity.field_70165_t;
        final Wrapper instance = Wrapper.INSTANCE;
        final double x = field_70165_t - Wrapper.player().field_70165_t;
        final double field_70163_u = entity.field_70163_u;
        final Wrapper instance2 = Wrapper.INSTANCE;
        final double y = field_70163_u - Wrapper.player().field_70163_u;
        final double field_70161_v = entity.field_70161_v;
        final Wrapper instance3 = Wrapper.INSTANCE;
        final double z = field_70161_v - Wrapper.player().field_70161_v;
        double yaw = Math.atan2(x, z) * 57.29577951308232;
        yaw = -yaw;
        return (float)yaw;
    }
    
    public static float[] getNeededRotations(final Vec3d vec) {
        final Vec3d eyesPos = getEyesPos();
        final double diffX = vec.field_72450_a - eyesPos.field_72450_a;
        final double diffY = vec.field_72448_b - eyesPos.field_72448_b;
        final double diffZ = vec.field_72449_c - eyesPos.field_72449_c;
        final double diffXZ = Math.sqrt(diffX * diffX + diffZ * diffZ);
        final float yaw = (float)Math.toDegrees(Math.atan2(diffZ, diffX)) - 90.0f;
        final float pitch = (float)(-Math.toDegrees(Math.atan2(diffY, diffXZ)));
        final float[] array = new float[2];
        final int n = 0;
        final Wrapper instance = Wrapper.INSTANCE;
        final float field_70177_z = Wrapper.player().field_70177_z;
        final float n2 = yaw;
        final Wrapper instance2 = Wrapper.INSTANCE;
        array[n] = field_70177_z + MathHelper.func_76142_g(n2 - Wrapper.player().field_70177_z);
        final int n3 = 1;
        final Wrapper instance3 = Wrapper.INSTANCE;
        final float field_70125_A = Wrapper.player().field_70125_A;
        final float n4 = pitch;
        final Wrapper instance4 = Wrapper.INSTANCE;
        array[n3] = field_70125_A + MathHelper.func_76142_g(n4 - Wrapper.player().field_70125_A);
        return array;
    }
    
    public static float getDirection() {
        final Wrapper instance = Wrapper.INSTANCE;
        float var1 = Wrapper.player().field_70177_z;
        final Wrapper instance2 = Wrapper.INSTANCE;
        if (Wrapper.player().field_191988_bg < 0.0f) {
            var1 += 180.0f;
        }
        float forward = 1.0f;
        final Wrapper instance3 = Wrapper.INSTANCE;
        if (Wrapper.player().field_191988_bg < 0.0f) {
            forward = -0.5f;
        }
        else {
            final Wrapper instance4 = Wrapper.INSTANCE;
            if (Wrapper.player().field_191988_bg > 0.0f) {
                forward = 0.5f;
            }
        }
        final Wrapper instance5 = Wrapper.INSTANCE;
        if (Wrapper.player().field_70702_br > 0.0f) {
            var1 -= 90.0f * forward;
        }
        final Wrapper instance6 = Wrapper.INSTANCE;
        if (Wrapper.player().field_70702_br < 0.0f) {
            var1 += 90.0f * forward;
        }
        var1 *= 0.017453292f;
        return var1;
    }
    
    public static void faceVectorPacket(final Vec3d vec) {
        final float[] rotations = getNeededRotations(vec);
        final EntityPlayerSP pl = Minecraft.func_71410_x().field_71439_g;
        final float preYaw = pl.field_70177_z;
        final float prePitch = pl.field_70125_A;
        pl.field_70177_z = rotations[0];
        pl.field_70125_A = rotations[1];
        try {
            final Method onUpdateWalkingPlayer = pl.getClass().getDeclaredMethod(Mapping.onUpdateWalkingPlayer, (Class<?>[])new Class[0]);
            onUpdateWalkingPlayer.setAccessible(true);
            onUpdateWalkingPlayer.invoke(pl, new Object[0]);
        }
        catch (Exception ex) {}
        pl.field_70177_z = preYaw;
        pl.field_70125_A = prePitch;
    }
    
    public static boolean placeBlockScaffold(final BlockPos pos) {
        final Wrapper instance = Wrapper.INSTANCE;
        final double field_70165_t = Wrapper.player().field_70165_t;
        final Wrapper instance2 = Wrapper.INSTANCE;
        final double field_70163_u = Wrapper.player().field_70163_u;
        final Wrapper instance3 = Wrapper.INSTANCE;
        final double n = field_70163_u + Wrapper.player().func_70047_e();
        final Wrapper instance4 = Wrapper.INSTANCE;
        final Vec3d eyesPos = new Vec3d(field_70165_t, n, Wrapper.player().field_70161_v);
        EnumFacing[] values;
        for (int length = (values = EnumFacing.values()).length, i = 0; i < length; ++i) {
            final EnumFacing side = values[i];
            final BlockPos neighbor = pos.func_177972_a(side);
            final EnumFacing side2 = side.func_176734_d();
            if (eyesPos.func_72436_e(new Vec3d((Vec3i)pos).func_72441_c(0.5, 0.5, 0.5)) < eyesPos.func_72436_e(new Vec3d((Vec3i)neighbor).func_72441_c(0.5, 0.5, 0.5)) && canBeClicked(neighbor)) {
                final Vec3d hitVec = new Vec3d((Vec3i)neighbor).func_72441_c(0.5, 0.5, 0.5).func_178787_e(new Vec3d(side2.func_176730_m()).func_186678_a(0.5));
                if (eyesPos.func_72436_e(hitVec) <= 18.0625) {
                    faceVectorPacketInstant(hitVec);
                    swingMainHand();
                    final PlayerControllerMP controller = Wrapper.INSTANCE.controller();
                    final Wrapper instance5 = Wrapper.INSTANCE;
                    controller.func_187099_a(Wrapper.player(), Wrapper.INSTANCE.world(), neighbor, side2, hitVec, EnumHand.MAIN_HAND);
                    try {
                        final Field f = Minecraft.class.getDeclaredField(Mapping.rightClickDelayTimer);
                        f.setAccessible(true);
                        f.set(Wrapper.INSTANCE.mc(), 4);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                    return true;
                }
            }
        }
        return false;
    }
    
    public static boolean isInsideBlock(final EntityLivingBase entity) {
        for (int x = MathHelper.func_76128_c(entity.func_174813_aQ().field_72340_a); x < MathHelper.func_76128_c(entity.func_174813_aQ().field_72336_d) + 1; ++x) {
            for (int y = MathHelper.func_76128_c(entity.func_174813_aQ().field_72338_b); y < MathHelper.func_76128_c(entity.func_174813_aQ().field_72337_e) + 1; ++y) {
                for (int z = MathHelper.func_76128_c(entity.func_174813_aQ().field_72339_c); z < MathHelper.func_76128_c(entity.func_174813_aQ().field_72334_f) + 1; ++z) {
                    final Block block = BlockUtils.getBlock(new BlockPos(x, y, z));
                    final AxisAlignedBB boundingBox;
                    if (block != null && !(block instanceof BlockAir) && (boundingBox = block.func_180646_a(BlockUtils.getState(new BlockPos(x, y, z)), (IBlockAccess)Wrapper.INSTANCE.world(), new BlockPos(x, y, z))) != null && entity.func_174813_aQ().func_72326_a(boundingBox)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public static boolean isBlockEdge(final EntityLivingBase entity) {
        return Wrapper.INSTANCE.world().func_184144_a((Entity)entity, entity.func_174813_aQ().func_72317_d(0.0, -0.5, 0.0).func_72321_a(0.001, 0.0, 0.001)).isEmpty() && entity.field_70122_E;
    }
    
    public static void faceEntity(final EntityLivingBase entity) {
        if (entity == null) {
            return;
        }
        final double field_70165_t = entity.field_70165_t;
        final Wrapper instance = Wrapper.INSTANCE;
        final double d0 = field_70165_t - Wrapper.player().field_70165_t;
        final double field_70163_u = entity.field_70163_u;
        final Wrapper instance2 = Wrapper.INSTANCE;
        final double d2 = field_70163_u - Wrapper.player().field_70163_u;
        final double field_70161_v = entity.field_70161_v;
        final Wrapper instance3 = Wrapper.INSTANCE;
        final double d3 = field_70161_v - Wrapper.player().field_70161_v;
        final Wrapper instance4 = Wrapper.INSTANCE;
        final double field_70163_u2 = Wrapper.player().field_70163_u;
        final Wrapper instance5 = Wrapper.INSTANCE;
        final double d4 = field_70163_u2 + Wrapper.player().func_70047_e() - (entity.field_70163_u + entity.func_70047_e());
        final double d5 = MathHelper.func_76133_a(d0 * d0 + d3 * d3);
        final float f = (float)(Math.atan2(d3, d0) * 180.0 / 3.141592653589793) - 90.0f;
        final float f2 = (float)(-(Math.atan2(d4, d5) * 180.0 / 3.141592653589793));
        final Wrapper instance6 = Wrapper.INSTANCE;
        Wrapper.player().field_70177_z = f;
        final Wrapper instance7 = Wrapper.INSTANCE;
        Wrapper.player().field_70125_A = f2;
    }
    
    public static void assistFaceEntity(final Entity entity, final float yaw, final float pitch) {
        if (entity == null) {
            return;
        }
        final double field_70165_t = entity.field_70165_t;
        final Wrapper instance = Wrapper.INSTANCE;
        final double diffX = field_70165_t - Wrapper.player().field_70165_t;
        final double field_70161_v = entity.field_70161_v;
        final Wrapper instance2 = Wrapper.INSTANCE;
        final double diffZ = field_70161_v - Wrapper.player().field_70161_v;
        double yDifference;
        if (entity instanceof EntityLivingBase) {
            final EntityLivingBase entityLivingBase = (EntityLivingBase)entity;
            final double n = entityLivingBase.field_70163_u + entityLivingBase.func_70047_e();
            final Wrapper instance3 = Wrapper.INSTANCE;
            final double field_70163_u = Wrapper.player().field_70163_u;
            final Wrapper instance4 = Wrapper.INSTANCE;
            yDifference = n - (field_70163_u + Wrapper.player().func_70047_e());
        }
        else {
            final double n2 = (entity.func_174813_aQ().field_72338_b + entity.func_174813_aQ().field_72337_e) / 2.0;
            final Wrapper instance5 = Wrapper.INSTANCE;
            final double field_70163_u2 = Wrapper.player().field_70163_u;
            final Wrapper instance6 = Wrapper.INSTANCE;
            yDifference = n2 - (field_70163_u2 + Wrapper.player().func_70047_e());
        }
        final double dist = MathHelper.func_76133_a(diffX * diffX + diffZ * diffZ);
        final float rotationYaw = (float)(Math.atan2(diffZ, diffX) * 180.0 / 3.141592653589793) - 90.0f;
        final float rotationPitch = (float)(-(Math.atan2(yDifference, dist) * 180.0 / 3.141592653589793));
        if (yaw > 0.0f) {
            final Wrapper instance7 = Wrapper.INSTANCE;
            final EntityPlayerSP player = Wrapper.player();
            final Wrapper instance8 = Wrapper.INSTANCE;
            player.field_70177_z = updateRotation(Wrapper.player().field_70177_z, rotationYaw, yaw / 4.0f);
        }
        if (pitch > 0.0f) {
            final Wrapper instance9 = Wrapper.INSTANCE;
            final EntityPlayerSP player2 = Wrapper.player();
            final Wrapper instance10 = Wrapper.INSTANCE;
            player2.field_70125_A = updateRotation(Wrapper.player().field_70125_A, rotationPitch, pitch / 4.0f);
        }
    }
    
    public static float updateRotation(final float p_70663_1_, final float p_70663_2_, final float p_70663_3_) {
        float var4 = MathHelper.func_76142_g(p_70663_2_ - p_70663_1_);
        if (var4 > p_70663_3_) {
            var4 = p_70663_3_;
        }
        if (var4 < -p_70663_3_) {
            var4 = -p_70663_3_;
        }
        return p_70663_1_ + var4;
    }
    
    public static int getDistanceFromMouse(final EntityLivingBase entity) {
        final float[] neededRotations = getRotationsNeeded((Entity)entity);
        if (neededRotations != null) {
            final Wrapper instance = Wrapper.INSTANCE;
            final float neededYaw = Wrapper.player().field_70177_z - neededRotations[0];
            final Wrapper instance2 = Wrapper.INSTANCE;
            final float neededPitch = Wrapper.player().field_70125_A - neededRotations[1];
            final float distanceFromMouse = MathHelper.func_76129_c(neededYaw * neededYaw + neededPitch * neededPitch * 2.0f);
            return (int)distanceFromMouse;
        }
        return -1;
    }
    
    public static float[] getSmoothNeededRotations(final Vec3d vec, final float yaw, final float pitch) {
        final Vec3d eyesPos = getEyesPos();
        final double diffX = vec.field_72450_a - eyesPos.field_72450_a;
        final double diffY = vec.field_72448_b - eyesPos.field_72448_b;
        final double diffZ = vec.field_72449_c - eyesPos.field_72449_c;
        final double diffXZ = Math.sqrt(diffX * diffX + diffZ * diffZ);
        final float rotationYaw = (float)Math.toDegrees(Math.atan2(diffZ, diffX)) - 90.0f;
        final float rotationPitch = (float)(-Math.toDegrees(Math.atan2(diffY, diffXZ)));
        final float[] array = new float[2];
        final int n = 0;
        final Wrapper instance = Wrapper.INSTANCE;
        array[n] = updateRotation(Wrapper.player().field_70177_z, rotationYaw, yaw / 4.0f);
        final int n2 = 1;
        final Wrapper instance2 = Wrapper.INSTANCE;
        array[n2] = updateRotation(Wrapper.player().field_70125_A, rotationPitch, pitch / 4.0f);
        return array;
    }
    
    public static float[] getRotationsNeeded(final Entity entity) {
        if (entity == null) {
            return null;
        }
        final double diffX = entity.field_70165_t - Wrapper.INSTANCE.mc().field_71439_g.field_70165_t;
        final double diffZ = entity.field_70161_v - Wrapper.INSTANCE.mc().field_71439_g.field_70161_v;
        double diffY;
        if (entity instanceof EntityLivingBase) {
            final EntityLivingBase entityLivingBase = (EntityLivingBase)entity;
            diffY = entityLivingBase.field_70163_u + entityLivingBase.func_70047_e() - (Wrapper.INSTANCE.mc().field_71439_g.field_70163_u + Wrapper.INSTANCE.mc().field_71439_g.func_70047_e());
        }
        else {
            diffY = (entity.func_174813_aQ().field_72338_b + entity.func_174813_aQ().field_72337_e) / 2.0 - (Wrapper.INSTANCE.mc().field_71439_g.field_70163_u + Wrapper.INSTANCE.mc().field_71439_g.func_70047_e());
        }
        final double dist = MathHelper.func_76133_a(diffX * diffX + diffZ * diffZ);
        final float yaw = (float)(Math.atan2(diffZ, diffX) * 180.0 / 3.141592653589793) - 90.0f;
        final float pitch = (float)(-(Math.atan2(diffY, dist) * 180.0 / 3.141592653589793));
        return new float[] { Wrapper.INSTANCE.mc().field_71439_g.field_70177_z + MathHelper.func_76142_g(yaw - Wrapper.INSTANCE.mc().field_71439_g.field_70177_z), Wrapper.INSTANCE.mc().field_71439_g.field_70125_A + MathHelper.func_76142_g(pitch - Wrapper.INSTANCE.mc().field_71439_g.field_70125_A) };
    }
    
    public static Vec3d getInterpolatedPos(final Entity entity, final float f) {
        return interpolateVec3d(entity.func_174791_d(), new Vec3d(entity.field_70142_S, entity.field_70137_T, entity.field_70136_U), f);
    }
    
    public static boolean isAnimal(final Entity entity) {
        return entity instanceof EntityAgeable || entity instanceof EntityAmbientCreature || entity instanceof EntityWaterMob || entity instanceof EntityGolem;
    }
    
    public static Vec3d interpolateVec3d(final Vec3d vec3d, final Vec3d vec3d2, final float f) {
        return vec3d.func_178788_d(vec3d2).func_186678_a((double)f).func_178787_e(vec3d2);
    }
    
    public static void setEntityBoundingBoxSize(final Entity entity, final float width, final float height) {
        final Entity size = getEntitySize(entity);
        entity.field_70130_N = size.field_70130_N;
        entity.field_70131_O = size.field_70131_O;
        final double d0 = width / 2.0;
        entity.func_174826_a(new AxisAlignedBB(entity.field_70165_t - d0, entity.field_70163_u, entity.field_70161_v - d0, entity.field_70165_t + d0, entity.field_70163_u + height, entity.field_70161_v + d0));
    }
    
    public static void setEntityBoundingBoxSize(final Entity entity) {
        final Entity size = getEntitySize(entity);
        entity.field_70130_N = size.field_70130_N;
        entity.field_70131_O = size.field_70131_O;
        final double d0 = entity.field_70130_N / 2.0;
        entity.func_174826_a(new AxisAlignedBB(entity.field_70165_t - d0, entity.field_70163_u, entity.field_70161_v - d0, entity.field_70165_t + d0, entity.field_70163_u + entity.field_70131_O, entity.field_70161_v + d0));
    }
    
    public static Entity getEntitySize(final Entity entity) {
        final Entity entitySize = entity;
        return entitySize;
    }
    
    static {
        Utils.rotationsToBlock = null;
        RANDOM = new Random();
    }
}
