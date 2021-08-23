package me.Divine.font;

import java.awt.*;
import java.io.*;

public class Fonts3
{
    public static MCFontRenderer font;
    public static MCFontRenderer fontFrame;
    public static MCFontRenderer fontButton;
    
    public static Font font(final float s) {
        try {
            return Font.createFont(0, new File("C:\\Users\\os10\\Desktop\\MODS\\External CLient\\run\\9.ttf")).deriveFont(s);
        }
        catch (Exception e) {
            return null;
        }
    }
    
    static {
        Fonts3.font = new MCFontRenderer(font(22.0f), false, true);
        Fonts3.fontFrame = new MCFontRenderer(font(20.0f), false, true);
        Fonts3.fontButton = new MCFontRenderer(font(28.0f), false, true);
    }
}
