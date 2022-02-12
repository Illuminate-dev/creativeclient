package creativeclient.mod.impl.animationsmod.util;

import net.minecraft.entity.*;
import net.minecraft.util.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.*;
import net.minecraft.client.renderer.*;

public class ModelMethods
{
    private static final FieldWrapper<ModelRenderer> bipedCape;
    
    public static void setRotationAnglesModelBiped(final ModelBiped model, final float p_78087_1_, final float p_78087_2_, final float p_78087_3_, final float p_78087_4_, final float p_78087_5_, final float p_78087_6_, final Entity entityIn) {
        model.bipedHead.rotateAngleY = p_78087_4_ / 57.295776f;
        model.bipedHead.rotateAngleX = p_78087_5_ / 57.295776f;
        model.bipedRightArm.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662f + 3.1415927f) * 2.0f * p_78087_2_ * 0.5f;
        model.bipedLeftArm.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662f) * 2.0f * p_78087_2_ * 0.5f;
        model.bipedRightArm.rotateAngleZ = 0.0f;
        model.bipedLeftArm.rotateAngleZ = 0.0f;
        model.bipedRightLeg.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662f) * 1.4f * p_78087_2_;
        model.bipedLeftLeg.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662f + 3.1415927f) * 1.4f * p_78087_2_;
        model.bipedRightLeg.rotateAngleY = 0.0f;
        model.bipedLeftLeg.rotateAngleY = 0.0f;
        if (model.isRiding) {
            final ModelRenderer bipedRightArm = model.bipedRightArm;
            bipedRightArm.rotateAngleX -= 0.62831855f;
            final ModelRenderer bipedLeftArm = model.bipedLeftArm;
            bipedLeftArm.rotateAngleX -= 0.62831855f;
            model.bipedRightLeg.rotateAngleX = -1.2566371f;
            model.bipedLeftLeg.rotateAngleX = -1.2566371f;
            model.bipedRightLeg.rotateAngleY = 0.31415927f;
            model.bipedLeftLeg.rotateAngleY = -0.31415927f;
        }
        if (model.heldItemLeft != 0) {
            model.bipedLeftArm.rotateAngleX = model.bipedLeftArm.rotateAngleX * 0.5f - 0.31415927f * model.heldItemLeft;
        }
        model.bipedRightArm.rotateAngleY = 0.0f;
        model.bipedRightArm.rotateAngleZ = 0.0f;
        switch (model.heldItemRight) {
            case 1: {
                model.bipedRightArm.rotateAngleX = model.bipedRightArm.rotateAngleX * 0.5f - 0.31415927f * model.heldItemRight;
                break;
            }
            case 3: {
                model.bipedRightArm.rotateAngleX = model.bipedRightArm.rotateAngleX * 0.5f - 0.31415927f * model.heldItemRight;
                if (true) {
                    model.bipedRightArm.rotateAngleY = 0.0f;
                    break;
                }
                model.bipedRightArm.rotateAngleY = -0.5235988f;
                break;
            }
        }
        model.bipedLeftArm.rotateAngleY = 0.0f;
        if (model.swingProgress > -9990.0f) {
            float f = model.swingProgress;
            model.bipedBody.rotateAngleY = MathHelper.sin(MathHelper.sqrt_float(f) * 3.1415927f * 2.0f) * 0.2f;
            model.bipedRightArm.rotationPointZ = MathHelper.sin(model.bipedBody.rotateAngleY) * 5.0f;
            model.bipedRightArm.rotationPointX = -MathHelper.cos(model.bipedBody.rotateAngleY) * 5.0f;
            model.bipedLeftArm.rotationPointZ = -MathHelper.sin(model.bipedBody.rotateAngleY) * 5.0f;
            model.bipedLeftArm.rotationPointX = MathHelper.cos(model.bipedBody.rotateAngleY) * 5.0f;
            final ModelRenderer bipedRightArm2 = model.bipedRightArm;
            bipedRightArm2.rotateAngleY += model.bipedBody.rotateAngleY;
            final ModelRenderer bipedLeftArm2 = model.bipedLeftArm;
            bipedLeftArm2.rotateAngleY += model.bipedBody.rotateAngleY;
            final ModelRenderer bipedLeftArm3 = model.bipedLeftArm;
            bipedLeftArm3.rotateAngleX += model.bipedBody.rotateAngleY;
            f = 1.0f - model.swingProgress;
            f *= f;
            f *= f;
            f = 1.0f - f;
            final float f2 = MathHelper.sin(f * 3.1415927f);
            final float f3 = MathHelper.sin(model.swingProgress * 3.1415927f) * -(model.bipedHead.rotateAngleX - 0.7f) * 0.75f;
            model.bipedRightArm.rotateAngleX -= (float)(f2 * 1.2 + f3);
            final ModelRenderer bipedRightArm3 = model.bipedRightArm;
            bipedRightArm3.rotateAngleY += model.bipedBody.rotateAngleY * 2.0f;
            final ModelRenderer bipedRightArm4 = model.bipedRightArm;
            bipedRightArm4.rotateAngleZ += MathHelper.sin(model.swingProgress * 3.1415927f) * -0.4f;
        }
        if (model.isSneak) {
            model.bipedBody.rotateAngleX = 0.5f;
            final ModelRenderer bipedRightArm5 = model.bipedRightArm;
            bipedRightArm5.rotateAngleX += 0.4f;
            final ModelRenderer bipedLeftArm4 = model.bipedLeftArm;
            bipedLeftArm4.rotateAngleX += 0.4f;
            model.bipedRightLeg.rotationPointZ = 4.0f;
            model.bipedLeftLeg.rotationPointZ = 4.0f;
            model.bipedRightLeg.rotationPointY = 9.0f;
            model.bipedLeftLeg.rotationPointY = 9.0f;
            model.bipedHead.rotationPointY = 1.0f;
        }
        else {
            model.bipedBody.rotateAngleX = 0.0f;
            model.bipedRightLeg.rotationPointZ = 0.1f;
            model.bipedLeftLeg.rotationPointZ = 0.1f;
            model.bipedRightLeg.rotationPointY = 12.0f;
            model.bipedLeftLeg.rotationPointY = 12.0f;
            model.bipedHead.rotationPointY = 0.0f;
        }
        final ModelRenderer bipedRightArm6 = model.bipedRightArm;
        bipedRightArm6.rotateAngleZ += MathHelper.cos(p_78087_3_ * 0.09f) * 0.05f + 0.05f;
        final ModelRenderer bipedLeftArm5 = model.bipedLeftArm;
        bipedLeftArm5.rotateAngleZ -= MathHelper.cos(p_78087_3_ * 0.09f) * 0.05f + 0.05f;
        final ModelRenderer bipedRightArm7 = model.bipedRightArm;
        bipedRightArm7.rotateAngleX += MathHelper.sin(p_78087_3_ * 0.067f) * 0.05f;
        final ModelRenderer bipedLeftArm6 = model.bipedLeftArm;
        bipedLeftArm6.rotateAngleX -= MathHelper.sin(p_78087_3_ * 0.067f) * 0.05f;
        if (model.aimedBow) {
            final float f4 = 0.0f;
            final float f5 = 0.0f;
            model.bipedRightArm.rotateAngleZ = 0.0f;
            model.bipedLeftArm.rotateAngleZ = 0.0f;
            model.bipedRightArm.rotateAngleY = -(0.1f - f4 * 0.6f) + model.bipedHead.rotateAngleY;
            model.bipedLeftArm.rotateAngleY = 0.1f - f4 * 0.6f + model.bipedHead.rotateAngleY + 0.4f;
            model.bipedRightArm.rotateAngleX = -1.5707964f + model.bipedHead.rotateAngleX;
            model.bipedLeftArm.rotateAngleX = -1.5707964f + model.bipedHead.rotateAngleX;
            final ModelRenderer bipedRightArm8 = model.bipedRightArm;
            bipedRightArm8.rotateAngleX -= f4 * 1.2f - f5 * 0.4f;
            final ModelRenderer bipedLeftArm7 = model.bipedLeftArm;
            bipedLeftArm7.rotateAngleX -= f4 * 1.2f - f5 * 0.4f;
            final ModelRenderer bipedRightArm9 = model.bipedRightArm;
            bipedRightArm9.rotateAngleZ += MathHelper.cos(p_78087_3_ * 0.09f) * 0.05f + 0.05f;
            final ModelRenderer bipedLeftArm8 = model.bipedLeftArm;
            bipedLeftArm8.rotateAngleZ -= MathHelper.cos(p_78087_3_ * 0.09f) * 0.05f + 0.05f;
            final ModelRenderer bipedRightArm10 = model.bipedRightArm;
            bipedRightArm10.rotateAngleX += MathHelper.sin(p_78087_3_ * 0.067f) * 0.05f;
            final ModelRenderer bipedLeftArm9 = model.bipedLeftArm;
            bipedLeftArm9.rotateAngleX -= MathHelper.sin(p_78087_3_ * 0.067f) * 0.05f;
        }
        ModelBase.copyModelAngles(model.bipedHead, model.bipedHeadwear);
    }
    
    public static void setRotationAnglesModelPlayer(final ModelPlayer model, final float p_78087_1_, final float p_78087_2_, final float p_78087_3_, final float p_78087_4_, final float p_78087_5_, final float p_78087_6_, final Entity entityIn) {
        setRotationAnglesModelBiped((ModelBiped)model, p_78087_1_, p_78087_2_, p_78087_3_, p_78087_4_, p_78087_5_, p_78087_6_, entityIn);
        ModelBase.copyModelAngles(model.bipedLeftLeg, model.bipedLeftLegwear);
        ModelBase.copyModelAngles(model.bipedRightLeg, model.bipedRightLegwear);
        ModelBase.copyModelAngles(model.bipedLeftArm, model.bipedLeftArmwear);
        ModelBase.copyModelAngles(model.bipedRightArm, model.bipedRightArmwear);
        ModelBase.copyModelAngles(model.bipedBody, model.bipedBodyWear);
        if (entityIn.isSneaking()) {
            ModelMethods.bipedCape.get(model).rotationPointY = 2.0f;
        }
        else {
            ModelMethods.bipedCape.get(model).rotationPointY = 0.0f;
        }
    }
    
    public static void renderModelPlayer(final ModelPlayer model, final Entity entityIn, final float scale) {
        renderModelBiped((ModelBiped)model, entityIn, scale);
        GlStateManager.pushMatrix();
        if (model.isChild) {
            final float f = 2.0f;
            GlStateManager.scale(1.0f / f, 1.0f / f, 1.0f / f);
            GlStateManager.translate(0.0f, 24.0f * scale, 0.0f);
            model.bipedLeftLegwear.render(scale);
            model.bipedRightLegwear.render(scale);
            model.bipedLeftArmwear.render(scale);
            model.bipedRightArmwear.render(scale);
            model.bipedBodyWear.render(scale);
        }
        else {
            if (entityIn.isSneaking()) {
                GlStateManager.translate(0.0f, 0.2f, 0.0f);
            }
            model.bipedLeftLegwear.render(scale);
            model.bipedRightLegwear.render(scale);
            model.bipedLeftArmwear.render(scale);
            model.bipedRightArmwear.render(scale);
            model.bipedBodyWear.render(scale);
        }
        GlStateManager.popMatrix();
    }
    
    public static void renderModelBiped(final ModelBiped model, final Entity entityIn, final float scale) {
        GlStateManager.pushMatrix();
        if (model.isChild) {
            final float f = 2.0f;
            GlStateManager.scale(1.5f / f, 1.5f / f, 1.5f / f);
            GlStateManager.translate(0.0f, 16.0f * scale, 0.0f);
            model.bipedHead.render(scale);
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            GlStateManager.scale(1.0f / f, 1.0f / f, 1.0f / f);
            GlStateManager.translate(0.0f, 24.0f * scale, 0.0f);
            model.bipedBody.render(scale);
            model.bipedRightArm.render(scale);
            model.bipedLeftArm.render(scale);
            model.bipedRightLeg.render(scale);
            model.bipedLeftLeg.render(scale);
            model.bipedHeadwear.render(scale);
        }
        else {
            if (entityIn.isSneaking()) {
                GlStateManager.translate(0.0f, 0.2f, 0.0f);
            }
            model.bipedHead.render(scale);
            model.bipedBody.render(scale);
            model.bipedRightArm.render(scale);
            model.bipedLeftArm.render(scale);
            model.bipedRightLeg.render(scale);
            model.bipedLeftLeg.render(scale);
            model.bipedHeadwear.render(scale);
        }
        GlStateManager.popMatrix();
    }
    
    static {
        bipedCape = new FieldWrapper<ModelRenderer>(isObfuscated() ? "field_178729_w" : "bipedCape", ModelPlayer.class);
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
