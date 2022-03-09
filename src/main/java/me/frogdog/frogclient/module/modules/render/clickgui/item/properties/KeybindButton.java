package me.frogdog.frogclient.module.modules.render.clickgui.item.properties;

import org.lwjgl.input.Keyboard;

import me.frogdog.frogclient.Frog;
import me.frogdog.frogclient.command.Command;
import me.frogdog.frogclient.module.Module;
import me.frogdog.frogclient.module.modules.render.clickgui.item.Button;
import me.frogdog.frogclient.properties.Property;
import me.frogdog.frogclient.util.minecraft.render.RenderMethods;
import me.frogdog.frogclient.util.minecraft.render.font.FontUtil;

public class KeybindButton extends Button {
    private Property property;
    private Module module;
    private boolean textToggle;

	public KeybindButton(Property property, Module module) {
		super(property.getAliases()[0]);
		this.property = property;
		this.module = module;
		this.width = 15;
		
	}
	
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        RenderMethods.drawRect(this.x, this.y, this.x + (float)this.width + 7.4f, this.y + (float)this.height, (!this.isHovering(mouseX, mouseY) ? 0x11555555 : -2007673515));
        FontUtil.drawString(textToggle ? "Press a key!": "Keybind" + " " + this.getKey(), this.x + 2.0f, this.y + 4.0f, -5592406);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        if (this.isHovering(mouseX, mouseY)) {
        	toggleText();
        	int clickGuiKey = Frog.getInstance().getKeybindManager().getKeybindByLabel("ClickGui").getKey();
        	if (Keyboard.getEventKey() != clickGuiKey && Keyboard.getEventKey() != Keyboard.KEY_DELETE) {
        		this.setKey(Keyboard.getEventKey());
        	} else if (Keyboard.getEventKey() == Keyboard.KEY_DELETE) {
        		this.setKey(Keyboard.KEY_NONE);
        	}
        }
    }

    @Override
    public int getHeight() {
        return 14;
    }
    
    public void toggleText() {
    	this.textToggle = !textToggle;
    }
    
    public void setKey(int keycode) {
    	Frog.getInstance().getKeybindManager().getKeybindByLabel(module.getLabel()).setKey(keycode);
    	Command.sendClientSideMessage(getLabel() +  "'s bind has been set to " + Keyboard.getKeyName(keycode));
    	toggleText();
    }
    
    public String getKey() {
    	int keycode =  Frog.getInstance().getKeybindManager().getKeybindByLabel(module.getLabel()).getKey();
    	return Keyboard.getKeyName(keycode);
    }

}
