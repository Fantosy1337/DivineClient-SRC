package me.Divine.clickgui.component.components.sub;

import me.Divine.settings.*;
import me.Divine.clickgui.component.components.*;
import java.awt.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import me.Divine.clickgui.component.*;
import java.math.*;
import net.minecraft.client.renderer.vertex.*;
import net.minecraft.client.renderer.*;

public class Slider extends Component
{
    private boolean hovered;
    private Setting set;
    private Button parent;
    private int offset;
    private int x;
    private int y;
    private int o;
    private boolean dragging;
    private double renderWidth;
    
    public Slider(final Setting value, final Button button, final int offset) {
        this.dragging = false;
        this.set = value;
        this.parent = button;
        this.x = button.parent.getX() + button.parent.getWidth();
        this.y = button.parent.getY() + button.offset;
        this.offset = offset;
        this.o = 3;
    }
    
    @Override
    public void renderComponent() {
        final int BG = new Color(0, 0, 0, 100).getRGB();
        final Minecraft mc = Minecraft.func_71410_x();
        Gui.func_73734_a(this.parent.parent.getX(), this.parent.parent.getY() + this.offset - 2, this.parent.parent.getX() + this.parent.parent.getWidth(), this.parent.parent.getY() + this.offset + 18, new Color(0, 0, 0).getRGB());
        Frame.drawRoundedRect((float)(this.parent.parent.getX() + 1), (float)(this.parent.parent.getY() + this.offset), (float)(this.parent.parent.getX() + this.parent.parent.getWidth() + 1), (float)(this.parent.parent.getY() + this.offset + 8 - 1), (float)this.o, new Color(255, 102, 204).getRGB());
        Frame.drawRoundedRect((float)(this.parent.parent.getX() + 2), (float)(this.parent.parent.getY() + this.offset + 1), (float)(this.parent.parent.getX() + this.parent.parent.getWidth()), (float)(this.parent.parent.getY() + this.offset + 8 - 2), (float)this.o, new Color(51, 0, 51).getRGB());
        Frame.drawRoundedRect((float)(int)(this.parent.parent.getX() + this.renderWidth - 2.0), (float)(this.parent.parent.getY() + this.offset), (float)(this.parent.parent.getX() + (int)this.renderWidth + 8), (float)(this.parent.parent.getY() + this.offset + 6), (float)this.o, new Color(255, 102, 204).getRGB());
        Gui.func_73734_a(this.parent.parent.getX(), this.parent.parent.getY() + this.offset, this.parent.parent.getX() + 2, this.parent.parent.getY() + this.offset + 16, 730426);
        final String str = this.set.getName() + ": " + String.format("%.1f", this.set.getValDouble());
        mc.field_71466_p.func_175063_a(str, (float)(this.parent.parent.getX() + this.parent.parent.getWidth() / 2 - mc.field_71466_p.func_78256_a(str) / 2), (float)(this.parent.parent.getY() + this.offset + 6), -1);
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
        this.hovered = (this.isMouseOnButtonD(mouseX, mouseY) || this.isMouseOnButtonI(mouseX, mouseY));
        this.y = this.parent.parent.getY() + this.offset;
        this.x = this.parent.parent.getX();
        final double diff = Math.min(88, Math.max(0, mouseX - this.x));
        final double min = this.set.getMin();
        final double max = this.set.getMax();
        this.renderWidth = 88.0 * (this.set.getValDouble() - min) / (max - min);
        if (this.dragging) {
            if (diff == 0.0) {
                this.set.setValDouble(this.set.getMin());
            }
            else {
                final double newValue = roundToPlace(diff / 76.0 * (max - min) + min, 2);
                this.set.setValDouble(newValue);
            }
        }
    }
    
    private static double roundToPlace(final double value, final int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
    
    @Override
    public void mouseClicked(final int mouseX, final int mouseY, final int button) {
        if (this.isMouseOnButtonD(mouseX, mouseY) && button == 0 && this.parent.open) {
            this.dragging = true;
        }
        if (this.isMouseOnButtonI(mouseX, mouseY) && button == 0 && this.parent.open) {
            this.dragging = true;
        }
    }
    
    @Override
    public void mouseReleased(final int mouseX, final int mouseY, final int mouseButton) {
        this.dragging = false;
    }
    
    public boolean isMouseOnButtonD(final int x, final int y) {
        return x > this.x && x < this.x + (this.parent.parent.getWidth() / 2 + 1) && y > this.y && y < this.y + 16;
    }
    
    public boolean isMouseOnButtonI(final int x, final int y) {
        return x > this.x + this.parent.parent.getWidth() / 2 && x < this.x + this.parent.parent.getWidth() && y > this.y && y < this.y + 16;
    }
    
    public static void drawRect(float f, float f2, float f3, float f4, final int n) {
        if (f < f3) {
            final float f5 = f;
            f = f3;
            f3 = f5;
        }
        if (f2 < f4) {
            final float f5 = f2;
            f2 = f4;
            f4 = f5;
        }
        final float f5 = (n >> 24 & 0xFF) / 255.0f;
        final float f6 = (n >> 16 & 0xFF) / 255.0f;
        final float f7 = (n >> 8 & 0xFF) / 255.0f;
        final float f8 = (n & 0xFF) / 255.0f;
        final Tessellator tessellator = Tessellator.func_178181_a();
        final BufferBuilder bufferBuilder = tessellator.func_178180_c();
        GlStateManager.func_179147_l();
        GlStateManager.func_179090_x();
        GlStateManager.func_187428_a(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.func_179131_c(f6, f7, f8, f5);
        bufferBuilder.func_181668_a(7, DefaultVertexFormats.field_181705_e);
        bufferBuilder.func_181662_b((double)f, (double)f4, 0.0).func_181675_d();
        bufferBuilder.func_181662_b((double)f3, (double)f4, 0.0).func_181675_d();
        bufferBuilder.func_181662_b((double)f3, (double)f2, 0.0).func_181675_d();
        bufferBuilder.func_181662_b((double)f, (double)f2, 0.0).func_181675_d();
        tessellator.func_78381_a();
        GlStateManager.func_179098_w();
        GlStateManager.func_179084_k();
    }
}
