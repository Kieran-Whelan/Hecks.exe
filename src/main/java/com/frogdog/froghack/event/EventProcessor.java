package com.frogdog.froghack.event;

import com.frogdog.froghack.Frog;
import com.frogdog.froghack.utils.Logger;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemPickupEvent;

public class EventProcessor {
	public static final EventProcessor INSTANCE = new EventProcessor();
    
	@SubscribeEvent
	public void entityJoinWorld(EntityJoinWorldEvent event) {
		if (event.getEntity() instanceof EntityPlayerSP) {
			Logger.info("Client player joined world. Unregistering EntityEventHandler.");

			final EntityPlayerSP player = (EntityPlayerSP) event.getEntity();
			player.sendChatMessage("Hello from the client!");
			player.sendMessage(null);
		}
		
	}
	
}
