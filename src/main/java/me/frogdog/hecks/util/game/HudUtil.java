package me.frogdog.hecks.util.game;

import me.frogdog.hecks.Hecks;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class HudUtil {
    private static final FontRenderer fontRenderer = Hecks.mc.fontRenderer;

    public static void enableGL2D() {
        GL11.glDisable(2929);
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glDepthMask(true);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glHint(3155, 4354);
    }

    public static void disableGL2D() {
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glEnable(2929);
        GL11.glDisable(2848);
        GL11.glHint(3154, 4352);
        GL11.glHint(3155, 4352);
    }

    public static void drawRect(float x, float y, float x1, float y1, int color) {
        HudUtil.enableGL2D();
        HudUtil.glColor(color);
        HudUtil.drawRect(x, y, x1, y1);
        HudUtil.disableGL2D();
    }

    public static void drawGradientRect(float x, float y, float x1, float y1, int topColor, int bottomColor) {
        HudUtil.enableGL2D();
        GL11.glShadeModel(7425);
        GL11.glBegin(7);
        HudUtil.glColor(topColor);
        GL11.glVertex2f(x, y1);
        GL11.glVertex2f(x1, y1);
        HudUtil.glColor(bottomColor);
        GL11.glVertex2f(x1, y);
        GL11.glVertex2f(x, y);
        GL11.glEnd();
        GL11.glShadeModel(7424);
        HudUtil.disableGL2D();
    }

    public static void drawRect(float x, float y, float x1, float y1) {
        GL11.glBegin(7);
        GL11.glVertex2f(x, y1);
        GL11.glVertex2f(x1, y1);
        GL11.glVertex2f(x1, y);
        GL11.glVertex2f(x, y);
        GL11.glEnd();
    }

    public static void glColor(Color color) {
        GL11.glColor4f((float) color.getRed() / 255.0f, (float) color.getGreen() / 255.0f, (float) color.getBlue() / 255.0f, (float) color.getAlpha() / 255.0f);
    }

    public static void glColor(int hex) {
        float alpha = (float) (hex >> 24 & 0xFF) / 255.0f;
        float red = (float) (hex >> 16 & 0xFF) / 255.0f;
        float green = (float) (hex >> 8 & 0xFF) / 255.0f;
        float blue = (float) (hex & 0xFF) / 255.0f;
        GL11.glColor4f(red, green, blue, alpha);
    }

    public static void drawModalRect(int var0, int var1, float var2, float var3, int var4, int var5, int var6, int var7, float var8, float var9) {
        Gui.drawScaledCustomSizeModalRect(var0, var1, var2, var3, var4, var5, var6, var7, var8, var9);
    }

    public static void drawString(String text, float x, float y, int colour) {
        fontRenderer.drawString(text, x, y, colour, true);
    }

    public static float getStringWidth(String text) {
        return fontRenderer.getStringWidth(text);
    }
}
