package me.frogdog.frogclient.events;

import com.mojang.realmsclient.gui.ChatFormatting;

import me.frogdog.api.event.Event;
import me.frogdog.frogclient.Frog;
import me.frogdog.frogclient.command.Command;
import net.minecraft.client.Minecraft;

public class ChatEvent extends Event {
	
    public ChatEvent(String message) {
    	Frog.getInstance().log.info(message);


        }
    }
