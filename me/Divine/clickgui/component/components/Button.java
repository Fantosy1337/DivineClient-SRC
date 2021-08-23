package me.Divine.clickgui.component.components;

import me.Divine.module.*;
import me.Divine.clickgui.component.*;
import net.minecraft.client.*;
import me.Divine.*;
import me.Divine.settings.*;
import me.Divine.clickgui.component.components.sub.*;
import java.util.*;
import java.awt.*;
import net.minecraft.client.gui.*;
import me.Divine.module.render.*;
import me.Divine.font.*;

public class Button extends Component
{
    public Module mod;
    public Frame parent;
    public int offset;
    private boolean isHovered;
    private ArrayList<Component> subcomponents;
    public boolean open;
    private int height;
    FontRenderer fr;
    int y;
    
    public Button(final Module mod, final Frame parent, final int offset) {
        this.fr = Minecraft.func_71410_x().field_71466_p;
        this.y = 2;
        this.mod = mod;
        this.parent = parent;
        this.offset = offset;
        this.subcomponents = new ArrayList<Component>();
        this.open = false;
        this.height = 16;
        int opY = offset + 16;
        final Divine instance = Divine.instance;
        if (Divine.settingsManager.getSettingsByMod(mod) != null) {
            final Divine instance2 = Divine.instance;
            for (final Setting s : Divine.settingsManager.getSettingsByMod(mod)) {
                if (s.isCombo()) {
                    this.subcomponents.add(new ModeButton(s, this, mod, opY));
                    opY += 16;
                }
                if (s.isSlider()) {
                    this.subcomponents.add(new Slider(s, this, opY));
                    opY += 16;
                }
                if (s.isCheck()) {
                    this.subcomponents.add(new Checkbox(s, this, opY));
                    opY += 16;
                }
            }
        }
        this.subcomponents.add(new Keybind(this, opY));
    }
    
    @Override
    public void setOff(final int newOff) {
        this.offset = newOff + 2;
        int opY = this.offset + 15;
        for (final Component comp : this.subcomponents) {
            comp.setOff(opY);
            opY += 16;
        }
    }
    
    @Override
    public void renderComponent() {
        final int BG = new Color(0, 0, 0, 140).getRGB();
        final int modToggleText = new Color(153, 0, 0).getRGB();
        final int modNotToggleBG = new Color(51, 0, 51).getRGB();
        final int modToggleBG = new Color(51, 0, 51).getRGB();
        Gui.func_73734_a(this.parent.getX(), this.parent.getY() + this.offset, this.parent.getX() + this.parent.getWidth(), this.parent.getY() + 16 + this.offset, new Color(0, 0, 0, 120).getRGB());
        if (this.mod.isToggled()) {
            Gui.func_73734_a(this.parent.getX() + 4, this.parent.getY() + this.offset, this.parent.getX() - 110 + this.parent.getWidth() + 1, this.parent.getY() + 10 + this.offset, HUD.rainbow(10, this.y));
        }
        Fonts.font.drawStringWithShadow(this.mod.getName2(), this.parent.getX() + this.parent.getWidth() / 2 - this.fr.func_78256_a(this.mod.getName2()) + this.fr.func_78256_a(this.mod.getName2()) / 2, this.parent.getY() + this.offset + 1 + 2, -1);
        if (this.subcomponents.size() > 1) {
            Minecraft.func_71410_x().field_71466_p.func_175063_a(this.open ? "<" : ">", (float)(this.parent.getX() + this.parent.getWidth() - 10), (float)(this.parent.getY() + this.offset + 2 + 2), -1);
        }
        if (this.open && !this.subcomponents.isEmpty()) {
            for (final Component comp : this.subcomponents) {
                comp.renderComponent();
            }
        }
    }
    
    @Override
    public int getHeight() {
        if (this.open) {
            return 16 * (this.subcomponents.size() + 1);
        }
        return 16;
    }
    
    @Override
    public void updateComponent(final int mouseX, final int mouseY) {
        this.isHovered = this.isMouseOnButton(mouseX, mouseY);
        if (!this.subcomponents.isEmpty()) {
            for (final Component comp : this.subcomponents) {
                comp.updateComponent(mouseX, mouseY);
            }
        }
    }
    
    @Override
    public void mouseClicked(final int mouseX, final int mouseY, final int button) {
        if (this.isMouseOnButton(mouseX, mouseY) && button == 0) {
            this.mod.toggle();
        }
        if (this.isMouseOnButton(mouseX, mouseY) && button == 1) {
            this.open = !this.open;
            this.parent.refresh();
        }
        for (final Component comp : this.subcomponents) {
            comp.mouseClicked(mouseX, mouseY, button);
        }
    }
    
    @Override
    public void mouseReleased(final int mouseX, final int mouseY, final int mouseButton) {
        for (final Component comp : this.subcomponents) {
            comp.mouseReleased(mouseX, mouseY, mouseButton);
        }
    }
    
    @Override
    public void keyTyped(final char typedChar, final int key) {
        for (final Component comp : this.subcomponents) {
            comp.keyTyped(typedChar, key);
        }
    }
    
    public boolean isMouseOnButton(final int x, final int y) {
        return x > this.parent.getX() && x < this.parent.getX() + this.parent.getWidth() && y > this.parent.getY() + this.offset && y < this.parent.getY() + 12 + this.offset;
    }
    
    public static int rainbow(final int delay, final long index) {
        double rainbowState = Math.ceil((double)(System.currentTimeMillis() + index + delay)) / 15.0;
        rainbowState %= 360.0;
        return Color.getHSBColor((float)(rainbowState / 360.0), 0.4f, 1.0f).getRGB();
    }
}
