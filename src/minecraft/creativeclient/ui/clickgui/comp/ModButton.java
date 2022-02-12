package creativeclient.ui.clickgui.comp;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import creativeclient.hud.mod.HudMod;
import creativeclient.mod.Mod;
import creativeclient.mod.impl.animationsmod.AnimationsMod;
import creativeclient.mod.impl.blockoverlay.BlockOverlayGui;
import creativeclient.mod.impl.hitboxes.HitBoxGui;
import creativeclient.mod.impl.itemmodel.ItemModelGui;
import creativeclient.mod.impl.itemphysics.ItemPhysicsGui;
import creativeclient.mod.impl.motionblur.MotionBlurGui;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;

public class ModButton {
	
	public int x, y, w, h;
	public HudMod m;
	public Mod mod;
	public Minecraft mc;
	public FontRenderer fr;
	
	public ModButton(int x, int y, int w, int h, HudMod m) {
		this.x = x;
		this.y = y;
		this.h = h;
		this.w = w;
		this.m = m;
	}
	
	public ModButton(int x, int y, int w, int h, Mod m) {
		this.x = x;
		this.y = y;
		this.h = h;
		this.w = w;
		this.mod = m;
		this.mc = Minecraft.getMinecraft();
	}

	public void draw() {
		if(m == null) {
			Gui.drawRect(x, y - 2, x + w + 20, y + h  + 2, new Color(0, 0, 0, 80).getRGB());
			mc.fontRendererObj.drawString(mod.name, x + 2, y + 2, getColor());
			
			GL11.glColor4f(1, 1, 1, 1);
			ResourceLocation settings = new ResourceLocation("creativeclient/gifs/settings/0.png");
			mc.getTextureManager().bindTexture(settings);
			
			Gui.drawModalRectWithCustomSizedTexture(x + 9 + Minecraft.getMinecraft().fontRendererObj.getStringWidth(mod.name) - 4, y - 2, 0, 0, 16, 16, 16, 16);
			
		} else {
			Gui.drawRect(x, y - 2, x + w + 20, y + h + 2, new Color(0, 0, 0, 80).getRGB());
			Minecraft.getMinecraft().fontRendererObj.drawString(m.name, x + 2, y + 2, getColor());
			
			GL11.glColor4f(1, 1, 1, 1);
			ResourceLocation settings = new ResourceLocation("creativeclient/gifs/settings/0.png");
			Minecraft.getMinecraft().getTextureManager().bindTexture(settings);
			
			Gui.drawModalRectWithCustomSizedTexture(x + 4 + Minecraft.getMinecraft().fontRendererObj.getStringWidth(m.name), y - 2, 0, 0, 16, 16, 16, 16);
		}
		

	}
	
	private int getColor() {
		if(m == null) {
			if(mod.isEnabled()) {
				return new Color(0, 255, 0, 255).getRGB();
			} else {
				return new Color(255, 0, 0, 255).getRGB();
			}
		} else {
			if(m.isEnabled()) {
				return new Color(0, 255, 0, 255).getRGB();
			} else {
				return new Color(255, 0, 0, 255).getRGB();
			}
		}
		

	}
	
	public void onClick(int mouseX, int mouseY, int button) {
		if(m == null) {
			int settingsX = x + 9 + Minecraft.getMinecraft().fontRendererObj.getStringWidth(mod.name) - 4;
			int settingsY = y - 2;
			
			if(mouseX >= x && mouseX <=x + w && mouseY >= y && mouseY <= y + h) {
				mod.toggle();
				
			}
			
			if(mouseX >= settingsX && mouseY >= settingsY && mouseX < settingsX + 16 && mouseY < settingsY + 16) {
				switch (mod.name) {
					case "1.7 Animations":
						break;
					case "Block Overlay":
						Minecraft.getMinecraft().displayGuiScreen(new BlockOverlayGui());
						break;
					case "Fullbright":
						break;
					case "Scroll Zoom":
						break;
					case "TNT Timer":
						break;
					case "Motionblur":
						Minecraft.getMinecraft().displayGuiScreen(new MotionBlurGui());
						break;
					case "Hitboxes":
						Minecraft.getMinecraft().displayGuiScreen(new HitBoxGui());
						break;
					case "Item Model":
						Minecraft.getMinecraft().displayGuiScreen(new ItemModelGui());
						break;
					case "Item Physics":
						Minecraft.getMinecraft().displayGuiScreen(new ItemPhysicsGui());
						break;
				}
			}
			
		} else {
			int settingsX = x + 4 + Minecraft.getMinecraft().fontRendererObj.getStringWidth(m.name);
			int settingsY = y - 2;
			
			if(mouseX >= x && mouseX <=x + w && mouseY >= y && mouseY <= y + h) {
				m.toggle();
				
			}
			
			if(mouseX >= settingsX && mouseY >= settingsY && mouseX < settingsX + 16 && mouseY < settingsY + 16) {
				
			}
		}
		
	}

}
