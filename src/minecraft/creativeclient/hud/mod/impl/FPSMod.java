package creativeclient.hud.mod.impl;

import java.awt.Color;

import creativeclient.hud.mod.HudMod;
import net.minecraft.client.gui.Gui;

public class FPSMod extends HudMod {
	
	

	public FPSMod() {
		super("FPS", 100, 100);
		
	}
	
	@Override
	public void draw() {
		
		fr.drawStringWithShadow("§8[§6FPS§f: " + mc.getDebugFPS() + "§8]", getX(), getY(), -1);
		super.draw();
	}
	
	@Override
	public void renderDummy(int mouseX, int mouseY) {
		
		fr.drawStringWithShadow("§8[§6FPS§f: " + mc.getDebugFPS() + "§8]", getX(), getY(), -1);
		
		super.renderDummy(mouseX, mouseY);
	}
	
	@Override
	public int getWidth() {
		return fr.getStringWidth("§8[§6FPS§f: " + mc.getDebugFPS() + "§8]");
	}
	
	@Override
	public int getHeight() {
		return fr.FONT_HEIGHT;
	}

}
