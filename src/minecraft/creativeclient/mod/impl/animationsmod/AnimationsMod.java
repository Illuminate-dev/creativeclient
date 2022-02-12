package creativeclient.mod.impl.animationsmod;

import creativeclient.event.impl.RenderLivingEvent;
import creativeclient.event.impl.RenderPlayerEvent;
import creativeclient.mod.Category;
import creativeclient.mod.Mod;
import creativeclient.mod.impl.animationsmod.util.CustomLayerBipedArmor;
import creativeclient.mod.impl.animationsmod.util.CustomLayerHeldItem;
import creativeclient.mod.impl.animationsmod.util.FieldWrapper;
import creativeclient.mod.impl.animationsmod.util.ModelMethods;
import net.minecraft.client.renderer.texture.*;
import org.apache.logging.log4j.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.entity.*;
import net.minecraft.client.renderer.entity.layers.*;
import java.util.*;
import net.minecraft.entity.player.*;
import net.minecraft.scoreboard.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.*;
import net.minecraft.client.model.*;
import net.minecraft.util.*;
import net.minecraft.client.entity.*;
import net.minecraft.client.renderer.*;
import org.lwjgl.opengl.*;
import java.nio.*;


public class AnimationsMod extends Mod {
	private static final DynamicTexture field_177096_e;
    
    private float partialTicks;
    private CustomLayerBipedArmor layerBipedArmor;
    private CustomLayerHeldItem layerHeldItem;
    private static final FieldWrapper<List<?>> layerRenderers;

	public AnimationsMod() {
		super("1.7 Animations", "Changes animations to 1.7", Category.MISC);
		
        this.partialTicks = 0.0f;
        this.layerBipedArmor = new CustomLayerBipedArmor(null);
        this.layerHeldItem = new CustomLayerHeldItem();
        AnimationsMod.field_177096_e.updateDynamicTexture();
	}
    
