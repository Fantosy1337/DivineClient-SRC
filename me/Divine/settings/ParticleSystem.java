package me.Divine.settings;

import org.lwjgl.input.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.*;
import java.util.*;

public class ParticleSystem
{
    private static final float SPEED = 0.1f;
    private int dist;
    private List particleList;
    private Random random;
    
    public ParticleSystem(int n) {
        n = 350;
        this.dist = 100;
        this.random = new Random();
        this.particleList = new ArrayList();
        this.addParticles(n);
    }
    
    public void addParticles(final int n) {
        for (int i = 0; i < n; ++i) {
            this.particleList.add(Particle.generateParticle());
        }
    }
    
    public double distance(final float f, final float f2, final float f3, final float f4) {
        return Math.sqrt((f - f3) * (f - f3) + (f2 - f4) * (f2 - f4));
    }
    
    public void tick(final int n) {
        if (Mouse.isButtonDown(0)) {
            this.addParticles(1);
        }
        this.particleList.forEach(particle -> particle.tick(n, 0.1f));
    }
    
    private void drawLine(final float f, final float f2, final float f3, final float f4, final float f5, final float f6, final float f7, final float f8) {
        GL11.glColor4f(f5, f6, f7, f8);
        GL11.glLineWidth(0.5f);
        GL11.glBegin(1);
        GL11.glVertex2f(f, f2);
        GL11.glVertex2f(f3, f4);
        GL11.glEnd();
    }
    
    public void render() {
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(2884);
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        for (final Object particlew : this.particleList) {
            final Particle particle = (Particle)particlew;
            GL11.glColor4f(1.0f, 1.0f, 1.0f, particle.getAlpha() / 255.0f);
            GL11.glPointSize(particle.getSize());
            GL11.glBegin(0);
            GL11.glVertex2f(particle.getX(), particle.getY());
            GL11.glEnd();
            final int n = Mouse.getEventX() * Minecraft.func_71410_x().field_71462_r.field_146294_l / Minecraft.func_71410_x().field_71443_c;
            final int n2 = Minecraft.func_71410_x().field_71462_r.field_146295_m - Mouse.getEventY() * Minecraft.func_71410_x().field_71462_r.field_146295_m / Minecraft.func_71410_x().field_71440_d - 1;
            float f = 0.0f;
            Particle particle2 = null;
            for (final Object particle3w : this.particleList) {
                final Particle particle3 = (Particle)particle3w;
                final float f2 = particle.getDistanceTo(particle3);
                if (f2 <= this.dist) {
                    if (this.distance((float)n, (float)n2, particle.getX(), particle.getY()) > this.dist && this.distance((float)n, (float)n2, particle3.getX(), particle3.getY()) > this.dist) {
                        continue;
                    }
                    Float.compare(f, 0.0f);
                    f = f2;
                    particle2 = particle3;
                }
            }
            if (particle2 == null) {
                continue;
            }
            Math.min(1.0f, Math.min(1.0f, 1.0f - f / this.dist));
        }
        GL11.glPushMatrix();
        GL11.glTranslatef(0.5f, 0.5f, 0.5f);
        GL11.glNormal3f(0.0f, 1.0f, 0.0f);
        GL11.glEnable(3553);
        GL11.glPopMatrix();
        GL11.glDepthMask(true);
        GL11.glEnable(2884);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }
}
