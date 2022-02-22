package me.frogdog.frogclient.module.modules.render;

import me.frogdog.api.event.Listener;
import me.frogdog.api.minecraft.render.RenderMethods;
import me.frogdog.frogclient.Frog;
import me.frogdog.frogclient.events.RenderGameInfoEvent;
import me.frogdog.frogclient.events.RenderGameOverlayEvent;
import me.frogdog.frogclient.events.ViewmodelEvent;
import me.frogdog.frogclient.module.Module;
import me.frogdog.frogclient.module.ModuleType;
import me.frogdog.frogclient.module.ToggleableModule;
import me.frogdog.frogclient.properties.NumberProperty;
import me.frogdog.frogclient.properties.Property;
import net.minecraft.client.gui.ScaledResolution;

public class NoRender extends ToggleableModule {
    private final Property<Boolean> customBars = new Property<Boolean>(true, "CustomBars", "c", "custom", "cb");
    private final Property<Boolean> nofov = new Property<Boolean>(false, "NoFOV", "nf");
    private final Property<Boolean> pumpkin = new Property<Boolean>(true, "NoPumpkin", "p", "np");
    private final Property<Boolean> fire = new Property<Boolean>(true, "NoFire", "fire", "nf");
    private final Property<Boolean> hurtcam = new Property<Boolean>(false, "NoHurtcam", "hurtcam", "nh");
    private final Property<Boolean> items = new Property<Boolean>(false, "NoItems", "items", "item", "ni");
    public final Property<Float> blockHeight = new NumberProperty<Float>(Float.valueOf(-0.2f), Float.valueOf(-1.5f), Float.valueOf(1.5f), "BlockHeight");

    public NoRender() {
        super("NoRender", new String[]{"render", "norender", "nr"}, -2366720, ModuleType.RENDER);
        this.offerProperties(pumpkin, customBars, fire, hurtcam, items, nofov, blockHeight);
        this.listeners.add(new Listener<RenderGameOverlayEvent>("textgui_render_game_info_listener"){
        	
            @Override
            public void call(RenderGameOverlayEvent event) {
            }
        });
        }
}

