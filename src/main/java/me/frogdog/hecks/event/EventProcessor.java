package me.frogdog.hecks.event;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.frogdog.hecks.Hecks;
import me.frogdog.hecks.command.Command;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class EventProcessor {

    public static final EventProcessor INSTANCE = new EventProcessor();

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
    }

    @SubscribeEvent
    public void onChatSend(ClientChatEvent event) {
        String message = event.getMessage();
        if (message.startsWith(Command.getPrefix())) {
            event.setCanceled(true);
            try {
                Hecks.getInstance().mc.ingameGUI.getChatGUI().addToSentMessages(message);
                Hecks.getInstance().getCommandManager().callClientCommand(message.substring(1));
            } catch (Exception e) {
                e.printStackTrace();
                Command.sendClientSideMessage(ChatFormatting.DARK_GREEN + "Error: " + e.getMessage());
            }
        }
    }
}