    public void onRenderPlayer(final RenderPlayerEvent e) {
    	
        this.partialTicks = e.partialRenderTick;
    }

    
    public void onRenderLiving(final RenderLivingEvent e) {
        if (!(e.entity instanceof EntityPlayer)) {
            return;
        }
        if (e.entity.isPlayerSleeping()) {
            return;
        }
        if(!this.isEnabled()) {
        	return;
        }
        e.setCancelled(true);
        final ModelBase mainModel = e.renderer.getMainModel();
        final List<LayerRenderer> layerRenderers = (List<LayerRenderer>) AnimationsMod.layerRenderers.get(e.renderer);
        final EntityLivingBase p_177093_1_ = e.entity;
        GlStateManager.pushMatrix();
        GlStateManager.disableCull();
        mainModel.swingProgress = this.getSwingProgress(e.entity, this.partialTicks);
        mainModel.isRiding = e.entity.isRiding();
        mainModel.isChild = e.entity.isChild();
        try {
            float f2 = this.interpolateRotation(e.entity.prevRenderYawOffset, e.entity.renderYawOffset, this.partialTicks);
            final float f3 = this.interpolateRotation(e.entity.prevRotationYawHead, e.entity.rotationYawHead, this.partialTicks);
            float f4 = f3 - f2;
            if (e.entity.isRiding() && e.entity.ridingEntity instanceof EntityLivingBase) {
                final EntityLivingBase entitylivingbase1 = (EntityLivingBase)e.entity.ridingEntity;
                f2 = this.interpolateRotation(entitylivingbase1.prevRenderYawOffset, entitylivingbase1.renderYawOffset, this.partialTicks);
                f4 = f3 - f2;
                float f5 = MathHelper.wrapAngleTo180_float(f4);
                if (f5 < -85.0f) {
                    f5 = -85.0f;
                }
                if (f5 >= 85.0f) {
                    f5 = 85.0f;
                }
                f2 = f3 - f5;
                if (f5 * f5 > 2500.0f) {
                    f2 += f5 * 0.2f;
                }
            }
            final float f6 = e.entity.prevRotationPitch + (e.entity.rotationPitch - e.entity.prevRotationPitch) * this.partialTicks;
            this.renderLivingAt(e.entity, e.x, e.y, e.z);
            float f5 = this.handleRotationFloat(e.entity, this.partialTicks);
            this.rotateCorpse(e.entity, f5, f2, this.partialTicks);
            GlStateManager.enableRescaleNormal();
            GlStateManager.scale(-1.0f, -1.0f, 1.0f);
            this.preRenderCallback(e.entity, this.partialTicks);
            GlStateManager.translate(0.0f, -1.5078125f, 0.0f);
            float f7 = e.entity.prevLimbSwingAmount + (e.entity.limbSwingAmount - e.entity.prevLimbSwingAmount) * this.partialTicks;
            float f8 = e.entity.limbSwing - e.entity.limbSwingAmount * (1.0f - this.partialTicks);
            if (e.entity.isChild()) {
                f8 *= 3.0f;
            }
            if (f7 > 1.0f) {
                f7 = 1.0f;
            }
            GlStateManager.enableAlpha();
            mainModel.setLivingAnimations(e.entity, f8, f7, this.partialTicks);
            mainModel.setRotationAngles(f8, f7, f5, f4, f6, 0.0625f, (Entity)e.entity);
            final boolean flag = this.func_177090_c(e.entity, this.partialTicks);
            this.renderModel(e.entity, f8, f7, f5, f4, f6, 0.0625f, mainModel, (RendererLivingEntity<?>)e.renderer);
            if (flag) {
                this.func_177091_f();
            }
            GlStateManager.depthMask(true);
            if (!(e.entity instanceof EntityPlayer) || !((EntityPlayer)e.entity).isSpectator()) {
                for (final LayerRenderer layerrenderer : layerRenderers) {
                    final boolean redarmor = true && (layerrenderer.shouldCombineTextures() || layerrenderer.toString().contains("LayerBipedArmor") || layerrenderer.toString().startsWith("bkx@"));
                    final boolean flag_2 = this.func_177092_a(p_177093_1_, this.partialTicks, redarmor);
                    if (false) {
                        if (layerrenderer instanceof LayerBipedArmor && !(layerrenderer instanceof LayerVillagerArmor)) {
                            this.layerBipedArmor.setRenderer((RendererLivingEntity<?>)e.renderer);
                            this.layerBipedArmor.doRenderLayer(e.entity, f8, f7, this.partialTicks, f5, f4, f6, 0.0625f);
                        }
                        else if (layerrenderer instanceof LayerHeldItem) {
                            this.layerHeldItem.setRenderer((RendererLivingEntity<?>)e.renderer);
                            this.layerHeldItem.doRenderLayer(e.entity, f8, f7, this.partialTicks, f5, f4, f6, 0.0625f);
                        }
                        else {
                            layerrenderer.doRenderLayer(e.entity, f8, f7, this.partialTicks, f5, f4, f6, 0.0625f);
                        }
                    }
                    else {
                        layerrenderer.doRenderLayer(e.entity, f8, f7, this.partialTicks, f5, f4, f6, 0.0625f);
                    }
                    if (flag_2) {
                        this.func_177091_f();
                    }
                }
            }
            GlStateManager.disableRescaleNormal();
        }
        catch (NullPointerException exception) {
        	exception.printStackTrace();
            System.out.println("Couldn't render e.entity");
        }
        GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GlStateManager.enableTexture2D();
        GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
        GlStateManager.enableCull();
        GlStateManager.popMatrix();
        e.renderer.renderName(e.entity, e.x, e.y, e.z);
    }
    
    private float getSwingProgress(final EntityLivingBase p_77040_1_, final float p_77040_2_) {
        return p_77040_1_.getSwingProgress(p_77040_2_);
    }
    
    protected float interpolateRotation(final float p_77034_1_, final float p_77034_2_, final float p_77034_3_) {
        float f3;
        for (f3 = p_77034_2_ - p_77034_1_; f3 < -180.0f; f3 += 360.0f) {}
        while (f3 >= 180.0f) {
            f3 -= 360.0f;
        }
        return p_77034_1_ + p_77034_3_ * f3;
    }
    
