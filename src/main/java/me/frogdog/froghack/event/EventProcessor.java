package me.frogdog.froghack.event;

import org.lwjgl.input.Keyboard;

import com.mojang.realmsclient.gui.ChatFormatting;

import me.frogdog.froghack.Frog;
import me.frogdog.froghack.command.Command;
import me.frogdog.froghack.command.CommandManager;
import me.frogdog.froghack.module.Module;
import me.frogdog.froghack.module.ModuleManager;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;

public class EventProcessor {
	
	private Minecraft mc = Minecraft.getMinecraft();
	CommandManager commandManager = new CommandManager();
    
    @SubscribeEvent
    public void onChatSent(ClientChatEvent event) {

        if (event.getMessage().startsWith(Command.getClientPrefix())) {
            event.setCanceled(true);
            try {
                mc.ingameGUI.getChatGUI().addToSentMessages(event.getMessage());
                commandManager.callClientCommand(event.getMessage().substring(1));
            } catch (Exception e) {
                e.printStackTrace();
                Command.sendClientSideMessage(ChatFormatting.DARK_GREEN + "Error: " + e.getMessage());
            }

        }
    }
    
	@SubscribeEvent
	public void key(KeyInputEvent event) {
		if(Minecraft.getMinecraft().world == null || Minecraft.getMinecraft().player == null)
			return;
		
		try {
			if(Keyboard.isCreated()) {
				if(Keyboard.getEventKeyState()) {
					int keyCode = Keyboard.getEventKey();
					if(keyCode <= 0)
						return;
					for(Module m : Frog.moduleManager.modules) {
						if(m.getKey() == keyCode && keyCode > 0) {
							m.toggle();
						}
					}
				}
			}
		} catch (Exception q) {
			q.printStackTrace();
		}
	}
	
	@SubscribeEvent
	public void onTick(TickEvent.ClientTickEvent event) {
		if (mc.player != null) {
			ModuleManager.onUpdate();
		}
	}
	
}
