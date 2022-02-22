package me.frogdog.frogclient.util;

import me.frogdog.frogclient.ui.Hud;
import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordUser;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;
import net.arikia.dev.drpc.callbacks.ReadyCallback;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class DiscordRP {
	
	private boolean run = true;
	private long created = 0;
	
	public void start() {
		
		this.created = System.currentTimeMillis();
		
		DiscordEventHandlers handlers = new DiscordEventHandlers.Builder().setReadyEventHandler(new ReadyCallback() {

			@Override
			public void apply(DiscordUser user) {
				update("Booting up....", "");
				
			}
			
		}).build();
		
		DiscordRPC.discordInitialize("942195496674533407", handlers, true);
		
		new Thread("Discord RPC callback") {
			
			@Override
			public void run() {
				
				while(run) {
					DiscordRPC.discordRunCallbacks();
				}
			}
			
		}.start();
	}
	
	public void shutDown() {
		run = false;
		DiscordRPC.discordShutdown();
	}
	
	public void update(String firstLine, String secondLine) {
		DiscordRichPresence.Builder b = new DiscordRichPresence.Builder(secondLine);
		b.setBigImage("large", "");
		b.setDetails(firstLine);
		b.setStartTimestamps(created);
		
		DiscordRPC.discordUpdatePresence(b.build());
	} 
}
