package me.frogdog.frogclient.module.modules.render;

import me.frogdog.frogclient.Frog;
import me.frogdog.frogclient.event.Listener;
import me.frogdog.frogclient.event.events.PacketEvent;
import me.frogdog.frogclient.event.events.RenderEvent;
import me.frogdog.frogclient.module.ModuleType;
import me.frogdog.frogclient.module.ToggleableModule;
import me.frogdog.frogclient.properties.Property;
import net.minecraft.init.MobEffects;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.fml.common.Mod.EventHandler;

public final class NoRender extends ToggleableModule {
    private final Property<Boolean> pumpkin = new Property<Boolean>(true, "NoPumpkin", "p", "np");
    private final Property<Boolean> fire = new Property<Boolean>(true, "NoFire", "fire", "nf");
    private final Property<Boolean> hurtcam = new Property<Boolean>(false, "NoHurtcam", "hurtcam", "nh");
    private final Property<Boolean> armour = new Property<Boolean>(false, "NoArmour", "armour",  "na");
    private final Property<Boolean> portalNausea = new Property<Boolean>(false, "NoPortalNausea", "portalnausea",  "np");
    
    public NoRender() {
        super("NoRender", new String[]{"render", "norender", "nr"}, ModuleType.RENDER);
        this.offerProperties(this.pumpkin, this.fire, this.hurtcam, this.armour, this.portalNausea, this.keybind);
        this.listeners.add(new Listener<RenderEvent>("render_listener"){

            @Override
            public void call(RenderEvent event) {
            	if(NoRender.this.pumpkin.getValue() == true) {
            		
            	}
            	
            	if(NoRender.this.fire.getValue() == true) {
            		
            	}
            	
            	if(NoRender.this.hurtcam.getValue() == true) {
            		
            	}
            	
            	if(NoRender.this.portalNausea.getValue() == true) {
        			GuiIngameForge.renderPortal = false;
        			mc.player.removeActivePotionEffect(MobEffects.NAUSEA);
            	}
            	
            }
            
        });
        

        }
}

