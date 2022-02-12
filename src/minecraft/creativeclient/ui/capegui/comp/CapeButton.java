package creativeclient.ui.capegui.comp;

import java.awt.Color;

import creativeclient.cosmetics.cape.CapeBase;
import creativeclient.hud.mod.HudMod;
import creativeclient.mod.Mod;
import creativeclient.mod.impl.animationsmod.AnimationsMod;
import creativeclient.util.CapeUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

public class CapeButton {
	
	public int x, y, w, h;
	public CapeBase cape;
	
	public CapeButton(int x, int y, int w, int h, CapeBase cape) {
		this.x = x;
		this.y = y;
		this.h = h;
		this.w = w;
		this.cape = cape;
	}
	
	

	public void draw() {
		Gui.drawRect(x, y - 2, x + w, y + h, new Color(0, 0, 0, 80).getRGB());
		Minecraft.getMinecraft().fontRendererObj.drawString(cape.getName(), x + 2, y + 2, getColor());
		

	}
	
	private int getColor() {
		
		if(cape.isEnabled()) {
			return new Color(0, 255, 0, 255).getRGB();
		} else {
			return new Color(255, 0, 0, 255).getRGB();
		}
		
	}
	
	public void onClick(int mouseX, int mouseY, int button) {
		
		if(mouseX >= x && mouseX <=x + w && mouseY >= y && mouseY <= y + h) {
			if(!cape.isEnabled()) {
				CapeUtils.setCape(cape);
			} else {
				CapeUtils.resetCape();
			}
			
			
		}
		
	}

}
