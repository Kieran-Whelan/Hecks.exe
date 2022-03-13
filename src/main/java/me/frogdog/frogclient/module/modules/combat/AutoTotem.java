package me.frogdog.frogclient.module.modules.combat;

import me.frogdog.frogclient.event.Listener;
import me.frogdog.frogclient.event.events.TickEvent;
import me.frogdog.frogclient.module.ModuleType;
import me.frogdog.frogclient.module.ToggleableModule;
import net.minecraft.client.gui.*;
import net.minecraft.client.gui.inventory.*;
import net.minecraft.init.Items;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public final class AutoTotem extends ToggleableModule {
	
	int totalTotems;

	public AutoTotem() {
		super("AutoTotem", new String[] {"AutoTotem", "autototem"}, ModuleType.COMBAT);
        this.offerProperties(this.keybind);
		this.listeners.add(new Listener<TickEvent>("tick_listener") {

			@Override
			public void call(TickEvent event) {
				totalTotems = mc.player.inventory.mainInventory.stream().filter(itemStack -> itemStack.getItem() == Items.TOTEM_OF_UNDYING).mapToInt(ItemStack::getCount).sum();
				
				for(int i = 0; i < 45; i++) {
					if(totalTotems >= 1) {
						//all other guis
						if(!(mc.currentScreen instanceof GuiBeacon) && !(mc.currentScreen instanceof GuiBrewingStand) && !(mc.currentScreen instanceof GuiCrafting) && !(mc.currentScreen instanceof GuiChest) && !(mc.currentScreen instanceof GuiCommandBlock) && !(mc.currentScreen instanceof GuiFurnace) && !(mc.currentScreen instanceof GuiDispenser) && !(mc.currentScreen instanceof GuiEnchantment) && !(mc.currentScreen instanceof GuiShulkerBox)  && !(mc.currentScreen instanceof GuiHopper) && !(mc.currentScreen instanceof GuiContainerCreative)) {
							ItemStack itemStack = mc.player.openContainer.getSlot(i).getStack();
						
							if(itemStack.isEmpty) {
								continue;
							}
							
							Item totem = Items.TOTEM_OF_UNDYING;
							if(mc.player.getHeldItemOffhand().isEmpty() && itemStack.getItem() == totem) {
								mc.playerController.windowClick(0, i, 1, ClickType.PICKUP, mc.player);
								mc.playerController.windowClick(0, 45, 1, ClickType.PICKUP, mc.player);
							}
						}
					}
				}
				
			}
			
		});
	}

}
