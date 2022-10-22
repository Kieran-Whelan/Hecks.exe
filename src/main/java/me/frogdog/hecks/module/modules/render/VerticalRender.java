package me.frogdog.hecks.module.modules.render;

import me.frogdog.hecks.module.ModuleType;
import me.frogdog.hecks.module.ToggleableModule;
import me.frogdog.hecks.property.NumberProperty;
import me.frogdog.hecks.util.game.RenderUtil;
import net.minecraftforge.client.event.RenderWorldLastEvent;

public final class VerticalRender extends ToggleableModule {
    private final NumberProperty<Integer> height = new NumberProperty<>(10, 0, 64, "Height");

    public VerticalRender() {
        super("VertRender", new String[]{"verticalrender", "VerticalRender"}, "Allows you to see blocks that that align with the player vertically", ModuleType.RENDER);
        this.offerProperties(this.height, this.keybind);
    }

    @Override
    public void render(RenderWorldLastEvent event) {
        for (int i = 0; i < height.getValue(); i++) {
            RenderUtil.drawBlockOutline(mc.player.getPosition(), i);
        }
    }

}
