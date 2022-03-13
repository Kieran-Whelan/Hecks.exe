package me.frogdog.frogclient.module.modules.misc;

import java.util.ArrayList;

import me.frogdog.frogclient.command.Command;
import me.frogdog.frogclient.event.Listener;
import me.frogdog.frogclient.event.events.RenderEvent;
import me.frogdog.frogclient.event.events.TickEvent;
import me.frogdog.frogclient.module.ModuleType;
import me.frogdog.frogclient.module.ToggleableModule;
import me.frogdog.frogclient.properties.Property;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public final class Notification extends ToggleableModule {
	private final Property<Boolean> visualRange = new Property<Boolean>(false, "VisualRange", "vr");
	private final Property<Boolean> totemPop = new Property<Boolean>(true, "TotemPop", "tp");
	
	int delay = 0;
	int totalTotems;
	int totemCount;
	ArrayList<String> players = new ArrayList<String>();

	public Notification() {
		super("Notification", new String[] {"notifs", "Notification", "notification"}, ModuleType.MISCELLANEOUS);
		this.offerProperties(this.visualRange, this.totemPop, this.keybind);
        this.listeners.add(new Listener<RenderEvent>("render_listener"){
        	
            @Override
            public void call(RenderEvent event) {
            	if(Notification.this.visualRange.getValue() == true) {
            		for(Entity player : mc.world.loadedEntityList) {
            			if(player instanceof EntityPlayer && player.getName() != mc.player.getName()) {
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
			            				Command.sendClientSideMessage(player.getName() + " has left visual range");
		            					players.remove(player.getName());
		            				}
		            			}
            			}
            		}
            	}
            }
	      			
	    });
        this.listeners.add(new Listener<TickEvent>("tick_event") {
            	
        		@Override
        		public void call(TickEvent event) {
	                if(Notification.this.totemPop.getValue() == true) {
	                	totemCount = mc.player.inventory.mainInventory.stream().filter(itemStack -> itemStack.getItem() == Items.TOTEM_OF_UNDYING).mapToInt(ItemStack::getCount).sum();
	                	if(totemCount > totalTotems) {
	                		Command.sendClientSideMessage("You just popped a totem, you now have " + totemCount + " totems left");
	                		totalTotems = totemCount;
	                	} else {
	                		totalTotems = totemCount;
	                	}
	                }
        		}
        });
	}
	
	private boolean isInRange(String player) {
		return mc.world.playerEntities.toString().contains(player);
	}
	
	@Override
	public void onEnable() {
		super.onEnable();
		totalTotems = mc.player.inventory.mainInventory.stream().filter(itemStack -> itemStack.getItem() == Items.TOTEM_OF_UNDYING).mapToInt(ItemStack::getCount).sum();
	}
}
    
