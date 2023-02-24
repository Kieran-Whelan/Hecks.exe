package me.frogdog.hecks.util.game;

import me.frogdog.hecks.Hecks;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector4f;

public class RenderUtil {

    public static void drawTracers(Entity entity, float width) {
        GL11.glPushAttrib(GL11.GL_ENABLE_BIT | GL11.GL_COLOR_BUFFER_BIT | GL11.GL_LINE_BIT | GL11.GL_CURRENT_BIT);

        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glLineWidth(2);

        GL11.glPushMatrix();
        GL11.glTranslated(-Hecks.mc.getRenderManager().renderPosX, -Hecks.mc.getRenderManager().renderPosY, -Hecks.mc.getRenderManager().renderPosZ);

        Vec3d start = new Vec3d(Hecks.mc.getRenderManager().viewerPosX, Hecks.mc.getRenderManager().viewerPosY + Hecks.mc.player.getEyeHeight(), Hecks.mc.getRenderManager().viewerPosZ).add(Hecks.mc.player.getLookVec());
        GL11.glLineWidth(width);
        GL11.glBegin(GL11.GL_LINES);

        Vec3d target = entity.getEntityBoundingBox().getCenter();

        if (entity instanceof EntityPlayer && Hecks.getInstance().getFriendManager().isFriend(entity.getName())) {
            GL11.glColor4f(0.9f, 0.2f, 1f, 0.5f);
        } else if (entity instanceof EntityItem) {
            GL11.glColor4f(0.5f, 0.5f, 1f, 0.5f);
        } else {
            float intensity = Hecks.mc.player.getDistance(entity) / 20f;
            GL11.glColor4f(2f - intensity, intensity, 0f, 0.5f);
        }

        GL11.glVertex3d(start.x, start.y, start.z);
        GL11.glVertex3d(target.x, target.y, target.z);
        GL11.glEnd();

        GL11.glPopMatrix();
        GL11.glPopAttrib();
    }

    public static void drawBlockOutline(BlockPos pos, int yOffset) {
        Vector4f rgba = new Vector4f(0, 255, 0, 100);
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.tryBlendFuncSeparate((int) rgba.getY(), (int) rgba.getZ(), (int) rgba.getW(), (int) rgba.getW());
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask(false);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glLineWidth(1);
        double x = (double)pos.getX() - Hecks.mc.getRenderManager().viewerPosX;
        double y = (double)pos.getY() - Hecks.mc.getRenderManager().viewerPosY;
        double z = (double)pos.getZ() - Hecks.mc.getRenderManager().viewerPosZ;
        AxisAlignedBB bb = new AxisAlignedBB(x, y + (double) yOffset, z, x + 1, y + (double) yOffset + 1, z + 1);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos(bb.minX, bb.minY, bb.minZ).color(rgba.getX(), rgba.getY(), rgba.getZ(), rgba.getW()).endVertex();
        bufferbuilder.pos(bb.maxX, bb.minY, bb.minZ).color(rgba.getX(), rgba.getY(), rgba.getZ(), rgba.getW()).endVertex();
        bufferbuilder.pos(bb.maxX, bb.minY, bb.maxZ).color(rgba.getX(), rgba.getY(), rgba.getZ(), rgba.getW()).endVertex();
        bufferbuilder.pos(bb.minX, bb.minY, bb.maxZ).color(rgba.getX(), rgba.getY(), rgba.getZ(), rgba.getW()).endVertex();
        bufferbuilder.pos(bb.minX, bb.minY, bb.minZ).color(rgba.getX(), rgba.getY(), rgba.getZ(), rgba.getW()).endVertex();
        tessellator.draw();
        bufferbuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos(bb.minX, bb.maxY, bb.minZ).color(rgba.getX(), rgba.getY(), rgba.getZ(), rgba.getW()).endVertex();
        bufferbuilder.pos(bb.maxX, bb.maxY, bb.minZ).color(rgba.getX(), rgba.getY(), rgba.getZ(), rgba.getW()).endVertex();
        bufferbuilder.pos(bb.maxX, bb.maxY, bb.maxZ).color(rgba.getX(), rgba.getY(), rgba.getZ(), rgba.getW()).endVertex();
        bufferbuilder.pos(bb.minX, bb.maxY, bb.maxZ).color(rgba.getX(), rgba.getY(), rgba.getZ(), rgba.getW()).endVertex();
        bufferbuilder.pos(bb.minX, bb.maxY, bb.minZ).color(rgba.getX(), rgba.getY(), rgba.getZ(), rgba.getW()).endVertex();
        tessellator.draw();
        bufferbuilder.begin(1, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos(bb.minX, bb.minY, bb.minZ).color(rgba.getX(), rgba.getY(), rgba.getZ(), rgba.getW()).endVertex();
        bufferbuilder.pos(bb.minX, bb.maxY, bb.minZ).color(rgba.getX(), rgba.getY(), rgba.getZ(), rgba.getW()).endVertex();
        bufferbuilder.pos(bb.maxX, bb.minY, bb.minZ).color(rgba.getX(), rgba.getY(), rgba.getZ(), rgba.getW()).endVertex();
        bufferbuilder.pos(bb.maxX, bb.maxY, bb.minZ).color(rgba.getX(), rgba.getY(), rgba.getZ(), rgba.getW()).endVertex();
        bufferbuilder.pos(bb.maxX, bb.minY, bb.maxZ).color(rgba.getX(), rgba.getY(), rgba.getZ(), rgba.getW()).endVertex();
        bufferbuilder.pos(bb.maxX, bb.maxY, bb.maxZ).color(rgba.getX(), rgba.getY(), rgba.getZ(), rgba.getW()).endVertex();
        bufferbuilder.pos(bb.minX, bb.minY, bb.maxZ).color(rgba.getX(), rgba.getY(), rgba.getZ(), rgba.getW()).endVertex();
        bufferbuilder.pos(bb.minX, bb.maxY, bb.maxZ).color(rgba.getX(), rgba.getY(), rgba.getZ(), rgba.getW()).endVertex();
        tessellator.draw();
        GL11.glDisable(2848);
        GlStateManager.depthMask(true);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }
}
