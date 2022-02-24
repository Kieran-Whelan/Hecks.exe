package me.frogdog.frogclient.module.modules.render.clickgui.item;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.util.ResourceLocation;

import java.awt.*;

import me.frogdog.frogclient.module.modules.client.Colors;
import me.frogdog.frogclient.module.modules.render.clickgui.ClickGui;
import me.frogdog.frogclient.module.modules.render.clickgui.Panel;
import me.frogdog.frogclient.util.interfaces.Labeled;
import me.frogdog.frogclient.util.minecraft.render.RenderMethods;
import me.frogdog.frogclient.util.minecraft.render.font.FontUtil;

public class Button extends Item implements Labeled {
    private boolean state;

    public Button(String label) {
        super(label);
        this.height = 15;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        RenderMethods.drawGradientRect(this.x, this.y, this.x + (float)this.width, this.y + (float)this.height, this.getState() ? (!this.isHovering(mouseX, mouseY) ? Colors.getClientColorCustomAlpha(77) : Colors.getClientColorCustomAlpha(55)) : (!this.isHovering(mouseX, mouseY) ? 0x33555555 : 0x77AAAAAB), this.getState() ? (!this.isHovering(mouseX, mouseY) ? Colors.getClientColorCustomAlpha(77) : Colors.getClientColorCustomAlpha(55)) : (!this.isHovering(mouseX, mouseY) ? 0x55555555 : 0x66AAAAAB));

        FontUtil.drawString(this.getLabel(), this.x + 2.0f, this.y + 4.0f, this.getState() ? -1 : -5592406);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (mouseButton == 0 && this.isHovering(mouseX, mouseY)) {
            this.state = !this.state;
            this.toggle();
//            Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.createPositionedSoundRecord(new ResourceLocation("random.click"), 1.0f));
        }
    }

    public void toggle() {
    }

    public boolean getState() {
        return this.state;
    }

    @Override
    public int getHeight() {
        return 14;
    }

    protected boolean isHovering(int mouseX, int mouseY) {
        for (Panel panel : ClickGui.getClickGui().getPanels()) {
            if (!panel.drag) continue;
            return false;
        }
        return (float)mouseX >= this.getX() && (float)mouseX <= this.getX() + (float)this.getWidth() && (float)mouseY >= this.getY() && (float)mouseY <= this.getY() + (float)this.height;
    }
}

