package me.Divine.clickgui.component.components.sub;

import me.Divine.clickgui.component.*;
import me.Divine.clickgui.component.components.*;
import me.Divine.settings.*;
import me.Divine.module.*;
import java.awt.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.*;

public class ModeButton extends Component
{
    private boolean hovered;
    private Button parent;
    private Setting set;
    private int offset;
    private int x;
    private int y;
    private Module mod;
    private int modeIndex;
    
    public ModeButton(final Setting set, final Button button, final Module mod, final int offset) {
        this.set = set;
        this.parent = button;
        this.mod = mod;
        this.x = button.parent.getX() + button.parent.getWidth();
        this.y = button.parent.getY() + button.offset;
        this.offset = offset;
        this.modeIndex = 0;
    }
    
    @Override
    public void setOff(final int newOff) {
        this.offset = newOff;
    }
    
    @Override
    public void renderComponent() {
        Gui.func_73734_a(this.parent.parent.getX(), this.parent.parent.getY() + this.offset, this.parent.parent.getX() + this.parent.parent.getWidth() * 1, this.parent.parent.getY() + this.offset + 14 + 2, new Color(0, 0, 0, 220).getRGB());
        Minecraft.func_71410_x().field_71466_p.func_175063_a("Mode: " + this.set.getValString(), (float)(this.parent.parent.getX() + this.parent.parent.getWidth() / 2 - Minecraft.func_71410_x().field_71466_p.func_78256_a("Mode: " + this.set.getValString()) / 2), (float)(this.parent.parent.getY() + this.offset + 3), -1);
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
            final int maxIndex = this.set.getOptions().size() - 2;
            if (this.modeIndex > maxIndex) {
                this.modeIndex = 0;
            }
            else {
                ++this.modeIndex;
            }
            this.set.setValString(this.set.getOptions().get(this.modeIndex));
        }
    }
    
    public boolean isMouseOnButton(final int x, final int y) {
        return x > this.x && x < this.x + 88 && y > this.y && y < this.y + 12;
    }
}