    protected void renderLivingAt(final EntityLivingBase p_77039_1_, final double p_77039_2_, final double p_77039_4_, final double p_77039_6_) {
        GlStateManager.translate((float)p_77039_2_, (float)p_77039_4_, (float)p_77039_6_);
    }
    
    protected float handleRotationFloat(final EntityLivingBase p_77044_1_, final float p_77044_2_) {
        return p_77044_1_.ticksExisted + p_77044_2_;
    }
    
    protected void rotateCorpse(final EntityLivingBase p_77043_1_, final float p_77043_2_, final float p_77043_3_, final float p_77043_4_) {
        GlStateManager.rotate(180.0f - p_77043_3_, 0.0f, 1.0f, 0.0f);
        if (p_77043_1_.deathTime > 0) {
            float f3 = (p_77043_1_.deathTime + p_77043_4_ - 1.0f) / 20.0f * 1.6f;
            f3 = MathHelper.sqrt_float(f3);
            if (f3 > 1.0f) {
                f3 = 1.0f;
            }
            GlStateManager.rotate(f3 * this.getDeathMaxRotation(p_77043_1_), 0.0f, 0.0f, 1.0f);
        }
        else {
            final String s = EnumChatFormatting.getTextWithoutFormattingCodes(p_77043_1_.getName());
            if (s != null && (s.equals("Dinnerbone") || s.equals("Grumm")) && !(p_77043_1_ instanceof EntityPlayer) && (!(p_77043_1_ instanceof EntityPlayer) || ((EntityPlayer)p_77043_1_).isWearing(EnumPlayerModelParts.CAPE))) {
                GlStateManager.translate(0.0f, p_77043_1_.height + 0.1f, 0.0f);
                GlStateManager.rotate(180.0f, 0.0f, 0.0f, 1.0f);
            }
        }
    }
    
    protected float getDeathMaxRotation(final EntityLivingBase p_77037_1_) {
        return 90.0f;
    }
    
    protected void preRenderCallback(final EntityLivingBase p_77041_1_, final float p_77041_2_) {
    }
    
    protected boolean func_177088_c(final EntityLivingBase p_177088_1_, final RendererLivingEntity<?> renderer) {
        int i = 16777215;
        if (p_177088_1_ instanceof EntityPlayer) {
            final ScorePlayerTeam scoreplayerteam = (ScorePlayerTeam)p_177088_1_.getTeam();
            if (scoreplayerteam != null) {
                final String s = FontRenderer.getFormatFromString(scoreplayerteam.getColorPrefix());
                if (s.length() >= 2) {
                    i = renderer.getFontRendererFromRenderManager().getColorCode(s.charAt(1));
                }
            }
        }
        final float f1 = (i >> 16 & 0xFF) / 255.0f;
        final float f2 = (i >> 8 & 0xFF) / 255.0f;
        final float f3 = (i & 0xFF) / 255.0f;
        GlStateManager.disableLighting();
        GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
        GlStateManager.color(f1, f2, f3, 1.0f);
        GlStateManager.disableTexture2D();
        GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GlStateManager.disableTexture2D();
        GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
        return true;
    }
    
