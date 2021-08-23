package me.Divine.clickgui.component.components.sub;

import me.Divine.clickgui.component.*;
import net.minecraft.client.*;
import me.Divine.settings.*;
import me.Divine.clickgui.component.components.*;
import java.awt.*;
import net.minecraft.client.gui.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.*;

public class Checkbox extends Component
{
    protected static Minecraft mc;
    private boolean hovered;
    private Setting op;
    private Button parent;
    private int offset;
    private int x;
    private int y;
    private int o;
    private int p;
    
    public Checkbox(final Setting option, final Button button, final int offset) {
        this.op = option;
        this.parent = button;
        this.x = button.parent.getX() + button.parent.getWidth();
        this.y = button.parent.getY() + button.offset;
        this.offset = offset;
        this.o = 1;
        this.p = 1;
    }
    
    @Override
    public void renderComponent() {
        Gui.func_73734_a(this.parent.parent.getX(), this.parent.parent.getY() + this.offset - 2, this.parent.parent.getX() + this.parent.parent.getWidth(), this.parent.parent.getY() + this.offset + 20, new Color(0, 0, 0, 250).getRGB());
        Gui.func_73734_a(this.parent.parent.getX(), this.parent.parent.getY() + this.offset, this.parent.parent.getX() + 2, this.parent.parent.getY() + this.offset + 12, -15658735);
        GL11.glPushMatrix();
        Minecraft.func_71410_x().field_71466_p.func_175063_a(this.op.getName(), (float)(this.parent.parent.getX() + 10 + 17 + 15), (float)(this.parent.parent.getY() + this.offset + 1 + 1), -1);
        GL11.glPopMatrix();
        Gui.func_73734_a(this.parent.parent.getX() + 3 + 4, this.parent.parent.getY() + this.offset + 3, this.parent.parent.getX() + 13 + 7, this.parent.parent.getY() + this.offset + 9, new Color(0, 0, 0, 240).getRGB());
        if (this.op.getValBoolean()) {
            drawRoundedRect((float)(this.parent.parent.getX() + 4 + 4), (float)(this.parent.parent.getY() + this.offset + 2), (float)(this.parent.parent.getX() + 15 + 6), (float)(this.parent.parent.getY() + this.offset + 14), (float)this.o, new Color(51, 51, 51, 240).getRGB());
            Minecraft.func_71410_x().field_71466_p.func_175063_a("\u2714", (float)(this.parent.parent.getX() + 1 + 1 + 10), (float)(this.parent.parent.getY() + this.offset + 1 + 1), -1);
        }
        else {
            drawRoundedRect((float)(this.parent.parent.getX() + 4 + 4), (float)(this.parent.parent.getY() + this.offset + 2), (float)(this.parent.parent.getX() + 15 + 6), (float)(this.parent.parent.getY() + this.offset + 14), (float)this.o, new Color(51, 51, 51, 240).getRGB());
        }
    }
    
    public static int rainbow(final int delay, final long index) {
        double rainbowState = Math.ceil((double)(System.currentTimeMillis() + index + delay)) / 15.0;
        rainbowState %= 360.0;
        return Color.getHSBColor((float)(rainbowState / 360.0), 0.4f, 1.0f).getRGB();
    }
    
    @Override
    public void setOff(final int newOff) {
        this.offset = newOff;
    }
    
    @Override
    public void updateComponent(final int mouseX, final int mouseY) {
        this.hovered = this.isMouseOnButton(mouseX, mouseY);
        this.y = this.parent.parent.getY() + this.offset;
        this.x = this.parent.parent.getX();
    }
    
    @Override
    public void mouseClicked(final int mouseX, final int mouseY, final int button) {
        if (this.isMouseOnButton(mouseX, mouseY) && button == 0 && this.parent.open) {
            this.op.setValBoolean(!this.op.getValBoolean());
        }
    }
    
    public boolean isMouseOnButton(final int x, final int y) {
        return x > this.x && x < this.x + 88 && y > this.y && y < this.y + 12;
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
    
    static {
        Checkbox.mc = Minecraft.func_71410_x();
    }
}
