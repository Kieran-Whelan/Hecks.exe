package me.frogdog.frogclient.module.modules.render.clickgui;

import java.util.ArrayList;

import me.frogdog.frogclient.Frog;
import me.frogdog.frogclient.module.Module;
import me.frogdog.frogclient.module.ModuleType;
import me.frogdog.frogclient.module.ToggleableModule;
import me.frogdog.frogclient.module.modules.render.clickgui.item.ModuleButton;
import me.frogdog.frogclient.util.interfaces.Toggleable;
import me.frogdog.frogclient.util.minecraft.render.RenderMethods;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;

public final class ClickGui extends GuiScreen {
    private static ClickGui clickGui;
    private final ArrayList<Panel> panels = new ArrayList();

    public ClickGui() {
        if (this.getPanels().isEmpty()) {
            this.load();
        }
    }

    public static ClickGui getClickGui() {
        return clickGui == null ? (clickGui = new ClickGui()) : clickGui;
    }

    private void load() {
        int x = -72;
        for (final ModuleType moduleType : ModuleType.values()) {
            this.panels.add(new Panel(moduleType.getLabel(), x += 72, 4, true){

                @Override
                public void setupItems() {
                    Frog.getInstance().getModuleManager().getRegistry().forEach(module -> {
                        ToggleableModule toggleableModule;
                        if (module instanceof Toggleable && !module.getLabel().equalsIgnoreCase("Tab Gui") && !module.getLabel().equalsIgnoreCase("Click Gui") && (toggleableModule = (ToggleableModule)module).getModuleType().equals((Object)moduleType)) {
                            this.addButton(new ModuleButton(toggleableModule));
                        }
                    });
                }
            });
        }
        this.panels.add(new Panel("Client", x += 72, 4, true){

            @Override
            public void setupItems() {
                Frog.getInstance().getModuleManager().getRegistry().forEach(module -> {
                    if (!(module instanceof Toggleable || module.getLabel().equalsIgnoreCase("Tab Gui") || module.getLabel().equalsIgnoreCase("Click Gui"))) {
                        this.addButton(new ModuleButton((Module)module));
                    }
                });
            }
        });
        this.panels.forEach(panel -> panel.getItems().sort((item1, item2) -> item1.getLabel().compareTo(item2.getLabel())));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        RenderMethods.drawGradientRect(0.0F, 0.0F, mc.displayWidth, mc.displayHeight, 536870912, -1879048192);
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
        this.panels.forEach(panel -> panel.drawScreen(mouseX, mouseY, partialTicks));
    }

//    @Override
//    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
//        this.drawDefaultBackground();
//        this.panels.forEach(panel -> panel.drawScreen(mouseX, mouseY, partialTicks));
//    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int clickedButton) {
        this.panels.forEach(panel -> panel.mouseClicked(mouseX, mouseY, clickedButton));
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int releaseButton) {
        this.panels.forEach(panel -> panel.mouseReleased(mouseX, mouseY, releaseButton));
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    public final ArrayList<Panel> getPanels() {
        return this.panels;
    }
}