    protected void renderModel(final EntityLivingBase entityIn, final float p_77036_2_, final float p_77036_3_, final float p_77036_4_, final float p_77036_5_, final float p_77036_6_, final float scale, final ModelBase mainModel, final RendererLivingEntity<?> renderer) {
        final boolean flag = !entityIn.isInvisible();
        final boolean flag2 = !flag && !entityIn.isInvisibleToPlayer((EntityPlayer)Minecraft.getMinecraft().thePlayer);
        if (flag || flag2) {
            if (!this.bindEntityTexture((Entity)entityIn, renderer)) {
                return;
            }
            if (flag2) {
                GlStateManager.pushMatrix();
                GlStateManager.color(1.0f, 1.0f, 1.0f, 0.15f);
                GlStateManager.depthMask(false);
                GlStateManager.enableBlend();
                GlStateManager.blendFunc(770, 771);
                GlStateManager.alphaFunc(516, 0.003921569f);
            }
            if (true) {
                if (mainModel instanceof ModelPlayer) {
                    ModelMethods.setRotationAnglesModelPlayer((ModelPlayer)mainModel, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, scale, (Entity)entityIn);
                    ModelMethods.renderModelPlayer((ModelPlayer)mainModel, (Entity)entityIn, scale);
                }
                else if (mainModel instanceof ModelBiped) {
                    ModelMethods.setRotationAnglesModelBiped((ModelBiped)mainModel, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, scale, (Entity)entityIn);
                    ModelMethods.renderModelBiped((ModelBiped)mainModel, (Entity)entityIn, scale);
                }
                else {
                    mainModel.render((Entity)entityIn, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, scale);
                }
            }
            else {
                mainModel.render((Entity)entityIn, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, scale);
            }
            if (flag2) {
                GlStateManager.disableBlend();
                GlStateManager.alphaFunc(516, 0.1f);
                GlStateManager.popMatrix();
                GlStateManager.depthMask(true);
            }
        }
    }
    
    protected boolean bindEntityTexture(final Entity entity, final RendererLivingEntity<?> renderer) {
        final ResourceLocation resourcelocation = this.getEntityTexture(entity);
        if (resourcelocation == null) {
            return false;
        }
        renderer.bindTexture(resourcelocation);
        return true;
    }
    
    protected ResourceLocation getEntityTexture(final Entity entity) {
        return this.func_180594_a((AbstractClientPlayer)entity);
    }
    
    protected ResourceLocation func_180594_a(final AbstractClientPlayer p_180594_1_) {
        return p_180594_1_.getLocationSkin();
    }
    
    protected void func_180565_e() {
        GlStateManager.enableLighting();
        GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
        GlStateManager.enableTexture2D();
        GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GlStateManager.enableTexture2D();
        GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
    }
    
    protected boolean func_177090_c(final EntityLivingBase p_177090_1_, final float p_177090_2_) {
        return this.func_177092_a(p_177090_1_, p_177090_2_, true);
    }
    
