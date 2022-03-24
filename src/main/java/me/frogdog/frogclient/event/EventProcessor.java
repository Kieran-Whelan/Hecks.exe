package me.frogdog.frogclient.event;

import me.frogdog.frogclient.event.events.PacketEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraftforge.fml.common.network.PacketLoggingHandler;
import org.lwjgl.input.Keyboard;

import com.mojang.realmsclient.gui.ChatFormatting;

import me.frogdog.frogclient.Frog;
import me.frogdog.frogclient.command.Command;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.client.event.PlayerSPPushOutOfBlocksEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class EventProcessor {

    public static final EventProcessor INSTANCE = new EventProcessor();

    @SubscribeEvent
    public void onInput(InputEvent.KeyInputEvent event) {
    	if(!(Keyboard.isRepeatEvent()) && !(Keyboard.isKeyDown(Keyboard.getEventKey()))) {
            me.frogdog.frogclient.event.events.InputEvent inputEvent = new me.frogdog.frogclient.event.events.InputEvent(me.frogdog.frogclient.event.events.InputEvent.Type.KEYBOARD_KEY_PRESS);

            Frog.getInstance().getEventManager().dispatch(inputEvent);
    	}
    }
    
    @SubscribeEvent
    public void onRenderGameOverlay(RenderGameOverlayEvent event) {
        me.frogdog.frogclient.event.events.RenderGameOverlayEvent renderEvent = new me.frogdog.frogclient.event.events.RenderGameOverlayEvent(me.frogdog.frogclient.event.events.RenderGameOverlayEvent.Type.IN_GAME);

        Frog.getInstance().getEventManager().dispatch(renderEvent);
    }
    
    @SubscribeEvent
    public void onChatSend(ClientChatEvent event) {
    	String message = event.getMessage();
        if (message.startsWith(Command.getPrefix())) {
            event.setCanceled(true);
            try {
                Frog.getInstance().mc.ingameGUI.getChatGUI().addToSentMessages(message);
                Frog.getInstance().getCommandManager().callClientCommand(message.substring(1));
            } catch (Exception e) {
                e.printStackTrace();
                Command.sendClientSideMessage(ChatFormatting.DARK_GREEN + "Error: " + e.getMessage());
            }
        }
    }
    
    @SubscribeEvent
    public void onTick(TickEvent event) {
    	if(!(Frog.getInstance().mc.player == null)) {
    		 me.frogdog.frogclient.event.events.TickEvent tickEvent = new me.frogdog.frogclient.event.events.TickEvent();
    		 
    		 Frog.getInstance().getEventManager().dispatch(tickEvent);

    	}
    	
    }
    
    @SubscribeEvent
    public void onRender(RenderWorldLastEvent event) {
    	if(!(Frog.getInstance().mc.player == null)) {
             me.frogdog.frogclient.event.events.RenderEvent renderEvent = new me.frogdog.frogclient.event.events.RenderEvent(0);

             Frog.getInstance().getEventManager().dispatch(renderEvent);

    	}
    	
    }
    
}
