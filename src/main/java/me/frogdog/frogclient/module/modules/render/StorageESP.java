package me.frogdog.frogclient.module.modules.render;

import org.lwjgl.opengl.GL11;

import me.frogdog.frogclient.event.Listener;
import me.frogdog.frogclient.event.events.RenderEvent;
import me.frogdog.frogclient.Frog;
import me.frogdog.frogclient.module.ModuleType;
import me.frogdog.frogclient.module.ToggleableModule;
import me.frogdog.frogclient.properties.NumberProperty;
import me.frogdog.frogclient.properties.Property;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.tileentity.TileEntityDropper;
import net.minecraft.tileentity.TileEntityEnderChest;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.tileentity.TileEntityShulkerBox;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public final class StorageESP extends ToggleableModule {
	private final Property<Boolean> chest = new Property<Boolean>(false, "Chest", "c");
	private final Property<Boolean> enderChest = new Property<Boolean>(false, "EnderChest", "ec");
	private final Property<Boolean> furance = new Property<Boolean>(false, "Furnace", "f");
	private final Property<Boolean> dispenser = new Property<Boolean>(false, "Dispenser", "d");
	private final Property<Boolean> dropper = new Property<Boolean>(false, "Dropper", "d");
	private final Property<Boolean> hopper = new Property<Boolean>(false, "Hopper", "h");
	private final Property<Boolean> shulker = new Property<Boolean>(false, "Shulker", "s");
	private final NumberProperty<Float> width = new NumberProperty<Float>(1f, 0.5f, 64f, "Width");

	public StorageESP() {
		super("StorageESP", new String[] {"storageesp", "StorageESP"}, ModuleType.RENDER);
		this.offerProperties(this.chest, this.enderChest, this.furance, this.dispenser, this.dropper, this.hopper, this.shulker, this.width, this.keybind);
        this.listeners.add(new Listener<RenderEvent>("render_listener"){

            @Override
            public void call(RenderEvent event) {
            	for(TileEntity tile : Frog.getInstance().mc.world.loadedTileEntityList) {
            		if(tile instanceof TileEntityChest && StorageESP.this.chest.getValue() == true) {
            			drawBlockBox(tile.getPos(), width.getValue(), 235, 177, 52, 100); //orange
            		}
            		
            		if(tile instanceof TileEntityEnderChest && StorageESP.this.enderChest.getValue() == true) {
            			drawBlockBox(tile.getPos(), width.getValue(), 186, 52, 235, 100); //purple
            		} 
            		
            		if(tile instanceof TileEntityFurnace && StorageESP.this.furance.getValue() == true) {
            			drawBlockBox(tile.getPos(), width.getValue(), 98, 100, 102, 100); //grey
            		}
            		
            		if(tile instanceof TileEntityDispenser && StorageESP.this.dispenser.getValue() == true) {
            			drawBlockBox(tile.getPos(), width.getValue(), 161, 163, 166, 100); //light grey
            		}
            		
            		if(tile instanceof TileEntityDropper && StorageESP.this.dropper.getValue() == true) {
            			drawBlockBox(tile.getPos(), width.getValue(), 161, 163, 166, 100); //light grey
            		}
            		
             		if(tile instanceof TileEntityHopper && StorageESP.this.hopper.getValue() == true) {
            			drawBlockBox(tile.getPos(), width.getValue(), 161, 163, 166, 100); //light grey
            		}
             		
             		if(tile instanceof TileEntityShulkerBox && StorageESP.this.shulker.getValue() == true) {
            			drawBlockBox(tile.getPos(), width.getValue(), 245, 159, 208, 100); //salmon pink
            		}
            	}
            }
        });
		
	}
	
	public void drawBlockBox(BlockPos pos, float width, int red, int green, int blue, int alpha) {
		GlStateManager.pushMatrix();
		GlStateManager.enableBlend();
		GlStateManager.disableDepth();
		GlStateManager.tryBlendFuncSeparate(green, blue, alpha, alpha);
		GlStateManager.disableTexture2D();
		GlStateManager.depthMask(false);
		GL11.glEnable(2848);
		GL11.glHint(3154, 4354);
		GL11.glLineWidth(width);
		double x = (double)pos.getX() - Frog.getInstance().mc.getRenderManager().viewerPosX;
		double y = (double)pos.getY() - Frog.getInstance().mc.getRenderManager().viewerPosY;
		double z = (double)pos.getZ() - Frog.getInstance().mc.getRenderManager().viewerPosZ;
        AxisAlignedBB bb = new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 1.0);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos(bb.minX, bb.minY, bb.minZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.minY, bb.minZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.minY, bb.maxZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.minY, bb.maxZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.minY, bb.minZ).color(red, green, blue, alpha).endVertex();
        tessellator.draw();
        bufferbuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos(bb.minX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
        tessellator.draw();
        bufferbuilder.begin(1, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos(bb.minX, bb.minY, bb.minZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.minY, bb.minZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.minY, bb.maxZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.minY, bb.maxZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
        tessellator.draw();
        GL11.glDisable((int)2848);
        GlStateManager.depthMask((boolean)true);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
	}

}