    protected boolean func_177092_a(final EntityLivingBase p_177092_1_, final float p_177092_2_, final boolean p_177092_3_) {
        final float f1 = p_177092_1_.getBrightness(p_177092_2_);
        final int i = this.getColorMultiplier(p_177092_1_, f1, p_177092_2_);
        final boolean flag1 = (i >> 24 & 0xFF) > 0;
        final boolean flag2 = p_177092_1_.hurtTime > 0 || p_177092_1_.deathTime > 0;
        if (!flag1 && !flag2) {
            return false;
        }
        if (!flag1 && !p_177092_3_) {
            return false;
        }
        final FloatBuffer field_177095_g = GLAllocation.createDirectFloatBuffer(4);
        GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
        GlStateManager.enableTexture2D();
        GL11.glTexEnvi(8960, 8704, OpenGlHelper.GL_COMBINE);
        GL11.glTexEnvi(8960, OpenGlHelper.GL_COMBINE_RGB, 8448);
        GL11.glTexEnvi(8960, OpenGlHelper.GL_SOURCE0_RGB, OpenGlHelper.defaultTexUnit);
        GL11.glTexEnvi(8960, OpenGlHelper.GL_SOURCE1_RGB, OpenGlHelper.GL_PRIMARY_COLOR);
        GL11.glTexEnvi(8960, OpenGlHelper.GL_OPERAND0_RGB, 768);
        GL11.glTexEnvi(8960, OpenGlHelper.GL_OPERAND1_RGB, 768);
        GL11.glTexEnvi(8960, OpenGlHelper.GL_COMBINE_ALPHA, 7681);
        GL11.glTexEnvi(8960, OpenGlHelper.GL_SOURCE0_ALPHA, OpenGlHelper.defaultTexUnit);
        GL11.glTexEnvi(8960, OpenGlHelper.GL_OPERAND0_ALPHA, 770);
        GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GlStateManager.enableTexture2D();
        GL11.glTexEnvi(8960, 8704, OpenGlHelper.GL_COMBINE);
        GL11.glTexEnvi(8960, OpenGlHelper.GL_COMBINE_RGB, OpenGlHelper.GL_INTERPOLATE);
        GL11.glTexEnvi(8960, OpenGlHelper.GL_SOURCE0_RGB, OpenGlHelper.GL_CONSTANT);
        GL11.glTexEnvi(8960, OpenGlHelper.GL_SOURCE1_RGB, OpenGlHelper.GL_PREVIOUS);
        GL11.glTexEnvi(8960, OpenGlHelper.GL_SOURCE2_RGB, OpenGlHelper.GL_CONSTANT);
        GL11.glTexEnvi(8960, OpenGlHelper.GL_OPERAND0_RGB, 768);
        GL11.glTexEnvi(8960, OpenGlHelper.GL_OPERAND1_RGB, 768);
        GL11.glTexEnvi(8960, OpenGlHelper.GL_OPERAND2_RGB, 770);
        GL11.glTexEnvi(8960, OpenGlHelper.GL_COMBINE_ALPHA, 7681);
        GL11.glTexEnvi(8960, OpenGlHelper.GL_SOURCE0_ALPHA, OpenGlHelper.GL_PREVIOUS);
        GL11.glTexEnvi(8960, OpenGlHelper.GL_OPERAND0_ALPHA, 770);
        field_177095_g.position(0);
        if (flag2) {
            field_177095_g.put(1.0f);
            field_177095_g.put(0.0f);
            field_177095_g.put(0.0f);
            field_177095_g.put(false ? 0.5f : 0.3f);
        }
        else {
            final float f2 = (i >> 24 & 0xFF) / 255.0f;
            final float f3 = (i >> 16 & 0xFF) / 255.0f;
            final float f4 = (i >> 8 & 0xFF) / 255.0f;
            final float f5 = (i & 0xFF) / 255.0f;
            field_177095_g.put(f3);
            field_177095_g.put(f4);
            field_177095_g.put(f5);
            field_177095_g.put(1.0f - f2);
        }
        field_177095_g.flip();
        GL11.glTexEnv(8960, 8705, field_177095_g);
        GlStateManager.setActiveTexture(OpenGlHelper.GL_TEXTURE2);
        GlStateManager.enableTexture2D();
        GlStateManager.bindTexture(AnimationsMod.field_177096_e.getGlTextureId());
        GL11.glTexEnvi(8960, 8704, OpenGlHelper.GL_COMBINE);
        GL11.glTexEnvi(8960, OpenGlHelper.GL_COMBINE_RGB, 8448);
        GL11.glTexEnvi(8960, OpenGlHelper.GL_SOURCE0_RGB, OpenGlHelper.GL_PREVIOUS);
        GL11.glTexEnvi(8960, OpenGlHelper.GL_SOURCE1_RGB, OpenGlHelper.lightmapTexUnit);
        GL11.glTexEnvi(8960, OpenGlHelper.GL_OPERAND0_RGB, 768);
        GL11.glTexEnvi(8960, OpenGlHelper.GL_OPERAND1_RGB, 768);
        GL11.glTexEnvi(8960, OpenGlHelper.GL_COMBINE_ALPHA, 7681);
        GL11.glTexEnvi(8960, OpenGlHelper.GL_SOURCE0_ALPHA, OpenGlHelper.GL_PREVIOUS);
        GL11.glTexEnvi(8960, OpenGlHelper.GL_OPERAND0_ALPHA, 770);
        GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
        return true;
    }
    
    protected int getColorMultiplier(final EntityLivingBase p_77030_1_, final float p_77030_2_, final float p_77030_3_) {
        return 0;
    }
    
