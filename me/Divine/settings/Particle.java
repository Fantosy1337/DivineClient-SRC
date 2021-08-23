package me.Divine.settings;

import java.util.*;
import javax.vecmath.*;
import org.lwjgl.opengl.*;

public class Particle
{
    private static final Random random;
    private Vector2f pos;
    private float alpha;
    private float size;
    private Vector2f velocity;
    
    public Particle(final Vector2f vector2f, final float f, final float f2, final float f3) {
        this.velocity = vector2f;
        this.pos = new Vector2f(f, f2);
        this.size = f3;
    }
    
    public static Particle generateParticle() {
        final Vector2f vector2f = new Vector2f((float)(Math.random() * 2.0 - 1.0), (float)(Math.random() * 2.0 - 1.0));
        final float f = (float)Particle.random.nextInt(Display.getWidth());
        final float f2 = (float)Particle.random.nextInt(Display.getHeight());
        final float f3 = (float)(Math.random() * 4.0) + 1.0f;
        return new Particle(vector2f, f, f2, f3);
    }
    
    public double distance(final float f, final float f2, final float f3, final float f4) {
        return Math.sqrt((f - f3) * (f - f3) + (f2 - f4) * (f2 - f4));
    }
    
    public float getAlpha() {
        return this.alpha;
    }
    
    public float getDistanceTo(final Particle particle) {
        return this.getDistanceTo(particle.getX(), particle.getY());
    }
    
    public float getX() {
        return this.pos.getX();
    }
    
    public void setX(final float f) {
        this.pos.setX(f);
    }
    
    public float getDistanceTo(final float f, final float f2) {
        return (float)this.distance(this.getX(), this.getY(), f, f2);
    }
    
    public void tick(final int n, float f) {
        f = 0.1f;
        final Vector2f pos = this.pos;
        pos.x += this.velocity.getX() * n * f;
        final Vector2f pos2 = this.pos;
        pos2.y += this.velocity.getY() * n * f;
        if (this.alpha < 255.0f) {
            this.alpha += 0.05f * n;
        }
        if (this.pos.getX() > Display.getWidth()) {
            this.pos.setX(0.0f);
        }
        if (this.pos.getX() < 0.0f) {
            this.pos.setX((float)Display.getWidth());
        }
        if (this.pos.getY() > Display.getHeight()) {
            this.pos.setY(0.0f);
        }
        if (this.pos.getY() < 0.0f) {
            this.pos.setY((float)Display.getHeight());
        }
    }
    
    public Vector2f getVelocity() {
        return this.velocity;
    }
    
    public void setVelocity(final Vector2f vector2f) {
        this.velocity = vector2f;
    }
    
    public float getY() {
        return this.pos.getY();
    }
    
    public void setY(final float f) {
        this.pos.setY(f);
    }
    
    public float getSize() {
        return this.size;
    }
    
    public void setSize(final float f) {
        this.size = f;
    }
    
    static {
        random = new Random();
    }
}
