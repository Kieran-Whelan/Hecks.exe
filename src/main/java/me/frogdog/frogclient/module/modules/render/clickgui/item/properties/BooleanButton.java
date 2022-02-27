package me.frogdog.frogclient.module.modules.render.clickgui.item.properties;

import me.frogdog.frogclient.module.modules.client.Colors;
import me.frogdog.frogclient.module.modules.render.clickgui.ClickGui;
import me.frogdog.frogclient.module.modules.render.clickgui.item.Button;
import me.frogdog.frogclient.properties.Property;
import me.frogdog.frogclient.util.minecraft.render.RenderMethods;
import me.frogdog.frogclient.util.minecraft.render.font.FontUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.util.ResourceLocation;

public class BooleanButton extends Button {
    private Property property;

    public BooleanButton(Property property) {
        super(property.getAliases()[0]);
        this.property = property;
        this.width = 15;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
//        RenderMethods.drawRect(this.x, this.y, this.x + (float)this.width + 7.4f, this.y + (float)this.height, this.getState() ? (!this.isHovering(mouseX, mouseY) ? 2012955202 : -1711586750) : (!this.isHovering(mouseX, mouseY) ? 0x11555555 : -2007673515));
        RenderMethods.drawRect(this.x, this.y, this.x + (float)this.width + 7.4f, this.y + (float)this.height, this.getState() ? (!this.isHovering(mouseX, mouseY) ? Colors.getClientColorCustomAlpha(77) : Colors.getClientColorCustomAlpha(77)) : (!this.isHovering(mouseX, mouseY) ? 0x11555555 : -2007673515));
        FontUtil.drawString(this.getLabel(), this.x + 2.0f, this.y + 4.0f, this.getState() ? -1 : -5592406);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        if (this.isHovering(mouseX, mouseY)) {
//            Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.createPositionedSoundRecord(new ResourceLocation("random.click"), 1.0f));
        }
    }

    @Override
    public int getHeight() {
        return 14;
    }

    @Override
    public void toggle() {
        this.property.setValue((Boolean)this.property.getValue() == false);
    }

    @Override
    public boolean getState() {
        return (Boolean)this.property.getValue();
    }
}

