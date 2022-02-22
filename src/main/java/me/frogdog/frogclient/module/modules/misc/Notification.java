package me.frogdog.frogclient.module.modules.misc;

import java.util.ArrayList;

import me.frogdog.frogclient.Frog;
import me.frogdog.frogclient.command.Command;
import me.frogdog.frogclient.event.Listener;
import me.frogdog.frogclient.event.events.TickEvent;
import me.frogdog.frogclient.module.ModuleType;
import me.frogdog.frogclient.module.ToggleableModule;
import me.frogdog.frogclient.properties.Property;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class Notification extends ToggleableModule {
	private final Property<Boolean> visualRange = new Property<Boolean>(false, "VisualRange", "vr");
	private final Property<Boolean> totemPop = new Property<Boolean>(true, "TotemPop", "tp");

	public Notification() {
		super("Notification", new String[] {"notifs", "Notification", "notification"}, -2366720, ModuleType.MISCELLANEOUS);
		this.offerProperties(this.visualRange, this.totemPop);
        this.listeners.add(new Listener<TickEvent>("tick_listener"){
        	
        	int delay = 0;
        	ArrayList<String> players = new ArrayList<String>();
        	
            @Override
            public void call(TickEvent event) {
            	if(Notification.this.visualRange.getValue() == true) {
            		for(Entity player : Frog.getInstance().mc.world.loadedEntityList) {
            			if(player instanceof EntityPlayer) {
            				delay++;
		            			if(!players.contains(player.getName())) {
		            				if(delay > 10) {
			            				Command.sendClientSideMessage(player.getName() + " has entered visual range");
			            				players.add(player.getName());
			            				delay = 0;
		            				}
		            			}
		            			
		            			if(!isInRange(player.getName())) {
		            				if(players.toString().contains(player.getName())) {
		            					players.remove(player.getName());
		            				}
		            			}
		            			
	            			}
            			
            		}
            		
            	}
            	
                if(Notification.this.totemPop.getValue() == true) {
                }
            }
            

        });
	}
	
	private boolean isInRange(String player) {
		return Frog.getInstance().mc.world.playerEntities.toString().contains(player);
	}
}
