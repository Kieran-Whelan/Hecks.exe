package me.frogdog.hecks.util.render.ui;

import me.frogdog.hecks.Hecks;
import net.minecraft.client.gui.FontRenderer;

public class FontUtil {
    private static final FontRenderer fontRenderer = Hecks.getInstance().mc.fontRenderer;

    public static void drawString(String text, float x, float y, int colour) {
        fontRenderer.drawString(text, x, y, colour, true);
    }

    public static float getStringWidth(String text) {
        return fontRenderer.getStringWidth(text);
    }
}
