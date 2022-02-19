package me.frogdog.frogclient.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiSubtitleOverlay;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.frogdog.frogclient.Frog;
import me.frogdog.frogclient.events.RenderGameOverlayEvent;

@Mixin(GuiSubtitleOverlay.class)
public abstract class MixinGuiSubtitleOverlay {

    @Inject(
        method = "renderSubtitles",
        at = @At(value = "HEAD"))
    private void renderSubtitlesHook(CallbackInfo info) {

        ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());

        RenderGameOverlayEvent overlayEvent
                = new RenderGameOverlayEvent(scaledResolution);

        Frog.getInstance().getEventManager()
                .dispatch(overlayEvent);

        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
    }

}