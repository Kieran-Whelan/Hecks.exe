package me.frogdog.api.event;

import com.mojang.realmsclient.gui.ChatFormatting;

import akka.io.Tcp.Register;
import me.frogdog.frogclient.Frog;
import me.frogdog.frogclient.command.Command;
import me.frogdog.frogclient.module.impl.toggle.movement.Sprint;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class EventProcessor {

    public static final EventProcessor INSTANCE = new EventProcessor();

    @SubscribeEvent
    public void onInput(InputEvent event) {
        me.frogdog.frogclient.events.InputEvent inputEvent = new me.frogdog.frogclient.events.InputEvent(me.frogdog.frogclient.events.InputEvent.Type.KEYBOARD_KEY_PRESS);

        Frog.getInstance().getEventManager().dispatch(inputEvent);
    }
    
    @SubscribeEvent
    public void onChatSend(ClientChatEvent event) {
    	String message = event.getMessage();
        if (message.startsWith(Command.getClientPrefix())) {
            event.setCanceled(true);
            try {
                Frog.getInstance().mc.ingameGUI.getChatGUI().addToSentMessages(message);
                Frog.getInstance().getCommandManager().callClientCommand(message.substring(1));
            } catch (Exception e) {
                e.printStackTrace();
                Command.sendClientSideMessage(ChatFormatting.DARK_GREEN + "Error: " + e.getMessage());
            }
        }
        me.frogdog.frogclient.events.ChatEvent chatEvent = new me.frogdog.frogclient.events.ChatEvent(null);
        Frog.getInstance().log.info("EventProc: " + message);
        
    	Frog.getInstance().getEventManager().dispatch(chatEvent);
    }
    
	@SubscribeEvent
	public void onTick(TickEvent.ClientTickEvent event) {
		if (Frog.getInstance().mc.player != null) {
			Sprint.onTick();
		}
	}
    
}