    protected void func_177091_f() {
        GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
        GlStateManager.enableTexture2D();
        GL11.glTexEnvi(8960, 8704, OpenGlHelper.GL_COMBINE);
        GL11.glTexEnvi(8960, OpenGlHelper.GL_COMBINE_RGB, 8448);
        GL11.glTexEnvi(8960, OpenGlHelper.GL_SOURCE0_RGB, OpenGlHelper.defaultTexUnit);
        GL11.glTexEnvi(8960, OpenGlHelper.GL_SOURCE1_RGB, OpenGlHelper.GL_PRIMARY_COLOR);
        GL11.glTexEnvi(8960, OpenGlHelper.GL_OPERAND0_RGB, 768);
        GL11.glTexEnvi(8960, OpenGlHelper.GL_OPERAND1_RGB, 768);
        GL11.glTexEnvi(8960, OpenGlHelper.GL_COMBINE_ALPHA, 8448);
        GL11.glTexEnvi(8960, OpenGlHelper.GL_SOURCE0_ALPHA, OpenGlHelper.defaultTexUnit);
        GL11.glTexEnvi(8960, OpenGlHelper.GL_SOURCE1_ALPHA, OpenGlHelper.GL_PRIMARY_COLOR);
        GL11.glTexEnvi(8960, OpenGlHelper.GL_OPERAND0_ALPHA, 770);
        GL11.glTexEnvi(8960, OpenGlHelper.GL_OPERAND1_ALPHA, 770);
        GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GL11.glTexEnvi(8960, 8704, OpenGlHelper.GL_COMBINE);
        GL11.glTexEnvi(8960, OpenGlHelper.GL_COMBINE_RGB, 8448);
        GL11.glTexEnvi(8960, OpenGlHelper.GL_OPERAND0_RGB, 768);
        GL11.glTexEnvi(8960, OpenGlHelper.GL_OPERAND1_RGB, 768);
        GL11.glTexEnvi(8960, OpenGlHelper.GL_SOURCE0_RGB, 5890);
		GL11.glTexEnvi(8960, OpenGlHelper.GL_SOURCE1_RGB, OpenGlHelper.GL_PREVIOUS);
        GL11.glTexEnvi(8960, OpenGlHelper.GL_COMBINE_ALPHA, 8448);
        GL11.glTexEnvi(8960, OpenGlHelper.GL_OPERAND0_ALPHA, 770);
        GL11.glTexEnvi(8960, OpenGlHelper.GL_SOURCE0_ALPHA, 5890);
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        GlStateManager.setActiveTexture(OpenGlHelper.GL_TEXTURE2);
        GlStateManager.disableTexture2D();
        GlStateManager.bindTexture(0);
        GL11.glTexEnvi(8960, 8704, OpenGlHelper.GL_COMBINE);
        GL11.glTexEnvi(8960, OpenGlHelper.GL_COMBINE_RGB, 8448);
        GL11.glTexEnvi(8960, OpenGlHelper.GL_OPERAND0_RGB, 768);
        GL11.glTexEnvi(8960, OpenGlHelper.GL_OPERAND1_RGB, 768);
        GL11.glTexEnvi(8960, OpenGlHelper.GL_SOURCE0_RGB, 5890);
        GL11.glTexEnvi(8960, OpenGlHelper.GL_SOURCE1_RGB, OpenGlHelper.GL_PREVIOUS);
        GL11.glTexEnvi(8960, OpenGlHelper.GL_COMBINE_ALPHA, 8448);
        GL11.glTexEnvi(8960, OpenGlHelper.GL_OPERAND0_ALPHA, 770);
        GL11.glTexEnvi(8960, OpenGlHelper.GL_SOURCE0_ALPHA, 5890);
        GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
    }
    
    static {
        field_177096_e = new DynamicTexture(16, 16);
        layerRenderers = new FieldWrapper<List<?>>(isObfuscated() ? "field_177097_h" : "layerRenderers", RendererLivingEntity.class);
    }
    private static boolean isObfuscated() {
        try {
            Minecraft.class.getDeclaredField("logger");
            return false;
        }
        catch (NoSuchFieldException ex) {}
        catch (SecurityException e1) {
            e1.printStackTrace();
        }
        return true;
    }
    
    
}

