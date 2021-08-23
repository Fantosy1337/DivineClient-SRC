package me.Divine.utils.visual;

import me.Divine.utils.*;
import java.text.*;
import me.Divine.wrappers.*;
import org.lwjgl.opengl.*;
import net.minecraft.entity.*;
import net.minecraft.util.math.*;
import java.awt.*;
import net.minecraft.world.*;
import net.minecraft.client.multiplayer.*;
import net.minecraft.client.entity.*;
import net.minecraft.block.state.*;
import net.minecraft.client.renderer.vertex.*;
import net.minecraft.client.renderer.*;
import java.util.*;
import me.Divine.value.*;

public class RenderUtils
{
    private static final AxisAlignedBB DEFAULT_AABB;
    public static TimerUtils splashTimer;
    public static int splashTickPos;
    public static boolean isSplash;
    
    public static String DF(final Number value, final int maxvalue) {
        final DecimalFormat df = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        df.setMaximumFractionDigits(maxvalue);
        return df.format(value);
    }
    
    public static void drawStringWithRect(final String string, final int x, final int y, final int colorString, final int colorRect, final int colorRect2) {
        drawBorderedRect(x - 2, y - 2, x + Wrapper.INSTANCE.fontRenderer().func_78256_a(string) + 2, y + 10, 1.0f, colorRect, colorRect2);
        Wrapper.INSTANCE.fontRenderer().func_78276_b(string, x, y, colorString);
    }
    
    public static void drawBorderedRect(final double x, final double y, final double x2, final double y2, final float l1, final int col1, final int col2) {
        drawRect((float)(int)x, (float)(int)y, (float)(int)x2, (float)(int)y2, col2);
        final float f = (col1 >> 24 & 0xFF) / 255.0f;
        final float f2 = (col1 >> 16 & 0xFF) / 255.0f;
        final float f3 = (col1 >> 8 & 0xFF) / 255.0f;
        final float f4 = (col1 & 0xFF) / 255.0f;
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glPushMatrix();
        GL11.glColor4f(f2, f3, f4, f);
        GL11.glLineWidth(l1);
        GL11.glBegin(1);
        GL11.glVertex2d(x, y);
        GL11.glVertex2d(x, y2);
        GL11.glVertex2d(x2, y2);
        GL11.glVertex2d(x2, y);
        GL11.glVertex2d(x, y);
        GL11.glVertex2d(x2, y);
        GL11.glVertex2d(x, y2);
        GL11.glVertex2d(x2, y2);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable(3553);
        GL11.glDisable(2848);
    }
    
    public static void drawTracer(final Entity entity, final float red, final float green, final float blue, final float alpha, final float ticks) {
        final double renderPosX = Wrapper.INSTANCE.mc().func_175598_ae().field_78730_l;
        final double renderPosY = Wrapper.INSTANCE.mc().func_175598_ae().field_78731_m;
        final double renderPosZ = Wrapper.INSTANCE.mc().func_175598_ae().field_78728_n;
        final double xPos = entity.field_70142_S + (entity.field_70165_t - entity.field_70142_S) * ticks - renderPosX;
        final double yPos = entity.field_70137_T + (entity.field_70163_u - entity.field_70137_T) * ticks + entity.field_70131_O / 2.0f - renderPosY;
        final double zPos = entity.field_70136_U + (entity.field_70161_v - entity.field_70136_U) * ticks - renderPosZ;
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(3042);
        GL11.glEnable(2848);
        GL11.glDisable(2896);
        GL11.glLineWidth(1.0f);
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glColor4f(red, green, blue, alpha);
        Vec3d eyes = null;
        final Vec3d vec3d = new Vec3d(0.0, 0.0, 1.0);
        final Wrapper instance = Wrapper.INSTANCE;
        final Vec3d func_178789_a = vec3d.func_178789_a(-(float)Math.toRadians(Wrapper.player().field_70125_A));
        final Wrapper instance2 = Wrapper.INSTANCE;
        eyes = func_178789_a.func_178785_b(-(float)Math.toRadians(Wrapper.player().field_70177_z));
        GL11.glBegin(1);
        final double field_72450_a = eyes.field_72450_a;
        final Wrapper instance3 = Wrapper.INSTANCE;
        GL11.glVertex3d(field_72450_a, Wrapper.player().func_70047_e() + eyes.field_72448_b, eyes.field_72449_c);
        GL11.glVertex3d(xPos, yPos, zPos);
        GL11.glEnd();
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glEnable(2896);
        GL11.glDepthMask(true);
    }
    
