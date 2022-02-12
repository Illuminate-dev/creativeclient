package creativeclient.hud.mod.impl;

import java.util.Collection;

import creativeclient.hud.mod.HudMod;
import creativeclient.util.HUDUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

public class PotionHud extends HudMod {
	
	protected float zLevelFloat;

	public PotionHud() {
		super("PotionHud", 100, 100);
		
	}
	
	@Override
	public void draw() {
		
		
		int offsetX = 21;
		int offsetY = 14;
		int i = 80;
        int i2 = 16;
		Collection<PotionEffect> collection = this.mc.thePlayer.getActivePotionEffects();

        if (!collection.isEmpty())
        {
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.disableLighting();
            int l = 33;


            if (collection.size() > 5)
            {
                l = 132 / (collection.size() - 1);
            }

            for (PotionEffect potioneffect : this.mc.thePlayer.getActivePotionEffects())
            {
                Potion potion = Potion.potionTypes[potioneffect.getPotionID()];
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                
                if (potion.hasStatusIcon())
                {
                	Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("textures/gui/container/inventory.png"));
                    int i1 = potion.getStatusIconIndex();
                    drawTexturedModalRect((getX() + offsetX) - 20, (getY() + i2) - offsetY, 0 + i1 % 8 * 18, 198 + i1 / 8 * 18, 18, 18);
                }
                
                String s1 = I18n.format(potion.getName(), new Object[0]);
                
               
            
                
                if (potioneffect.getAmplifier() == 1)
                {
                    s1 = s1 + " II";
                }
                else if (potioneffect.getAmplifier() == 2)
                {
                    s1 = s1 + " III";
                }
                else if (potioneffect.getAmplifier() == 3)
                {
                    s1 = s1 + " IV";
                }
                else if (potioneffect.getAmplifier() == 4)
                {
                    s1 = s1 + " V";
                }
                else if (potioneffect.getAmplifier() == 5)
                {
                    s1 = s1 + " VI";
                }
                else if (potioneffect.getAmplifier() == 6)
                {
                    s1 = s1 + " VII";
                }
                else if (potioneffect.getAmplifier() == 7)
                {
                    s1 = s1 + " VIII";
                }
                else if (potioneffect.getAmplifier() == 8)
                {
                    s1 = s1 + " IX";
                }
                else if (potioneffect.getAmplifier() == 9)
                {
                    s1 = s1 + " X";
                }
              	else if (potioneffect.getAmplifier() > 9) {
                   	s1 = s1 + " " + (potioneffect.getAmplifier() + 1);        
              	}
                
        		fr.drawString(s1, getX() + offsetX, (getY() + i2) - offsetY, 16777215, true);
                String s = Potion.getDurationString(potioneffect);
        		fr.drawString(s, getX() + offsetX, (getY() + i2 + 10) - offsetY, 8355711, true);
                i2 += l;
            }
        }
	}
	
	
	@Override
	public void renderDummy(int mouseX, int mouseY) {
		
		fr.drawString("Potion Effects", getX(), getY(), -1);
		
		super.renderDummy(mouseX, mouseY);
	}
	
	@Override
	public int getWidth() {
		return 101;
	}

	@Override
	public int getHeight() {
		return 154;
	}
	
	public void drawTexturedModalRect(int x, int y, int textureX, int textureY, int width, int height)
    {
        float f = 0.00390625F;
        float f1 = 0.00390625F;
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
        worldrenderer.pos((double)(x + 0), (double)(y + height), (double)this.zLevelFloat).tex((double)((float)(textureX + 0) * f), (double)((float)(textureY + height) * f1)).endVertex();
        worldrenderer.pos((double)(x + width), (double)(y + height), (double)this.zLevelFloat).tex((double)((float)(textureX + width) * f), (double)((float)(textureY + height) * f1)).endVertex();
        worldrenderer.pos((double)(x + width), (double)(y + 0), (double)this.zLevelFloat).tex((double)((float)(textureX + width) * f), (double)((float)(textureY + 0) * f1)).endVertex();
        worldrenderer.pos((double)(x + 0), (double)(y + 0), (double)this.zLevelFloat).tex((double)((float)(textureX + 0) * f), (double)((float)(textureY + 0) * f1)).endVertex();
        tessellator.draw();
    }

}
