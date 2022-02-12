package creativeclient.hud.mod.impl;

import java.awt.Color;


import creativeclient.hud.mod.HudMod;
import net.minecraft.client.Minecraft;

public class Coordinates extends HudMod{

	public Coordinates() {
		super("Coordinates", 270, 270);
		// TODO Auto-generated constructor stub
	}

	int X;
	int Y;
	int Z;
	
	
	@Override
	public int getWidth() {
		return fr.getStringWidth("Xddd");
	}

	@Override
	public int getHeight() {
		return fr.FONT_HEIGHT + 18;
	}

	@Override
	public void draw() {
		if(!mc.gameSettings.showDebugInfo) {
			X = mc.thePlayer.getPosition().getX();
			Y = mc.thePlayer.getPosition().getY();
			Z = mc.thePlayer.getPosition().getZ();
			
				fr.drawStringWithShadow("§6X" + " §f" + X, this.getX() + 1, this.getY() + 1, 0x595959);
				fr.drawStringWithShadow("§6Y" + " §f" + Y, this.getX() + 1, this.getY() + 10, 0x595959);
				fr.drawStringWithShadow("§6Z" + " §f" + Z, this.getX() + 1, this.getY() + 20,0x595959);
		}

		
		
	
}
	
	@Override
	public void renderDummy(int mouseX, int mouseY) {
		if(!mc.gameSettings.showDebugInfo) {
			fr.drawStringWithShadow("§6X §f" + X, this.getX() + 1, this.getY() + 1, 0x595959);
			fr.drawStringWithShadow("§6Y §f" + Y, this.getX() + 1, this.getY() + 10, 0x595959);		
			fr.drawStringWithShadow("§6Z §f" + Z, this.getX() + 1, this.getY() + 20, 0x595959);
			super.renderDummy(mouseX, mouseY);
		}
}
	

}
