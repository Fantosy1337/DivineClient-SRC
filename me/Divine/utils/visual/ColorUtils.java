package me.Divine.utils.visual;

import java.awt.*;

public class ColorUtils
{
    public static Color rainbow() {
        final long offset = 999999999999L;
        final float fade = 1.0f;
        final float hue = (System.nanoTime() + offset) / 1.0E10f % 1.0f;
        final long color = Long.parseLong(Integer.toHexString(Color.HSBtoRGB(hue, 1.0f, 1.0f)), 16);
        final Color c = new Color((int)color);
        return new Color(c.getRed() / 255.0f * fade, c.getGreen() / 255.0f * fade, c.getBlue() / 255.0f * fade, c.getAlpha() / 255.0f);
    }
    
    public static int color(final int r, final int g, final int b, final int a) {
        return new Color(r, g, b, a).getRGB();
    }
    
    public static int color(final float r, final float g, final float b, final float a) {
        return new Color(r, g, b, a).getRGB();
    }
    
    public static int getColor(final int a, final int r, final int g, final int b) {
        return a << 24 | r << 16 | g << 8 | b;
    }
    
    public static int getColor(final int r, final int g, final int b) {
        return 0xFF000000 | r << 16 | g << 8 | b;
    }
    
    public static Color fade(final Color color, final int i, final int j) {
        return fade(color, 2, 100);
    }
    
    public static int astolfo(final int delay, final float offset) {
        float speed;
        float hue;
        for (speed = 3000.0f, hue = Math.abs(System.currentTimeMillis() % delay + -offset / 21.0f * 2.0f); hue > speed; hue -= speed) {}
        hue /= speed;
        if (hue > 0.5) {
            hue = 0.5f - (hue - 0.5f);
        }
        hue += 0.5f;
        final Color color = new Color(255, 0, 0);
        final float[] hsb = new float[3];
        Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), hsb);
        return Color.HSBtoRGB(hue + color.getRGB(), 0.5f, 1.0f);
    }
}
