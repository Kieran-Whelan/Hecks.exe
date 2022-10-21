package me.frogdog.hecks.mixin.mixins;

import net.minecraft.client.renderer.entity.RenderEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;

@Mixin(value = RenderEntity.class)
public class MixinEntityRenderer {

}
