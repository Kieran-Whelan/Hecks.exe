package me.frogdog.hecks.module.modules.render;

import me.frogdog.hecks.Hecks;
import me.frogdog.hecks.module.ModuleType;
import me.frogdog.hecks.module.ToggleableModule;
import me.frogdog.hecks.property.NumberProperty;
import me.frogdog.hecks.util.game.RenderUtil;
import net.minecraft.entity.Entity;
import net.minecraftforge.client.event.RenderWorldLastEvent;

public final class Tracers extends ToggleableModule {
    private final NumberProperty<Float> width = new NumberProperty<Float>(1f, 0.5f, 64f, "Width");

    public Tracers() {
        super("Tracers", new String[] {"tracers", "Tracers"}, "Draws lines to entities", ModuleType.RENDER);
        this.offerProperties(this.width, this.keybind);
    }

    @Override
    public void render(RenderWorldLastEvent event) {
        for (Entity entity : Hecks.mc.world.loadedEntityList) {
            RenderUtil.drawTracers(entity, this.width.getValue());
        }
    }
}
