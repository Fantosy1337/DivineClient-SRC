package me.Divine.font;

import java.awt.*;
import java.io.*;

public class Fonts4
{
    public static MCFontRenderer font;
    public static MCFontRenderer fontFrame;
    public static MCFontRenderer fontButton;
    
    public static Font font(final float s) {
        try {
            return Font.createFont(0, new File("C:\\Users\\os10\\Desktop\\MODS\\External Client\\run\\13.ttf")).deriveFont(s);
        }
        catch (Exception e) {
            return null;
        }
    }
    
    static {
        Fonts4.font = new MCFontRenderer(font(22.0f), false, true);
        Fonts4.fontFrame = new MCFontRenderer(font(20.0f), false, true);
        Fonts4.fontButton = new MCFontRenderer(font(28.0f), false, true);
    }
}
