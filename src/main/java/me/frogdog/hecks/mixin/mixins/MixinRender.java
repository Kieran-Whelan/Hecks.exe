package me.frogdog.hecks.mixin.mixins;

import me.frogdog.hecks.Hecks;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(value = Render.class)
public class MixinRender<T extends Entity> {

    @Redirect(method = "renderOffsetAABB", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;color(FFFF)V"))
    private static void injected(float colorRed, float colorGreen, float colorBlue, float colorAlpha) {
        GlStateManager.color(1.0f, 0.0f, 0.0f, 1.0f);
    }

}
