package me.Divine.clickgui.component;

import me.Divine.*;
import me.Divine.module.*;
import me.Divine.clickgui.component.components.*;
import java.util.*;
import net.minecraft.client.gui.*;
import java.awt.*;
import me.Divine.font.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.*;

public class Frame
{
    public ArrayList<Component> components;
    public Category category;
    private boolean open;
    private int width;
    private int y;
    private int x;
    private int barHeight;
    private boolean isDragging;
    public int dragX;
    public int dragY;
    public static Frame instance;
    private int o;
    
    public Frame(final Category cat) {
        this.components = new ArrayList<Component>();
        this.category = cat;
        this.width = 110;
        this.x = 16;
        this.y = 16;
        this.barHeight = 17;
        this.dragX = 0;
        this.open = false;
        this.isDragging = false;
        this.o = 1;
        int tY = this.barHeight + 2;
        for (final Module mod : Divine.instance.moduleManager.getModulesInCategory(this.category)) {
            final Button modButton = new Button(mod, this, tY);
            this.components.add(modButton);
            tY += 16;
        }
    }
    
    public ArrayList<Component> getComponents() {
        return this.components;
    }
    
    public void setX(final int newX) {
        this.x = newX;
    }
    
    public void setY(final int newY) {
        this.y = newY;
    }
    
    public void setDrag(final boolean drag) {
        this.isDragging = drag;
    }
    
    public boolean isOpen() {
        return this.open;
    }
    
    public void setOpen(final boolean open) {
        this.open = open;
    }
    
    public void renderFrame(final FontRenderer fontRenderer) {
        this.barHeight = 16;
        drawRoundedRect((float)this.x, (float)this.y, (float)(this.x + this.width), (float)(this.y + this.barHeight), (float)this.o, new Color(255, 102, 204, 170).getRGB());
        Fonts.font.drawStringWithShadow(this.category.name(), this.x + 2 + 5, (int)(this.y + 2.5f + 1.0f), -1);
        if (this.open && !this.components.isEmpty()) {
            for (final Component component : this.components) {
                component.renderComponent();
            }
        }
    }
    
    public void refresh() {
        int off = this.barHeight;
        for (final Component comp : this.components) {
            comp.setOff(off);
            off += comp.getHeight();
        }
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public void updatePosition(final int mouseX, final int mouseY) {
        if (this.isDragging) {
            this.setX(mouseX - this.dragX);
            this.setY(mouseY - this.dragY);
        }
    }
    
    public boolean isWithinHeader(final int x, final int y) {
        return x >= this.x && x <= this.x + this.width && y >= this.y && y <= this.y + this.barHeight;
    }
    
    public static void drawRoundedRect(final float x0, final float y0, final float x1, final float y1, final float radius, final int color) {
        GL11.glPushMatrix();
        final int numberOfArcs = 18;
        final float angleIncrement = 5.0f;
        final float a = (color >> 24 & 0xFF) / 255.0f;
        final float r = (color >> 16 & 0xFF) / 255.0f;
        final float g = (color >> 8 & 0xFF) / 255.0f;
        final float b = (color & 0xFF) / 255.0f;
        GL11.glDisable(2884);
        GL11.glDisable(3553);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glColor4f(r, g, b, a);
        GL11.glBegin(5);
        GL11.glVertex2f(x0 + radius, y0);
        GL11.glVertex2f(x0 + radius, y1);
        GL11.glVertex2f(x1 - radius, y0);
        GL11.glVertex2f(x1 - radius, y1);
        GL11.glEnd();
        GL11.glBegin(5);
        GL11.glVertex2f(x0, y0 + radius);
        GL11.glVertex2f(x0 + radius, y0 + radius);
        GL11.glVertex2f(x0, y1 - radius);
        GL11.glVertex2f(x0 + radius, y1 - radius);
        GL11.glEnd();
        GL11.glBegin(5);
        GL11.glVertex2f(x1, y0 + radius);
        GL11.glVertex2f(x1 - radius, y0 + radius);
        GL11.glVertex2f(x1, y1 - radius);
        GL11.glVertex2f(x1 - radius, y1 - radius);
        GL11.glEnd();
        GL11.glBegin(6);
        float centerX = x1 - radius;
        float centerY = y0 + radius;
        GL11.glVertex2f(centerX, centerY);
        for (int i = 0; i <= 18; ++i) {
            final float angle = i * 5.0f;
            GL11.glVertex2f((float)(centerX + radius * Math.cos(Math.toRadians(angle))), (float)(centerY - radius * Math.sin(Math.toRadians(angle))));
        }
        GL11.glEnd();
        GL11.glBegin(6);
        centerX = x0 + radius;
        centerY = y0 + radius;
        GL11.glVertex2f(centerX, centerY);
        for (int i = 0; i <= 18; ++i) {
            final float angle = i * 5.0f;
            GL11.glVertex2f((float)(centerX - radius * Math.cos(Math.toRadians(angle))), (float)(centerY - radius * Math.sin(Math.toRadians(angle))));
        }
        GL11.glEnd();
        GL11.glBegin(6);
        centerX = x0 + radius;
        centerY = y1 - radius;
        GL11.glVertex2f(centerX, centerY);
        for (int i = 0; i <= 18; ++i) {
            final float angle = i * 5.0f;
            GL11.glVertex2f((float)(centerX - radius * Math.cos(Math.toRadians(angle))), (float)(centerY + radius * Math.sin(Math.toRadians(angle))));
        }
        GL11.glEnd();
        GL11.glBegin(6);
        centerX = x1 - radius;
        centerY = y1 - radius;
        GL11.glVertex2f(centerX, centerY);
        for (int i = 0; i <= 18; ++i) {
            final float angle = i * 5.0f;
            GL11.glVertex2f((float)(centerX + radius * Math.cos(Math.toRadians(angle))), (float)(centerY + radius * Math.sin(Math.toRadians(angle))));
        }
        GL11.glEnd();
        GL11.glEnable(3553);
        GL11.glEnable(2884);
        GL11.glDisable(3042);
        GlStateManager.func_179084_k();
        GL11.glPopMatrix();
        GlStateManager.func_179117_G();
    }
}
