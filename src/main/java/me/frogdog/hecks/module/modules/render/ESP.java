package me.frogdog.hecks.module.modules.render;

import me.frogdog.hecks.module.ModuleType;
import me.frogdog.hecks.module.ToggleableModule;
import me.frogdog.hecks.property.NumberProperty;
import me.frogdog.hecks.util.game.RenderUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import org.lwjgl.opengl.GL11;

public final class ESP extends ToggleableModule {

    private final NumberProperty<Float> width = new NumberProperty<Float>(1f, 0.5f, 64f, "Width");

    public ESP() {
        super("ESP", new String[] {"esp", "ESP"}, "Allows you to see entities through walls", ModuleType.RENDER);
        this.offerProperties(this.width, this.keybind);
    }

    @Override
    public void render(RenderWorldLastEvent event) {
        mc.world.getLoadedEntityList().forEach(e -> {
            if (e instanceof EntityPlayer && e != mc.player) {
                e.setGlowing(true);
            }

            if (e instanceof EntityMob) {
                e.setGlowing(true);
            }

            if (e instanceof EntityAnimal) {
                e.setGlowing(true);
            }
        });
    }

    @Override
    public void onDisable() {
        mc.world.getLoadedEntityList().forEach(e -> {
            e.setGlowing(false);
        });
    }

}
