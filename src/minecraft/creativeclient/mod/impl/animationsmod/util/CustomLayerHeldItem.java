package creativeclient.mod.impl.animationsmod.util;

import net.minecraft.client.renderer.entity.layers.*;
import net.minecraft.entity.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.model.*;
import net.minecraft.entity.player.*;
import net.minecraft.init.*;
import net.minecraft.client.*;
import net.minecraft.item.*;
import net.minecraft.block.*;
import net.minecraft.client.renderer.block.model.*;


public class CustomLayerHeldItem implements LayerRenderer<EntityLivingBase>
{
    private RendererLivingEntity<?> livingEntityRenderer;
    
    public void setRenderer(final RendererLivingEntity<?> renderer) {
        this.livingEntityRenderer = renderer;
    }
    
    public void func_177141_a(final EntityLivingBase entitylivingbaseIn, final float p_177141_2_, final float p_177141_3_, final float partialTicks, final float p_177141_5_, final float p_177141_6_, final float p_177141_7_, final float scale) {
        ItemStack itemstack = entitylivingbaseIn.getHeldItem();
        if (itemstack != null) {
            GlStateManager.pushMatrix();
            final ModelBiped model = (ModelBiped)this.livingEntityRenderer.getMainModel();
            final boolean isBlocking = model.heldItemRight == 3;
            if (model.isChild) {
                final float f = 0.5f;
                GlStateManager.translate(0.0f, 0.625f, 0.0f);
                GlStateManager.rotate(-20.0f, -1.0f, 0.0f, 0.0f);
                GlStateManager.scale(f, f, f);
            }
            model.postRenderArm(0.0625f);
            GlStateManager.translate(-0.0625f, 0.4375f, 0.0625f);
            if (entitylivingbaseIn instanceof EntityPlayer && ((EntityPlayer)entitylivingbaseIn).fishEntity != null) {
                itemstack = new ItemStack((Item)Items.fishing_rod, 0);
            }
            final Item item = itemstack.getItem();
            final Minecraft minecraft = Minecraft.getMinecraft();
            if (item instanceof ItemBlock && Block.getBlockFromItem(item).getRenderType() == 2) {
                GlStateManager.translate(0.0f, 0.1875f, -0.3125f);
                GlStateManager.rotate(20.0f, 1.0f, 0.0f, 0.0f);
                GlStateManager.rotate(45.0f, 0.0f, 1.0f, 0.0f);
                final float f2 = 0.375f;
                GlStateManager.scale(-f2, -f2, f2);
            }
            if (entitylivingbaseIn.isSneaking()) {
                GlStateManager.translate(0.0f, 0.203125f, 0.0f);
            }
            if (isBlocking) {
                GlStateManager.rotate(-45.0f, 1.0f, 1.0f, 1.0f);
                GlStateManager.rotate(20.0f, 1.0f, 1.0f, 0.0f);
                GlStateManager.rotate(-20.0f, 0.0f, 1.0f, 0.0f);
                GlStateManager.rotate(-30.0f, 0.0f, 0.0f, 1.0f);
            }
            minecraft.getItemRenderer().renderItem(entitylivingbaseIn, itemstack, ItemCameraTransforms.TransformType.THIRD_PERSON);
            GlStateManager.popMatrix();
        }
    }
    
    public boolean func_177142_b() {
        return false;
    }

	@Override
	public void doRenderLayer(EntityLivingBase entitylivingbaseIn, float p_177141_2_, float p_177141_3_,
			float partialTicks, float p_177141_5_, float p_177141_6_, float p_177141_7_, float scale) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean shouldCombineTextures() {
		// TODO Auto-generated method stub
		return false;
	}
}
