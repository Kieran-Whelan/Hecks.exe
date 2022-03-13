package me.frogdog.frogclient.module.modules.render;

import org.lwjgl.opengl.GL11;

import me.frogdog.frogclient.Frog;
import me.frogdog.frogclient.event.Listener;
import me.frogdog.frogclient.event.events.RenderEvent;
import me.frogdog.frogclient.module.ModuleType;
import me.frogdog.frogclient.module.ToggleableModule;
import me.frogdog.frogclient.properties.NumberProperty;
import me.frogdog.frogclient.properties.Property;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.init.Blocks;
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
import net.minecraftforge.common.util.BlockSnapshot;

public final class OreESP extends ToggleableModule{
	private final Property<Boolean> diamond = new Property<Boolean>(false, "Diamond", "c");
	private final Property<Boolean> iron = new Property<Boolean>(false, "Iron", "ec");
	private final Property<Boolean> gold = new Property<Boolean>(false, "Gold", "f");
	private final Property<Boolean> emerald = new Property<Boolean>(false, "Emerald", "d");
	private final Property<Boolean> restone = new Property<Boolean>(false, "Redstone", "d");
	private final Property<Boolean> coal = new Property<Boolean>(false, "Coal", "h");
	private final Property<Boolean> shulker = new Property<Boolean>(false, "Shulker", "s");
	private final NumberProperty<Float> width = new NumberProperty<Float>(1f, 0.5f, 64f, "Width");

	public OreESP() {
		super("OreESP", new String[] {"oreesp", "OreESP"}, ModuleType.RENDER);
		this.offerProperties(this.diamond, this.iron, this.gold, this.emerald, this.restone, this.coal, this.shulker, this.width, this.keybind);
        this.listeners.add(new Listener<RenderEvent>("render_listener"){

            @Override
            public void call(RenderEvent event) {
            	for(BlockSnapshot block : Frog.getInstance().mc.world.capturedBlockSnapshots) {
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
