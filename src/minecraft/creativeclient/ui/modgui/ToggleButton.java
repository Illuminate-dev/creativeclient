package creativeclient.ui.modgui;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import creativeclient.Creative;
import creativeclient.hud.mod.HudMod;
import creativeclient.mod.Mod;
import creativeclient.mod.impl.animationsmod.AnimationsMod;
import creativeclient.settings.BooleanSetting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;

public class ToggleButton {
	
	public int x, y, w, h;
	private BooleanSetting s;
	public Minecraft mc;
	
	public ToggleButton(int x, int y, int w, int h, BooleanSetting s) {
		this.x = x;
		this.y = y;
		this.h = h;
		this.w = w;
		this.s = s;
	}
	
	public void draw() {
		
		Gui.drawRect(x, y - 2, x + w, y + h  + 2, new Color(0, 0, 0, 80).getRGB());
		Minecraft.getMinecraft().fontRendererObj.drawString(s.name, x + 2, y + 2, getColor());	

	}
	
	private int getColor() {
		
		if(s.isEnabled()) {
			return new Color(0, 255, 0, 255).getRGB();
		} else {
			return new Color(255, 0, 0, 255).getRGB();
		}
		

	}
	
	public void onClick(int mouseX, int mouseY, int button) {
		
		if(mouseX >= x && mouseX <=x + w && mouseY >= y && mouseY <= y + h) {
			s.toggle();
		}
		
		
	}

}