    public static void drawESP(final Entity entity, final float colorRed, final float colorGreen, final float colorBlue, final float colorAlpha, final float ticks) {
        try {
            final double renderPosX = Wrapper.INSTANCE.mc().func_175598_ae().field_78730_l;
            final double renderPosY = Wrapper.INSTANCE.mc().func_175598_ae().field_78731_m;
            final double renderPosZ = Wrapper.INSTANCE.mc().func_175598_ae().field_78728_n;
            final double xPos = entity.field_70142_S + (entity.field_70165_t - entity.field_70142_S) * ticks - renderPosX;
            final double yPos = entity.field_70137_T + (entity.field_70163_u - entity.field_70137_T) * ticks + entity.field_70131_O / 2.0f - renderPosY;
            final double zPos = entity.field_70136_U + (entity.field_70161_v - entity.field_70136_U) * ticks - renderPosZ;
            final float playerViewY = Wrapper.INSTANCE.mc().func_175598_ae().field_78735_i;
            final float playerViewX = Wrapper.INSTANCE.mc().func_175598_ae().field_78732_j;
            final boolean thirdPersonView = Wrapper.INSTANCE.mc().func_175598_ae().field_78733_k.field_74320_O == 2;
            GL11.glPushMatrix();
            GlStateManager.func_179137_b(xPos, yPos, zPos);
            GlStateManager.func_187432_a(0.0f, 1.0f, 0.0f);
            GlStateManager.func_179114_b(-playerViewY, 0.0f, 1.0f, 0.0f);
            GlStateManager.func_179114_b((thirdPersonView ? -1 : 1) * playerViewX, 1.0f, 0.0f, 0.0f);
            GL11.glEnable(3042);
            GL11.glDisable(3553);
            GL11.glDisable(2896);
            GL11.glDisable(2929);
            GL11.glDepthMask(false);
            GL11.glLineWidth(1.0f);
            GL11.glBlendFunc(770, 771);
            GL11.glEnable(2848);
            GL11.glColor4f(colorRed, colorGreen, colorBlue, colorAlpha);
            GL11.glBegin(1);
            GL11.glVertex3d(0.0, 1.0, 0.0);
            GL11.glVertex3d(-0.5, 0.5, 0.0);
            GL11.glVertex3d(0.0, 1.0, 0.0);
            GL11.glVertex3d(0.5, 0.5, 0.0);
            GL11.glVertex3d(0.0, 0.0, 0.0);
            GL11.glVertex3d(-0.5, 0.5, 0.0);
            GL11.glVertex3d(0.0, 0.0, 0.0);
            GL11.glVertex3d(0.5, 0.5, 0.0);
            GL11.glEnd();
            GL11.glDepthMask(true);
            GL11.glEnable(2929);
            GL11.glEnable(3553);
            GL11.glEnable(2896);
            GL11.glDisable(2848);
            GL11.glDisable(3042);
            GL11.glPopMatrix();
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    
    public static void drawNukerBlocks(final Iterable<BlockPos> blocks, final float r, final float g, final float b, final float ticks) {
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glLineWidth(1.0f);
        GL11.glDisable(3553);
        GL11.glEnable(2884);
        GL11.glDisable(2929);
        GL11.glDisable(2896);
        final WorldClient world = Wrapper.INSTANCE.world();
        final Wrapper instance = Wrapper.INSTANCE;
        final EntityPlayerSP player = Wrapper.player();
        for (final BlockPos pos : blocks) {
            final IBlockState iblockstate = world.func_180495_p(pos);
            final double x = player.field_70142_S + (player.field_70165_t - player.field_70142_S) * ticks;
            final double y = player.field_70137_T + (player.field_70163_u - player.field_70137_T) * ticks;
            final double z = player.field_70136_U + (player.field_70161_v - player.field_70136_U) * ticks;
            GLUtils.glColor(new Color(r, g, b, 1.0f));
            final AxisAlignedBB boundingBox = iblockstate.func_185918_c((World)world, pos).func_186662_g(0.0020000000949949026).func_72317_d(-x, -y, -z);
            drawSelectionBoundingBox(boundingBox);
        }
        GL11.glEnable(2896);
        GL11.glEnable(2929);
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
        GL11.glPopMatrix();
    }
    
    public static void drawBlockESP(final BlockPos pos, final float red, final float green, final float blue) {
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glLineWidth(1.0f);
        GL11.glDisable(3553);
        GL11.glEnable(2884);
        GL11.glDisable(2929);
        GL11.glDisable(2896);
        final double renderPosX = Wrapper.INSTANCE.mc().func_175598_ae().field_78730_l;
        final double renderPosY = Wrapper.INSTANCE.mc().func_175598_ae().field_78731_m;
        final double renderPosZ = Wrapper.INSTANCE.mc().func_175598_ae().field_78728_n;
        GL11.glTranslated(-renderPosX, -renderPosY, -renderPosZ);
        GL11.glTranslated((double)pos.func_177958_n(), (double)pos.func_177956_o(), (double)pos.func_177952_p());
        GL11.glColor4f(red, green, blue, 0.3f);
        drawSolidBox();
        GL11.glColor4f(red, green, blue, 0.7f);
        drawOutlinedBox();
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glEnable(2896);
        GL11.glEnable(2929);
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
        GL11.glPopMatrix();
    }
    
    public static void drawSelectionBoundingBox(final AxisAlignedBB boundingBox) {
        final Tessellator tessellator = Tessellator.func_178181_a();
        final BufferBuilder vertexbuffer = tessellator.func_178180_c();
        vertexbuffer.func_181668_a(3, DefaultVertexFormats.field_181705_e);
        vertexbuffer.func_181662_b(boundingBox.field_72340_a, boundingBox.field_72338_b, boundingBox.field_72339_c).func_181675_d();
        vertexbuffer.func_181662_b(boundingBox.field_72336_d, boundingBox.field_72338_b, boundingBox.field_72339_c).func_181675_d();
        vertexbuffer.func_181662_b(boundingBox.field_72336_d, boundingBox.field_72338_b, boundingBox.field_72334_f).func_181675_d();
        vertexbuffer.func_181662_b(boundingBox.field_72340_a, boundingBox.field_72338_b, boundingBox.field_72334_f).func_181675_d();
        vertexbuffer.func_181662_b(boundingBox.field_72340_a, boundingBox.field_72338_b, boundingBox.field_72339_c).func_181675_d();
        tessellator.func_78381_a();
        vertexbuffer.func_181668_a(3, DefaultVertexFormats.field_181705_e);
        vertexbuffer.func_181662_b(boundingBox.field_72340_a, boundingBox.field_72337_e, boundingBox.field_72339_c).func_181675_d();
        vertexbuffer.func_181662_b(boundingBox.field_72336_d, boundingBox.field_72337_e, boundingBox.field_72339_c).func_181675_d();
        vertexbuffer.func_181662_b(boundingBox.field_72336_d, boundingBox.field_72337_e, boundingBox.field_72334_f).func_181675_d();
        vertexbuffer.func_181662_b(boundingBox.field_72340_a, boundingBox.field_72337_e, boundingBox.field_72334_f).func_181675_d();
        vertexbuffer.func_181662_b(boundingBox.field_72340_a, boundingBox.field_72337_e, boundingBox.field_72339_c).func_181675_d();
        tessellator.func_78381_a();
        vertexbuffer.func_181668_a(1, DefaultVertexFormats.field_181705_e);
        vertexbuffer.func_181662_b(boundingBox.field_72340_a, boundingBox.field_72338_b, boundingBox.field_72339_c).func_181675_d();
        vertexbuffer.func_181662_b(boundingBox.field_72340_a, boundingBox.field_72337_e, boundingBox.field_72339_c).func_181675_d();
        vertexbuffer.func_181662_b(boundingBox.field_72336_d, boundingBox.field_72338_b, boundingBox.field_72339_c).func_181675_d();
        vertexbuffer.func_181662_b(boundingBox.field_72336_d, boundingBox.field_72337_e, boundingBox.field_72339_c).func_181675_d();
        vertexbuffer.func_181662_b(boundingBox.field_72336_d, boundingBox.field_72338_b, boundingBox.field_72334_f).func_181675_d();
        vertexbuffer.func_181662_b(boundingBox.field_72336_d, boundingBox.field_72337_e, boundingBox.field_72334_f).func_181675_d();
        vertexbuffer.func_181662_b(boundingBox.field_72340_a, boundingBox.field_72338_b, boundingBox.field_72334_f).func_181675_d();
        vertexbuffer.func_181662_b(boundingBox.field_72340_a, boundingBox.field_72337_e, boundingBox.field_72334_f).func_181675_d();
        tessellator.func_78381_a();
    }
    
    public static void drawColorBox(final AxisAlignedBB axisalignedbb, final float red, final float green, final float blue, final float alpha) {
        final Tessellator ts = Tessellator.func_178181_a();
        final BufferBuilder vb = ts.func_178180_c();
        vb.func_181668_a(7, DefaultVertexFormats.field_181707_g);
        vb.func_181662_b(axisalignedbb.field_72340_a, axisalignedbb.field_72338_b, axisalignedbb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72340_a, axisalignedbb.field_72337_e, axisalignedbb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72336_d, axisalignedbb.field_72338_b, axisalignedbb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72336_d, axisalignedbb.field_72337_e, axisalignedbb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72336_d, axisalignedbb.field_72338_b, axisalignedbb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72336_d, axisalignedbb.field_72337_e, axisalignedbb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72340_a, axisalignedbb.field_72338_b, axisalignedbb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72340_a, axisalignedbb.field_72337_e, axisalignedbb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        ts.func_78381_a();
        vb.func_181668_a(7, DefaultVertexFormats.field_181707_g);
        vb.func_181662_b(axisalignedbb.field_72336_d, axisalignedbb.field_72337_e, axisalignedbb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72336_d, axisalignedbb.field_72338_b, axisalignedbb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72340_a, axisalignedbb.field_72337_e, axisalignedbb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72340_a, axisalignedbb.field_72338_b, axisalignedbb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72340_a, axisalignedbb.field_72337_e, axisalignedbb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72340_a, axisalignedbb.field_72338_b, axisalignedbb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72336_d, axisalignedbb.field_72337_e, axisalignedbb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72336_d, axisalignedbb.field_72338_b, axisalignedbb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        ts.func_78381_a();
        vb.func_181668_a(7, DefaultVertexFormats.field_181707_g);
        vb.func_181662_b(axisalignedbb.field_72340_a, axisalignedbb.field_72337_e, axisalignedbb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72336_d, axisalignedbb.field_72337_e, axisalignedbb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72336_d, axisalignedbb.field_72337_e, axisalignedbb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72340_a, axisalignedbb.field_72337_e, axisalignedbb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72340_a, axisalignedbb.field_72337_e, axisalignedbb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72340_a, axisalignedbb.field_72337_e, axisalignedbb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72336_d, axisalignedbb.field_72337_e, axisalignedbb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72336_d, axisalignedbb.field_72337_e, axisalignedbb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        ts.func_78381_a();
        vb.func_181668_a(7, DefaultVertexFormats.field_181707_g);
        vb.func_181662_b(axisalignedbb.field_72340_a, axisalignedbb.field_72338_b, axisalignedbb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72336_d, axisalignedbb.field_72338_b, axisalignedbb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72336_d, axisalignedbb.field_72338_b, axisalignedbb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72340_a, axisalignedbb.field_72338_b, axisalignedbb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72340_a, axisalignedbb.field_72338_b, axisalignedbb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72340_a, axisalignedbb.field_72338_b, axisalignedbb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72336_d, axisalignedbb.field_72338_b, axisalignedbb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72336_d, axisalignedbb.field_72338_b, axisalignedbb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        ts.func_78381_a();
        vb.func_181668_a(7, DefaultVertexFormats.field_181707_g);
        vb.func_181662_b(axisalignedbb.field_72340_a, axisalignedbb.field_72338_b, axisalignedbb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72340_a, axisalignedbb.field_72337_e, axisalignedbb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72340_a, axisalignedbb.field_72338_b, axisalignedbb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72340_a, axisalignedbb.field_72337_e, axisalignedbb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72336_d, axisalignedbb.field_72338_b, axisalignedbb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72336_d, axisalignedbb.field_72337_e, axisalignedbb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72336_d, axisalignedbb.field_72338_b, axisalignedbb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72336_d, axisalignedbb.field_72337_e, axisalignedbb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        ts.func_78381_a();
        vb.func_181668_a(7, DefaultVertexFormats.field_181707_g);
        vb.func_181662_b(axisalignedbb.field_72340_a, axisalignedbb.field_72337_e, axisalignedbb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72340_a, axisalignedbb.field_72338_b, axisalignedbb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72340_a, axisalignedbb.field_72337_e, axisalignedbb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72340_a, axisalignedbb.field_72338_b, axisalignedbb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72336_d, axisalignedbb.field_72337_e, axisalignedbb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72336_d, axisalignedbb.field_72338_b, axisalignedbb.field_72339_c).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72336_d, axisalignedbb.field_72337_e, axisalignedbb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        vb.func_181662_b(axisalignedbb.field_72336_d, axisalignedbb.field_72338_b, axisalignedbb.field_72334_f).func_181666_a(red, green, blue, alpha).func_181675_d();
        ts.func_78381_a();
    }
    
    public static void drawSolidBox() {
        drawSolidBox(RenderUtils.DEFAULT_AABB);
    }
    
    public static void drawSolidBox(final AxisAlignedBB bb) {
        GL11.glBegin(7);
        GL11.glVertex3d(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c);
        GL11.glVertex3d(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c);
        GL11.glVertex3d(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f);
        GL11.glVertex3d(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f);
        GL11.glVertex3d(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c);
        GL11.glVertex3d(bb.field_72340_a, bb.field_72337_e, bb.field_72334_f);
        GL11.glVertex3d(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f);
        GL11.glVertex3d(bb.field_72336_d, bb.field_72337_e, bb.field_72339_c);
        GL11.glVertex3d(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c);
        GL11.glVertex3d(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c);
        GL11.glVertex3d(bb.field_72336_d, bb.field_72337_e, bb.field_72339_c);
        GL11.glVertex3d(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c);
        GL11.glVertex3d(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c);
        GL11.glVertex3d(bb.field_72336_d, bb.field_72337_e, bb.field_72339_c);
        GL11.glVertex3d(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f);
        GL11.glVertex3d(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f);
        GL11.glVertex3d(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f);
        GL11.glVertex3d(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f);
        GL11.glVertex3d(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f);
        GL11.glVertex3d(bb.field_72340_a, bb.field_72337_e, bb.field_72334_f);
        GL11.glVertex3d(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c);
        GL11.glVertex3d(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f);
        GL11.glVertex3d(bb.field_72340_a, bb.field_72337_e, bb.field_72334_f);
        GL11.glVertex3d(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c);
        GL11.glEnd();
    }
    
    public static void drawOutlinedBox() {
        drawOutlinedBox(RenderUtils.DEFAULT_AABB);
    }
    
    public static void drawOutlinedBox(final AxisAlignedBB bb) {
        GL11.glBegin(1);
        GL11.glVertex3d(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c);
        GL11.glVertex3d(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c);
        GL11.glVertex3d(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c);
        GL11.glVertex3d(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f);
        GL11.glVertex3d(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f);
        GL11.glVertex3d(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f);
        GL11.glVertex3d(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f);
        GL11.glVertex3d(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c);
        GL11.glVertex3d(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c);
        GL11.glVertex3d(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c);
        GL11.glVertex3d(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c);
        GL11.glVertex3d(bb.field_72336_d, bb.field_72337_e, bb.field_72339_c);
        GL11.glVertex3d(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f);
        GL11.glVertex3d(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f);
        GL11.glVertex3d(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f);
        GL11.glVertex3d(bb.field_72340_a, bb.field_72337_e, bb.field_72334_f);
        GL11.glVertex3d(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c);
        GL11.glVertex3d(bb.field_72336_d, bb.field_72337_e, bb.field_72339_c);
        GL11.glVertex3d(bb.field_72336_d, bb.field_72337_e, bb.field_72339_c);
        GL11.glVertex3d(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f);
        GL11.glVertex3d(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f);
        GL11.glVertex3d(bb.field_72340_a, bb.field_72337_e, bb.field_72334_f);
        GL11.glVertex3d(bb.field_72340_a, bb.field_72337_e, bb.field_72334_f);
        GL11.glVertex3d(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c);
        GL11.glEnd();
    }
    
    public static void drawBoundingBox(final AxisAlignedBB aa) {
        final Tessellator tessellator = Tessellator.func_178181_a();
        final BufferBuilder vertexBuffer = tessellator.func_178180_c();
        vertexBuffer.func_181668_a(3, DefaultVertexFormats.field_181705_e);
        vertexBuffer.func_181662_b(aa.field_72340_a, aa.field_72338_b, aa.field_72339_c);
        vertexBuffer.func_181662_b(aa.field_72340_a, aa.field_72337_e, aa.field_72339_c);
        vertexBuffer.func_181662_b(aa.field_72336_d, aa.field_72338_b, aa.field_72339_c);
        vertexBuffer.func_181662_b(aa.field_72336_d, aa.field_72337_e, aa.field_72339_c);
        vertexBuffer.func_181662_b(aa.field_72336_d, aa.field_72338_b, aa.field_72334_f);
        vertexBuffer.func_181662_b(aa.field_72336_d, aa.field_72337_e, aa.field_72334_f);
        vertexBuffer.func_181662_b(aa.field_72340_a, aa.field_72338_b, aa.field_72334_f);
        vertexBuffer.func_181662_b(aa.field_72340_a, aa.field_72337_e, aa.field_72334_f);
        tessellator.func_78381_a();
        vertexBuffer.func_181668_a(3, DefaultVertexFormats.field_181705_e);
        vertexBuffer.func_181662_b(aa.field_72336_d, aa.field_72337_e, aa.field_72339_c);
        vertexBuffer.func_181662_b(aa.field_72336_d, aa.field_72338_b, aa.field_72339_c);
        vertexBuffer.func_181662_b(aa.field_72340_a, aa.field_72337_e, aa.field_72339_c);
        vertexBuffer.func_181662_b(aa.field_72340_a, aa.field_72338_b, aa.field_72339_c);
        vertexBuffer.func_181662_b(aa.field_72340_a, aa.field_72337_e, aa.field_72334_f);
        vertexBuffer.func_181662_b(aa.field_72340_a, aa.field_72338_b, aa.field_72334_f);
        vertexBuffer.func_181662_b(aa.field_72336_d, aa.field_72337_e, aa.field_72334_f);
        vertexBuffer.func_181662_b(aa.field_72336_d, aa.field_72338_b, aa.field_72334_f);
        tessellator.func_78381_a();
        vertexBuffer.func_181668_a(3, DefaultVertexFormats.field_181705_e);
        vertexBuffer.func_181662_b(aa.field_72340_a, aa.field_72337_e, aa.field_72339_c);
        vertexBuffer.func_181662_b(aa.field_72336_d, aa.field_72337_e, aa.field_72339_c);
        vertexBuffer.func_181662_b(aa.field_72336_d, aa.field_72337_e, aa.field_72334_f);
        vertexBuffer.func_181662_b(aa.field_72340_a, aa.field_72337_e, aa.field_72334_f);
        vertexBuffer.func_181662_b(aa.field_72340_a, aa.field_72337_e, aa.field_72339_c);
        vertexBuffer.func_181662_b(aa.field_72340_a, aa.field_72337_e, aa.field_72334_f);
        vertexBuffer.func_181662_b(aa.field_72336_d, aa.field_72337_e, aa.field_72334_f);
        vertexBuffer.func_181662_b(aa.field_72336_d, aa.field_72337_e, aa.field_72339_c);
        tessellator.func_78381_a();
        vertexBuffer.func_181668_a(3, DefaultVertexFormats.field_181705_e);
        vertexBuffer.func_181662_b(aa.field_72340_a, aa.field_72338_b, aa.field_72339_c);
        vertexBuffer.func_181662_b(aa.field_72336_d, aa.field_72338_b, aa.field_72339_c);
        vertexBuffer.func_181662_b(aa.field_72336_d, aa.field_72338_b, aa.field_72334_f);
        vertexBuffer.func_181662_b(aa.field_72340_a, aa.field_72338_b, aa.field_72334_f);
        vertexBuffer.func_181662_b(aa.field_72340_a, aa.field_72338_b, aa.field_72339_c);
        vertexBuffer.func_181662_b(aa.field_72340_a, aa.field_72338_b, aa.field_72334_f);
        vertexBuffer.func_181662_b(aa.field_72336_d, aa.field_72338_b, aa.field_72334_f);
        vertexBuffer.func_181662_b(aa.field_72336_d, aa.field_72338_b, aa.field_72339_c);
        tessellator.func_78381_a();
        vertexBuffer.func_181668_a(3, DefaultVertexFormats.field_181705_e);
        vertexBuffer.func_181662_b(aa.field_72340_a, aa.field_72338_b, aa.field_72339_c);
        vertexBuffer.func_181662_b(aa.field_72340_a, aa.field_72337_e, aa.field_72339_c);
        vertexBuffer.func_181662_b(aa.field_72340_a, aa.field_72338_b, aa.field_72334_f);
        vertexBuffer.func_181662_b(aa.field_72340_a, aa.field_72337_e, aa.field_72334_f);
        vertexBuffer.func_181662_b(aa.field_72336_d, aa.field_72338_b, aa.field_72334_f);
        vertexBuffer.func_181662_b(aa.field_72336_d, aa.field_72337_e, aa.field_72334_f);
        vertexBuffer.func_181662_b(aa.field_72336_d, aa.field_72338_b, aa.field_72339_c);
        vertexBuffer.func_181662_b(aa.field_72336_d, aa.field_72337_e, aa.field_72339_c);
        tessellator.func_78381_a();
        vertexBuffer.func_181668_a(3, DefaultVertexFormats.field_181705_e);
        vertexBuffer.func_181662_b(aa.field_72340_a, aa.field_72337_e, aa.field_72334_f);
        vertexBuffer.func_181662_b(aa.field_72340_a, aa.field_72338_b, aa.field_72334_f);
        vertexBuffer.func_181662_b(aa.field_72340_a, aa.field_72337_e, aa.field_72339_c);
        vertexBuffer.func_181662_b(aa.field_72340_a, aa.field_72338_b, aa.field_72339_c);
        vertexBuffer.func_181662_b(aa.field_72336_d, aa.field_72337_e, aa.field_72339_c);
        vertexBuffer.func_181662_b(aa.field_72336_d, aa.field_72338_b, aa.field_72339_c);
        vertexBuffer.func_181662_b(aa.field_72336_d, aa.field_72337_e, aa.field_72334_f);
        vertexBuffer.func_181662_b(aa.field_72336_d, aa.field_72338_b, aa.field_72334_f);
        tessellator.func_78381_a();
    }
    
    public static void drawOutlineBoundingBox(final AxisAlignedBB boundingBox) {
        final Tessellator tessellator = Tessellator.func_178181_a();
        final BufferBuilder vertexbuffer = tessellator.func_178180_c();
        vertexbuffer.func_181668_a(3, DefaultVertexFormats.field_181705_e);
        vertexbuffer.func_181662_b(boundingBox.field_72340_a, boundingBox.field_72338_b, boundingBox.field_72339_c).func_181675_d();
        vertexbuffer.func_181662_b(boundingBox.field_72336_d, boundingBox.field_72338_b, boundingBox.field_72339_c).func_181675_d();
        vertexbuffer.func_181662_b(boundingBox.field_72336_d, boundingBox.field_72338_b, boundingBox.field_72334_f).func_181675_d();
        vertexbuffer.func_181662_b(boundingBox.field_72340_a, boundingBox.field_72338_b, boundingBox.field_72334_f).func_181675_d();
        vertexbuffer.func_181662_b(boundingBox.field_72340_a, boundingBox.field_72338_b, boundingBox.field_72339_c).func_181675_d();
        tessellator.func_78381_a();
        vertexbuffer.func_181668_a(3, DefaultVertexFormats.field_181705_e);
        vertexbuffer.func_181662_b(boundingBox.field_72340_a, boundingBox.field_72337_e, boundingBox.field_72339_c).func_181675_d();
        vertexbuffer.func_181662_b(boundingBox.field_72336_d, boundingBox.field_72337_e, boundingBox.field_72339_c).func_181675_d();
        vertexbuffer.func_181662_b(boundingBox.field_72336_d, boundingBox.field_72337_e, boundingBox.field_72334_f).func_181675_d();
        vertexbuffer.func_181662_b(boundingBox.field_72340_a, boundingBox.field_72337_e, boundingBox.field_72334_f).func_181675_d();
        vertexbuffer.func_181662_b(boundingBox.field_72340_a, boundingBox.field_72337_e, boundingBox.field_72339_c).func_181675_d();
        tessellator.func_78381_a();
        vertexbuffer.func_181668_a(1, DefaultVertexFormats.field_181705_e);
        vertexbuffer.func_181662_b(boundingBox.field_72340_a, boundingBox.field_72338_b, boundingBox.field_72339_c).func_181675_d();
        vertexbuffer.func_181662_b(boundingBox.field_72340_a, boundingBox.field_72337_e, boundingBox.field_72339_c).func_181675_d();
        vertexbuffer.func_181662_b(boundingBox.field_72336_d, boundingBox.field_72338_b, boundingBox.field_72339_c).func_181675_d();
        vertexbuffer.func_181662_b(boundingBox.field_72336_d, boundingBox.field_72337_e, boundingBox.field_72339_c).func_181675_d();
        vertexbuffer.func_181662_b(boundingBox.field_72336_d, boundingBox.field_72338_b, boundingBox.field_72334_f).func_181675_d();
        vertexbuffer.func_181662_b(boundingBox.field_72336_d, boundingBox.field_72337_e, boundingBox.field_72334_f).func_181675_d();
        vertexbuffer.func_181662_b(boundingBox.field_72340_a, boundingBox.field_72338_b, boundingBox.field_72334_f).func_181675_d();
        vertexbuffer.func_181662_b(boundingBox.field_72340_a, boundingBox.field_72337_e, boundingBox.field_72334_f).func_181675_d();
        tessellator.func_78381_a();
    }
    
    public static void drawTri(final double x1, final double y1, final double x2, final double y2, final double x3, final double y3, final double width, final Color c) {
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glBlendFunc(770, 771);
        GLUtils.glColor(c);
        GL11.glLineWidth((float)width);
        GL11.glBegin(3);
        GL11.glVertex2d(x1, y1);
        GL11.glVertex2d(x2, y2);
        GL11.glVertex2d(x3, y3);
        GL11.glEnd();
        GL11.glDisable(2848);
        GL11.glEnable(3553);
    }
    
    public static void drawHLine(float par1, float par2, final float par3, final int color) {
        if (par2 < par1) {
            final float var5 = par1;
            par1 = par2;
            par2 = var5;
        }
        drawRect(par1, par3, par2 + 1.0f, par3 + 1.0f, color);
    }
    
    public static void drawVLine(final float par1, float par2, float par3, final int color) {
        if (par3 < par2) {
            final float var5 = par2;
            par2 = par3;
            par3 = var5;
        }
        drawRect(par1, par2 + 1.0f, par1 + 1.0f, par3, color);
    }
    
    public static void drawRect(float left, float top, float right, float bottom, final int color) {
        if (left < right) {
            final float var5 = left;
            left = right;
            right = var5;
        }
        if (top < bottom) {
            final float var5 = top;
            top = bottom;
            bottom = var5;
        }
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glPushMatrix();
        GLUtils.glColor(color);
        GL11.glBegin(7);
        GL11.glVertex2d((double)left, (double)bottom);
        GL11.glVertex2d((double)right, (double)bottom);
        GL11.glVertex2d((double)right, (double)top);
        GL11.glVertex2d((double)left, (double)top);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable(3553);
        GL11.glDisable(2848);
    }
    
    public static Color rainbow(final int speed, final int r, final float wh) {
        final float hex = (float)((System.currentTimeMillis() + r) % speed);
        return Color.getHSBColor(hex / speed, wh, 1.0f);
    }
    
    public static void drawXRayBlocks(final LinkedList<XRayBlock> blocks, final float ticks) {
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glLineWidth(1.0f);
        GL11.glDisable(3553);
        GL11.glEnable(2884);
        GL11.glDisable(2929);
        GL11.glDisable(2896);
        final WorldClient world = Wrapper.INSTANCE.world();
        final Wrapper instance = Wrapper.INSTANCE;
        final EntityPlayerSP player = Wrapper.player();
        for (final XRayBlock block : blocks) {
            final BlockPos pos = block.getBlockPos();
            final XRayData data = block.getxRayData();
            final IBlockState iblockstate = world.func_180495_p(pos);
            final double x = player.field_70142_S + (player.field_70165_t - player.field_70142_S) * ticks;
            final double y = player.field_70137_T + (player.field_70163_u - player.field_70137_T) * ticks;
            final double z = player.field_70136_U + (player.field_70161_v - player.field_70136_U) * ticks;
            final int color = new Color(data.getRed(), data.getGreen(), data.getBlue(), 255).getRGB();
            GLUtils.glColor(color);
            final AxisAlignedBB boundingBox = iblockstate.func_185918_c((World)world, pos).func_186662_g(0.0020000000949949026).func_72317_d(-x, -y, -z);
            drawSelectionBoundingBox(boundingBox);
        }
        GL11.glEnable(2896);
        GL11.glEnable(2929);
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
        GL11.glPopMatrix();
    }
    
    static {
        DEFAULT_AABB = new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 1.0, 1.0);
        RenderUtils.splashTimer = new TimerUtils();
        RenderUtils.splashTickPos = 0;
        RenderUtils.isSplash = false;
    }
}
