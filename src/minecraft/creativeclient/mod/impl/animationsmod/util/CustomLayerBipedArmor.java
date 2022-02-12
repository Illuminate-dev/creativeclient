package creativeclient.mod.impl.animationsmod.util;

import net.minecraft.client.renderer.entity.layers.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraftforge.client.*;
import net.minecraft.item.*;
import net.minecraft.entity.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.*;



public class CustomLayerBipedArmor extends LayerArmorBase<ModelBiped>
{
    private RendererLivingEntity<?> rendererIn;
    private float colorR;
    private float colorG;
    private float colorB;
    private float alpha;
    private static final FieldWrapper<RendererLivingEntity<?>> renderer;
    
    public CustomLayerBipedArmor(final RendererLivingEntity<?> rendererIn) {
        super((RendererLivingEntity)rendererIn);
        this.colorR = 1.0f;
        this.colorG = 1.0f;
        this.colorB = 1.0f;
        this.alpha = 1.0f;
        this.rendererIn = rendererIn;
    }
    
    public void setRenderer(final RendererLivingEntity<?> rendererIn) {
        CustomLayerBipedArmor.renderer.setFinal(this, rendererIn);
        this.rendererIn = rendererIn;
    }
    
    protected void func_177177_a() {
        this.field_177189_c = (ModelBase)new ModelBiped(0.5f);
        this.field_177186_d = (ModelBase)new ModelBiped(1.0f);
    }
    
    protected void func_177179_a(final ModelBiped p_177179_1_, final int p_177179_2_) {
        this.func_177194_a(p_177179_1_);
        switch (p_177179_2_) {
            case 1: {
                p_177179_1_.bipedRightLeg.showModel = true;
                p_177179_1_.bipedLeftLeg.showModel = true;
                break;
            }
            case 2: {
                p_177179_1_.bipedBody.showModel = true;
                p_177179_1_.bipedRightLeg.showModel = true;
                p_177179_1_.bipedLeftLeg.showModel = true;
                break;
            }
            case 3: {
                p_177179_1_.bipedBody.showModel = true;
                p_177179_1_.bipedRightArm.showModel = true;
                p_177179_1_.bipedLeftArm.showModel = true;
                break;
            }
            case 4: {
                p_177179_1_.bipedHead.showModel = true;
                p_177179_1_.bipedHeadwear.showModel = true;
                break;
            }
        }
    }
    
    protected void func_177194_a(final ModelBiped p_177194_1_) {
        p_177194_1_.setInvisible(false);
    }
    
    protected ModelBiped getArmorModelHook(final EntityLivingBase entity, final ItemStack itemStack, final int slot, final ModelBiped model) {
//        return ForgeHooksClient.getArmorModel(entity, itemStack, slot, model);
        return null;
    }
    
    public void func_177141_a(final EntityLivingBase entitylivingbaseIn, final float p_177141_2_, final float p_177141_3_, final float partialTicks, final float p_177141_5_, final float p_177141_6_, final float p_177141_7_, final float scale) {
        this.renderLayer(entitylivingbaseIn, p_177141_2_, p_177141_3_, partialTicks, p_177141_5_, p_177141_6_, p_177141_7_, scale, 4);
        this.renderLayer(entitylivingbaseIn, p_177141_2_, p_177141_3_, partialTicks, p_177141_5_, p_177141_6_, p_177141_7_, scale, 3);
        this.renderLayer(entitylivingbaseIn, p_177141_2_, p_177141_3_, partialTicks, p_177141_5_, p_177141_6_, p_177141_7_, scale, 2);
        this.renderLayer(entitylivingbaseIn, p_177141_2_, p_177141_3_, partialTicks, p_177141_5_, p_177141_6_, p_177141_7_, scale, 1);
    }
    
