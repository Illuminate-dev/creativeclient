package creativeclient.hud.mod.impl;

import java.awt.Color;

import creativeclient.hud.mod.HudMod;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItemFrame;

public class TargetHUD extends HudMod {

	EntityLivingBase target;
	
	public TargetHUD() {
		super("TargetHUD", 150, 150);
	}
	
	@Override
	public void draw() {
		renderTargetHud();
		
		super.draw();
	}
	
	@Override
	public void renderDummy(int mouseX, int mouseY) {
		
		double playerHealth = (double) Math.round(mc.thePlayer.getHealth() * 10f) / 10f;
		
		
		fr.drawStringWithShadow(mc.thePlayer.getName(), getX() + 2, getY() + 2, -1);
		fr.drawStringWithShadow(playerHealth + " \u2764", getX() + 2, getY() + 2 + fr.FONT_HEIGHT, -1);
		GuiInventory.drawEntityOnScreen(getX() + fr.getStringWidth(mc.thePlayer.getName()) + 17, getY() + 30, 20, 50, 0, mc.thePlayer);
		
		super.renderDummy(mouseX, mouseY);
	}
	
	@Override
	public int getWidth() {
		return 100;
	}
	
	@Override
	public int getHeight() {
		return fr.FONT_HEIGHT * 2 + 4;
	}
	
	private void renderTargetHud() {
		if(!(mc.pointedEntity instanceof EntityItemFrame)) {
			target = (EntityLivingBase) mc.pointedEntity;
			
			
			if(target != null) {
				double targetHealth = (double) Math.round(target.getHealth() * 10f) / 10f;
				fr.drawStringWithShadow(target.getName(), getX() + 2, getY() + 2, -1);
				fr.drawStringWithShadow(targetHealth + " \u2764", getX() + 2, getY() + 2 + fr.FONT_HEIGHT, -1);
				GuiInventory.drawEntityOnScreen(getX() + fr.getStringWidth(target.getName()) + 17, getY() + 30, 20, 50, 0, target);
			}
		}
		

	}

}
