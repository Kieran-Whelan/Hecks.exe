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
    private int box = 0;

    private final NumberProperty<Float> width = new NumberProperty<Float>(1f, 0.5f, 64f, "Width");

    public ESP() {
        super("ESP", new String[] {"esp", "ESP"}, "Allows you to see entities through walls", ModuleType.RENDER);
        this.offerProperties(this.width, this.keybind);
    }

    @Override
    public void onEnable() {
        box = GL11.glGenLists(1);
        GL11.glNewList(box, GL11.GL_COMPILE);
        RenderUtil.drawOutlinedBox(new AxisAlignedBB(-0.5, 0, -0.5, 0.5, 1, 0.5));
        GL11.glEndList();
    }

    @Override
    public void onDisable() {
        GL11.glDeleteLists(box, 1);
    }

    @Override
    public void render(RenderWorldLastEvent event) {
        for (Entity entity : mc.world.getLoadedEntityList()) {
            if (entity instanceof EntityPlayer && entity != mc.player) {
                RenderUtil.drawESPBoxes(entity, box, event.getPartialTicks());
            }

            if (entity instanceof EntityMob) {
                RenderUtil.drawESPBoxes(entity, box, event.getPartialTicks());
            }

            if (entity instanceof EntityAnimal) {
                RenderUtil.drawESPBoxes(entity, box, event.getPartialTicks());
            }
        }
    }

}
