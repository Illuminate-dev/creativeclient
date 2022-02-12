package creativeclient.hud.mod.impl;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Mouse;

import creativeclient.hud.ScreenPosition;
import creativeclient.hud.mod.HudMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

public class DirectionMod extends HudMod {

	public DirectionMod() {
		super("Yaw & Pitch", 290, 290);
		
	}
	
	@Override
	public void draw() {
		double yaw = (Minecraft.getMinecraft().thePlayer.rotationYawHead) % 360;
        if (yaw < -180.1) {
        	yaw += 360.0;
        }
        if(yaw > 180.1) {
        	yaw -= 360.0;
        }
        yaw = Math.round(yaw * 10);
        yaw /=10;
		float pitch = Math.round(mc.thePlayer.rotationPitch * 10);
		pitch /=10;
		
		fr.drawStringWithShadow("§6Yaw§f: " + yaw, this.getX(), this.getY() + fr.FONT_HEIGHT + 2, -1);
		fr.drawStringWithShadow("§6Pitch§f: "+ pitch, this.getX(), this.getY(), -1);
		
		super.draw();
	}
	

	@Override
	public void renderDummy(int mouseX, int mouseY) {
		double yaw = (Minecraft.getMinecraft().thePlayer.rotationYawHead) % 360;
        if (yaw < -180.1) {
        	yaw += 360.0;
        }
        if(yaw > 180.1) {
        	yaw -= 360.0;
        }
        yaw = Math.round(yaw * 10);
        yaw /=10;
		float pitch = Math.round(mc.thePlayer.rotationPitch * 10);
		pitch /=10;
		
		fr.drawStringWithShadow("§6Yaw§f: " + yaw, this.getX(), this.getY() + fr.FONT_HEIGHT + 2, -1);
		fr.drawStringWithShadow("§6Pitch§f: "+ pitch, this.getX(), this.getY(), -1);
		
		super.renderDummy(mouseX, mouseY);
	}
	
	@Override
	public int getWidth() {
		return fr.getStringWidth("Pitch: 180.0");
	}
	
	@Override
	public int getHeight() {
		return fr.FONT_HEIGHT * 2;
	}
	

}