    private void renderLayer(final EntityLivingBase entitylivingbaseIn, final float p_177182_2_, final float p_177182_3_, final float p_177182_4_, final float p_177182_5_, final float p_177182_6_, final float p_177182_7_, final float p_177182_8_, final int armorSlot) {
        final ItemStack itemstack = this.getCurrentArmor(entitylivingbaseIn, armorSlot);
        if (itemstack != null && itemstack.getItem() instanceof ItemArmor) {
            final ItemArmor itemarmor = (ItemArmor)itemstack.getItem();
            ModelBiped model = (ModelBiped)this.func_177175_a(armorSlot);
            model.setModelAttributes(this.rendererIn.getMainModel());
            model.setLivingAnimations(entitylivingbaseIn, p_177182_2_, p_177182_3_, p_177182_4_);
            model = this.getArmorModelHook(entitylivingbaseIn, itemstack, armorSlot, model);
            this.func_177179_a(model, armorSlot);
            final boolean flag = this.isSlotForLeggings(armorSlot);
            this.rendererIn.bindTexture(this.getArmorResource((Entity)entitylivingbaseIn, itemstack, flag ? 2 : 1, (String)null));
            final int i = itemarmor.getColor(itemstack);
            if (i != -1) {
                final float f = (i >> 16 & 0xFF) / 255.0f;
                final float f2 = (i >> 8 & 0xFF) / 255.0f;
                final float f3 = (i & 0xFF) / 255.0f;
                GlStateManager.color(this.colorR * f, this.colorG * f2, this.colorB * f3, this.alpha);
                ModelMethods.setRotationAnglesModelBiped(model, p_177182_2_, p_177182_3_, p_177182_5_, p_177182_6_, p_177182_7_, p_177182_8_, (Entity)entitylivingbaseIn);
                ModelMethods.renderModelBiped(model, (Entity)entitylivingbaseIn, p_177182_8_);
                this.rendererIn.bindTexture(this.getArmorResource((Entity)entitylivingbaseIn, itemstack, flag ? 2 : 1, "overlay"));
            }
            GlStateManager.color(this.colorR, this.colorG, this.colorB, this.alpha);
            if (true) {
                ModelMethods.setRotationAnglesModelBiped(model, p_177182_2_, p_177182_3_, p_177182_5_, p_177182_6_, p_177182_7_, p_177182_8_, (Entity)entitylivingbaseIn);
                ModelMethods.renderModelBiped(model, (Entity)entitylivingbaseIn, p_177182_8_);
            }
            else {
                model.render((Entity)entitylivingbaseIn, p_177182_2_, p_177182_3_, p_177182_5_, p_177182_6_, p_177182_7_, p_177182_8_);
            }
            if (itemstack.hasEffect()) {
                this.func_177183_a(entitylivingbaseIn, model, p_177182_2_, p_177182_3_, p_177182_4_, p_177182_5_, p_177182_6_, p_177182_7_, p_177182_8_);
            }
        }
    }
    
    private boolean isSlotForLeggings(final int armorSlot) {
        return armorSlot == 2;
    }
    
    private void func_177183_a(final EntityLivingBase entitylivingbaseIn, final ModelBiped modelbaseIn, final float p_177183_3_, final float p_177183_4_, final float p_177183_5_, final float p_177183_6_, final float p_177183_7_, final float p_177183_8_, final float p_177183_9_) {
        final float f = entitylivingbaseIn.ticksExisted + p_177183_5_;
        this.rendererIn.bindTexture(CustomLayerBipedArmor.ENCHANTED_ITEM_GLINT_RES);
        GlStateManager.enableBlend();
        GlStateManager.depthFunc(514);
        GlStateManager.depthMask(false);
        final float f2 = 0.5f;
        GlStateManager.color(f2, f2, f2, 1.0f);
        for (int i = 0; i < 2; ++i) {
            GlStateManager.disableLighting();
            GlStateManager.blendFunc(768, 1);
            final float f3 = 0.76f;
            GlStateManager.color(0.5f * f3, 0.25f * f3, 0.8f * f3, 1.0f);
            GlStateManager.matrixMode(5890);
            GlStateManager.loadIdentity();
            final float f4 = 0.33333334f;
            GlStateManager.scale(f4, f4, f4);
            GlStateManager.rotate(30.0f - i * 60.0f, 0.0f, 0.0f, 1.0f);
            GlStateManager.translate(0.0f, f * (0.001f + i * 0.003f) * 20.0f, 0.0f);
            GlStateManager.matrixMode(5888);
            ModelMethods.renderModelBiped(modelbaseIn, (Entity)entitylivingbaseIn, p_177183_9_);
        }
        GlStateManager.matrixMode(5890);
        GlStateManager.loadIdentity();
        GlStateManager.matrixMode(5888);
        GlStateManager.enableLighting();
        GlStateManager.depthMask(true);
        GlStateManager.depthFunc(515);
        GlStateManager.disableBlend();
    }
    
    static {
        renderer = new FieldWrapper<RendererLivingEntity<?>>(isObfuscated() ? "field_177190_a" : "renderer", LayerArmorBase.class);
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

	@Override
	protected void initArmor() {
		// TODO Auto-generated method stub
		
	}
}
